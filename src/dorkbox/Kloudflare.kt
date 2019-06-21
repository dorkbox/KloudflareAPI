/*
 * Copyright 2019 dorkbox, llc
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
package dorkbox

import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import dorkbox.api.CloudflareActions
import dorkbox.api.core.CfErrorResponse
import dorkbox.api.core.CfResponse
import dorkbox.api.core.Error
import dorkbox.api.core.ISO8601Adapter
import dorkbox.api.dns.DnsRecord
import dorkbox.api.dns.DnsRecordTypeAdapter
import dorkbox.api.user.BillingHistory
import dorkbox.api.user.BillingProfile
import dorkbox.api.user.User
import dorkbox.api.zone.RatePlan
import dorkbox.api.zone.Zone
import dorkbox.api.zone.settings.ZoneSetting
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import okhttp3.logging.HttpLoggingInterceptor
import org.slf4j.LoggerFactory
import retrofit2.Call
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.io.IOException


class Kloudflare(private val xAuthEmail: String, private val xAuthKey: String) {
    private val logger = LoggerFactory.getLogger(Kloudflare::class.java)


    companion object {
        private const val API_BASE_URL = "https://api.cloudflare.com/client/v4/"

        val errorConverter: Converter<ResponseBody, CfErrorResponse>
        val cloudflare: CloudflareActions

        init {
            // JSON mapping to java classes
            val httpClient = OkHttpClient.Builder()

            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY

            val client = httpClient
//                    .addInterceptor(interceptor)  // this is the raw HTTP interceptor
                    .build()

            val moshi = Moshi.Builder()
                    .add(ISO8601Adapter())
                    .add(DnsRecordTypeAdapter())
                    .build()

            val adapter = moshi.adapter<List<String>>(Types.newParameterizedType(List::class.java, String::class.java))


            val retrofit = Retrofit.Builder()
                    .baseUrl(API_BASE_URL)
                    .addConverterFactory(MoshiConverterFactory.create(moshi))
                    .client(client)
                    .build()

            errorConverter = retrofit.responseBodyConverter<CfErrorResponse>(CfErrorResponse::class.java, CfErrorResponse::class.annotations.toTypedArray())

            cloudflare = retrofit.create(CloudflareActions::class.java)
        }

        fun <T> wrap(call: Call<CfResponse<T>>): T {
            val response = call.execute()

            val body = response.body()
            if (response.isSuccessful && body != null && body.success) {
                return body.result!!
            }

            val errorResponse = errorConverter.convert(response.errorBody()!!)
            throw IOException("HTTP call failed: " + errorResponse?.errors?.joinToString { error: Error -> "[${error.code} : ${error.message}]" })
        }
    }


    fun getUser() : User {
        return wrap(cloudflare.getUser(xAuthEmail, xAuthKey))
    }

    fun getUserBillingProfile(): BillingProfile {
        return wrap(cloudflare.getUserBillingProfile(xAuthEmail, xAuthKey))
    }

    fun getUserBillingHistory(): BillingHistory {
        return wrap(cloudflare.getUserBillingHistory(xAuthEmail, xAuthKey))
    }

    fun listZones(options: Map<String, String> = emptyMap()): List<Zone> {
        return wrap(cloudflare.listZones(xAuthEmail, xAuthKey, options))
    }

    fun getZoneRatePlans(zoneIdentifier: String): RatePlan {
        return wrap(cloudflare.getZoneRatePlans(xAuthEmail, xAuthKey, zoneIdentifier))
    }

    fun getZoneSettings(zoneIdentifier: String): ZoneSetting {
        return wrap(cloudflare.getZoneSettings(xAuthEmail, xAuthKey, zoneIdentifier))
    }

    fun listDnsRecords(zoneIdentifier: String): List<DnsRecord> {
        return wrap(cloudflare.listDnsRecords(xAuthEmail, xAuthKey, zoneIdentifier))
    }
}

