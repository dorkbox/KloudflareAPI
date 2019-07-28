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
package dorkbox.kloudflare

import com.squareup.moshi.Moshi
import dorkbox.kloudflare.api.CloudflareActions
import dorkbox.kloudflare.api.core.CfErrorResponse
import dorkbox.kloudflare.api.core.CfResponse
import dorkbox.kloudflare.api.core.DnsRecordTypeAdapter
import dorkbox.kloudflare.api.core.Error
import dorkbox.kloudflare.api.core.ISO8601Adapter
import dorkbox.kloudflare.api.dns.CreateDnsRecord
import dorkbox.kloudflare.api.dns.DeleteDnsRecord
import dorkbox.kloudflare.api.dns.DnsRecord
import dorkbox.kloudflare.api.dns.UpdateDnsRecord
import dorkbox.kloudflare.api.firewall.AccessRule
import dorkbox.kloudflare.api.user.BillingHistory
import dorkbox.kloudflare.api.user.BillingProfile
import dorkbox.kloudflare.api.user.User
import dorkbox.kloudflare.api.zone.RatePlan
import dorkbox.kloudflare.api.zone.Zone
import dorkbox.kloudflare.api.zone.settings.ZoneSetting
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

        val client: OkHttpClient

        init {
            // JSON mapping to java classes
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY

            client = OkHttpClient.Builder()
        //                    .addInterceptor(interceptor)  // this is the raw HTTP interceptor
                    .build()

            val moshi = Moshi.Builder()
                    .add(ISO8601Adapter())
                    .add(DnsRecordTypeAdapter())
                    .build()

//            val adapter = moshi.adapter<List<String>>(Types.newParameterizedType(List::class.java, String::class.java))


            val retrofit = Retrofit.Builder()
                    .baseUrl(API_BASE_URL)
                    .addConverterFactory(MoshiConverterFactory.create(moshi))
                    .client(client)
                    .build()

            errorConverter = retrofit.responseBodyConverter(CfErrorResponse::class.java, CfErrorResponse::class.annotations.toTypedArray())

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


    fun getUserDetails(): User {
        return wrap(cloudflare.getUserDetails(xAuthEmail, xAuthKey))
    }

    fun getUserBillingProfile(): BillingProfile {
        return wrap(cloudflare.getUserBillingProfile(xAuthEmail, xAuthKey))
    }

    fun getUserBillingHistory(): BillingHistory {
        return wrap(cloudflare.getUserBillingHistory(xAuthEmail, xAuthKey))
    }

    fun listZones(options: Map<String, String> = emptyMap()): List<Zone> {
        val zones = wrap(cloudflare.listZones(xAuthEmail, xAuthKey, options))
        zones.forEach { zone ->
            // have to assign
            zone.kloudflare = this;

        }

        return zones
    }

    fun getZoneRatePlans(zone: Zone): RatePlan {
        return wrap(cloudflare.getZoneRatePlans(xAuthEmail, xAuthKey, zone.id))
    }

    fun getZoneSettings(zone: Zone): ZoneSetting {
        return wrap(cloudflare.getZoneSettings(xAuthEmail, xAuthKey, zone.id))
    }

    fun listDnsRecords(zone: Zone): List<DnsRecord> {
        val wrap =
            wrap(cloudflare.listDnsRecords(xAuthEmail, xAuthKey, zone.id))
        wrap.forEach {
            it.zone = zone
        }
        return wrap
    }

    fun createDnsRecord(dnsRecord: CreateDnsRecord): DnsRecord {
        val wrap =
            wrap(cloudflare.createDnsRecord(xAuthEmail, xAuthKey, dnsRecord.zone.id, dnsRecord))
        wrap.zone = dnsRecord.zone
        return wrap
    }

    fun updateDnsRecord(updatedDnsRecord: UpdateDnsRecord): Any {
        return wrap(cloudflare.updateDnsRecord(xAuthEmail,
                                               xAuthKey,
                                               updatedDnsRecord.zone.id,
                                               updatedDnsRecord.id,
                                               updatedDnsRecord
                                              )
                   )
    }

    fun deleteDnsRecord(dnsRecord: DnsRecord): DeleteDnsRecord {
        return wrap(cloudflare.deleteDnsRecord(xAuthEmail, xAuthKey, dnsRecord.zone.id, dnsRecord.id))
    }

    fun listAccessRules(): List<AccessRule> {
        return wrap(cloudflare.listAccessRules(xAuthEmail, xAuthKey))
    }

    fun shutdown() {
        // shutdown the http client stuff
        client.dispatcher.cancelAll()
        client.dispatcher.executorService.shutdown()
        client.connectionPool.evictAll()
        client.cache?.close()
    }
}

