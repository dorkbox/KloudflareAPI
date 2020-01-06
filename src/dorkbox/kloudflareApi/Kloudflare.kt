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
package dorkbox.kloudflareApi

import com.squareup.moshi.Moshi
import dorkbox.kloudflareApi.api.CloudflareActions
import dorkbox.kloudflareApi.api.core.*
import dorkbox.kloudflareApi.api.dns.CreateDnsRecord
import dorkbox.kloudflareApi.api.dns.DeleteDnsRecord
import dorkbox.kloudflareApi.api.dns.DnsRecord
import dorkbox.kloudflareApi.api.dns.UpdateDnsRecord
import dorkbox.kloudflareApi.api.firewall.AccessRule
import dorkbox.kloudflareApi.api.user.BillingHistory
import dorkbox.kloudflareApi.api.user.BillingProfile
import dorkbox.kloudflareApi.api.user.User
import dorkbox.kloudflareApi.api.zone.RatePlan
import dorkbox.kloudflareApi.api.zone.Zone
import dorkbox.kloudflareApi.api.zone.settings.ZoneSetting
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.ResponseBody
import org.conscrypt.Conscrypt
import retrofit2.Call
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.io.IOException
import java.security.Security
import java.util.Collections.emptyMap


class Kloudflare(private val xAuthEmail: String, private val xAuthKey: String) {
    companion object {
        private const val API_BASE_URL = "https://api.cloudflare.com/client/v4/"


        init {
            try {
                Security.insertProviderAt(Conscrypt.newProvider(), 1);
            }
            catch (e: Throwable) {
                e.printStackTrace();
            }
        }

        /**
         * Gets the version number.
         */
        fun getVersion(): String {
            return "1.2"
        }
    }

    private val errorConverter: Converter<ResponseBody, CfErrorResponse>
    private val cloudflare: CloudflareActions
    private val client: OkHttpClient

    init {
        // JSON mapping to java classes

        // val interceptor = HttpLoggingInterceptor()
        // interceptor.level = HttpLoggingInterceptor.Level.BODY

        client = OkHttpClient.Builder()
                // .addInterceptor(interceptor)  // this is the raw HTTP interceptor
                .build()

        val moshi = Moshi.Builder()
                .add(ISO8601Adapter())
                .add(DnsRecordTypeAdapter())
                .build()

        val retrofit = Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .client(client)
                .build()

        errorConverter = retrofit.responseBodyConverter(CfErrorResponse::class.java, CfErrorResponse::class.annotations.toTypedArray())
        cloudflare = retrofit.create(CloudflareActions::class.java)
    }

    /**
     * @return the content of an http get to the requested URL
     */
    fun get(url: String): String {
        val request = Request.Builder()
                .url(url)
                .build()

        val response = client.newCall(request).execute()
        return response.body?.string()!!
    }

    @Throws(IOException::class)
    private fun <T> wrap(call: Call<CfResponse<T>>): T {
        val response = call.execute()

        val body = response.body()
        if (response.isSuccessful && body != null && body.success) {
            return body.result!!
        }

        val errorResponse = errorConverter.convert(response.errorBody()!!)
        throw IOException("Call failed: " + errorResponse?.errors?.joinToString { error: Error -> "[${error.code} : ${error.message}]" })
    }

    /**
     * Gets the User details
     *
     * https://api.cloudflare.com/#user-properties
     */
    fun getUserDetails(): User {
        return wrap(cloudflare.getUserDetails(xAuthEmail, xAuthKey))
    }

    /**
     * Gets the user's Billing Profile
     *
     * https://api.cloudflare.com/#user-billing-profile-billing-profile
     */
    fun getUserBillingProfile(): BillingProfile {
        return wrap(cloudflare.getUserBillingProfile(xAuthEmail, xAuthKey))
    }

    /**
     * Gets the users Billing History
     *
     * https://api.cloudflare.com/#user-billing-history-billing-history
     */
    fun getUserBillingHistory(): BillingHistory {
        return wrap(cloudflare.getUserBillingHistory(xAuthEmail, xAuthKey))
    }

    /**
     * Gets the list of Zone's owned by this user
     *
     * https://api.cloudflare.com/#zone-properties
     */
    fun listZones(options: Map<String, String> = emptyMap()): List<Zone> {
        val zones = wrap(cloudflare.listZones(xAuthEmail, xAuthKey, options))
        zones.forEach { zone ->
            // have to assign
            zone.kloudflare = this;

        }

        return zones
    }

    /**
     * Gets the zone rate plan for the specified zone from the billing service
     *
     * https://api.cloudflare.com/#zone-rate-plan-properties
     */
    fun getZoneRatePlans(zone: Zone): RatePlan {
        return wrap(cloudflare.getZoneRatePlans(xAuthEmail, xAuthKey, zone.id))
    }

    /**
     * Gets the zone settings for the specified zone
     *
     * https://api.cloudflare.com/#zone-settings-properties
     */
    fun getZoneSettings(zone: Zone): ZoneSetting {
        return wrap(cloudflare.getZoneSettings(xAuthEmail, xAuthKey, zone.id))
    }

    /**
     * Lists the DNS records for a specified zone
     *
     * https://api.cloudflare.com/#dns-records-for-a-zone-properties
     */
    fun listDnsRecords(zone: Zone): List<DnsRecord> {
        val wrap =
            wrap(cloudflare.listDnsRecords(xAuthEmail, xAuthKey, zone.id))
        wrap.forEach {
            it.zone = zone
        }
        return wrap
    }

    /**
     * Creates a new DNS record in the specified zone
     *
     * https://api.cloudflare.com/#dns-records-for-a-zone-create-dns-record
     */
    fun createDnsRecord(dnsRecord: CreateDnsRecord): DnsRecord {
        val wrap =
            wrap(cloudflare.createDnsRecord(xAuthEmail, xAuthKey, dnsRecord.zone.id, dnsRecord))
        wrap.zone = dnsRecord.zone
        return wrap
    }

    /**
     * Updates a DNS record for the specified zone + dns record
     *
     * https://api.cloudflare.com/#dns-records-for-a-zone-update-dns-record
     */
    fun updateDnsRecord(updatedDnsRecord: UpdateDnsRecord): Any {
        return wrap(cloudflare.updateDnsRecord(xAuthEmail,
                                               xAuthKey,
                                               updatedDnsRecord.zone.id,
                                               updatedDnsRecord.id,
                                               updatedDnsRecord
                                              )
                   )
    }

    /**
     * Deletes a DNS record for the specified zone + dns record
     *
     * https://api.cloudflare.com/#dns-records-for-a-zone-delete-dns-record
     */
    fun deleteDnsRecord(dnsRecord: DnsRecord): DeleteDnsRecord {
        return wrap(cloudflare.deleteDnsRecord(xAuthEmail, xAuthKey, dnsRecord.zone.id, dnsRecord.id))
    }

    /**
     * Lists the access rules for the firewall.
     *
     * https://api.cloudflare.com/#dns-records-for-a-zone-delete-dns-record
     */
    fun listAccessRules(): List<AccessRule> {
        return wrap(cloudflare.listAccessRules(xAuthEmail, xAuthKey))
    }

    /**
     * Shuts down the HTTP executors used. This is necessary if you want to be able to shutdown the JVM
     */
    fun shutdown() {
        // shutdown the http client stuff
        client.dispatcher.cancelAll()
        client.dispatcher.executorService.shutdown()
        client.connectionPool.evictAll()
        client.cache?.close()
    }
}

