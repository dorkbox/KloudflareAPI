/*
 * Copyright 2025 dorkbox, llc
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package dorkbox.kloudflareApi

import dorkbox.kloudflareApi.api.FirewallActions
import dorkbox.kloudflareApi.api.UserActions
import dorkbox.kloudflareApi.api.ZoneActions
import dorkbox.kloudflareApi.api.core.*
import dorkbox.kloudflareApi.api.dns.DnsRecord
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.util.*
import kotlinx.serialization.json.Json
import java.io.IOException

class Kloudflare(private val xAuthEmail: String, private val xAuthKey: String) {
    companion object {
        private const val API_BASE_URL = "https://api.cloudflare.com/client/v4/"

        /**
         * Gets the version number.
         */
        const val version = "3.0"

        init {
            // Add this project to the updates system, which verifies this class + UUID + version information
            dorkbox.updates.Updates.add(Kloudflare::class.java, "16bcc9060ac6483782aafc2a5502e7b3", version)
        }
    }

    val user: UserActions
    val zones: ZoneActions
    val firewall: FirewallActions

    private val client: HttpClient

    init {
        client = HttpClient(CIO) {
            install(ContentNegotiation) {
                json(Json {
                    ignoreUnknownKeys = true
                })
            }

//            install(Logging) {
//                logger = Logger.DEFAULT
//                level = LogLevel.ALL
//            }

            // set default request parameters
            defaultRequest {
                // add base url for all request
                url(API_BASE_URL)

                headers.appendIfNameAbsent(HttpHeaders.ContentType, ContentType.Application.Json.toString())
                headers.append("X-Auth-Email", xAuthEmail)
                headers.append("X-Auth-Key", xAuthKey)
            }
        }

        user = UserActions(client)
        zones = ZoneActions(client)
        firewall = FirewallActions(client)
    }

    /**
     * @return the content of a http get to the requested URL
     */
    suspend fun get(url: String): String? {
        val response = runCatching {
            val b = HttpRequestBuilder()
            b.method = HttpMethod.Get
            b.url(url)

            client.request(b)
        }

        return if (response.isSuccess) {
            response.getOrNull()?.body<String>()?.trim()
        } else {
            null
        }
    }


    /**
     * Shuts down the HTTP executors used. This is necessary if you want to be able to shutdown the JVM
     */
    fun shutdown() {
        client.close()
    }
}


suspend inline fun <reified R> HttpClient.cf(
    method: HttpMethod,
    urlString: String,
    builder: HttpRequestBuilder.() -> Unit = {}
): R {
    val response = runCatching {
        val b = HttpRequestBuilder().apply(builder)
        b.method = method
        b.url(urlString)

        request(b)
    }

    if (response.isSuccess) {
        if (DnsRecord::class.java.isAssignableFrom(R::class.java)) {
            val cfResponse = response.getOrNull()?.body<CfResponseDnsRecord>()
            val success = cfResponse?.success == true
            if (success) {
                return cfResponse?.result as R
            } else {
                throw IOException("Call ($method) failed: " + cfResponse?.errors?.joinToString())
            }
        } else {
            val cfResponse = response.getOrNull()?.body<CfResponse<R>>()
            val success = cfResponse?.success == true
            if (success) {
                return cfResponse?.result!!
            } else {
                throw IOException("Call ($method) failed: " + cfResponse?.errors?.joinToString())
            }
        }
    }

    throw IOException("Call ($method) failed: " + response.exceptionOrNull())
}

suspend inline fun <reified R> HttpClient.cfList(
    method: HttpMethod,
    urlString: String,
    builder: HttpRequestBuilder.() -> Unit = {}
): List<R> {
    val results = mutableListOf<R>()

    var response = runCatching {
        val b = HttpRequestBuilder().apply(builder)
        b.method = method
        b.url(urlString)

        request(b)
    }

    if (response.isSuccess) {
        var success = false
        var resultInfo: ResultInfo?

        if (DnsRecord::class.java.isAssignableFrom(R::class.java)) {
            val cfResponse = response.getOrNull()?.body<CfResponseDnsRecordList>()
            success = cfResponse?.success == true
            resultInfo = cfResponse?.resultInfo
            if (success) {
                @Suppress("UNCHECKED_CAST")
                results.addAll(cfResponse?.result as List<R>)
            } else {
                throw IOException("Call ($method) failed: " + cfResponse?.errors?.joinToString())
            }
        } else {
            val cfResponse = response.getOrNull()?.body<CfResponseList<R>>()
            success = cfResponse?.success == true
            resultInfo = cfResponse?.resultInfo
            if (success) {
                results.addAll(cfResponse?.result!!)
            } else {
                throw IOException("Call ($method) failed: " + cfResponse?.errors?.joinToString())
            }
        }

        while (resultInfo != null && resultInfo.totalPages - resultInfo.page > 0) {
            val nextPage = (resultInfo.page + 1).toString()
            response = runCatching {
                val b = HttpRequestBuilder().apply(builder)
                b.method = method
                b.url(urlString)

                b.url.parameters.remove("page")
                b.url.parameters.append("page", nextPage)

                request(b)
            }

            if (response.isSuccess) {
                if (DnsRecord::class.java.isAssignableFrom(R::class.java)) {
                    val cfResponse = response.getOrNull()?.body<CfResponseDnsRecordList>()
                    success = cfResponse?.success == true
                    resultInfo = cfResponse?.resultInfo
                    if (success) {
                        @Suppress("UNCHECKED_CAST")
                        results.addAll(cfResponse?.result as List<R>)
                    }
                } else {
                    val cfResponse = response.getOrNull()?.body<CfResponseList<R>>()
                    success = cfResponse?.success == true
                    resultInfo = cfResponse?.resultInfo
                    if (success) {
                        results.addAll(cfResponse?.result!!)
                    }
                }
            }
        }

        return results
    }

    throw IOException("Call LIST (GET) failed: " + response.exceptionOrNull())
}


