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
package dorkbox.api

import dorkbox.api.core.CfResponse
import dorkbox.api.dns.CreateDnsRecord
import dorkbox.api.dns.DeleteDnsRecord
import dorkbox.api.dns.DnsRecord
import dorkbox.api.dns.UpdateDnsRecord
import dorkbox.api.firewall.AccessRule
import dorkbox.api.user.BillingHistory
import dorkbox.api.user.BillingProfile
import dorkbox.api.user.User
import dorkbox.api.zone.RatePlan
import dorkbox.api.zone.Zone
import dorkbox.api.zone.settings.ZoneSetting
import retrofit2.Call
import retrofit2.http.*

interface CloudflareActions {
    /**
     * Gets the User details
     *
     * https://api.cloudflare.com/#user-properties
     */
    @Headers("Content-Type: application/json")
    @GET("user")
    fun getUserDetails(
            @Header("X-Auth-Email") email: String,
            @Header("X-Auth-Key") key: String
                      ): Call<CfResponse<User>>

    /**
     * Gets the user's Billing Profile
     *
     * https://api.cloudflare.com/#user-billing-profile-billing-profile
     */
    @Headers("Content-Type: application/json")
    @GET("user/billing/profile")
    fun getUserBillingProfile(
            @Header("X-Auth-Email") email: String,
            @Header("X-Auth-Key") key: String
                             ): Call<CfResponse<BillingProfile>>

    /**
     * Gets the users Billing History
     *
     * https://api.cloudflare.com/#user-billing-history-billing-history
     */
    @Headers("Content-Type: application/json")
    @GET("user/billing/history")
    fun getUserBillingHistory(
            @Header("X-Auth-Email") email: String,
            @Header("X-Auth-Key") key: String
                             ): Call<CfResponse<BillingHistory>>

    /**
     * Gets the list of Zone's owned by this user
     *
     * https://api.cloudflare.com/#zone-properties
     */
    @Headers("Content-Type: application/json")
    @GET("zones")
    fun listZones(
            @Header("X-Auth-Email") email: String,
            @Header("X-Auth-Key") key: String,
            @QueryMap options: Map<String, String>
                 ): Call<CfResponse<List<Zone>>>

    /**
     * Gets the zone rate plan for the specified zone from the billing service
     *
     * https://api.cloudflare.com/#zone-rate-plan-properties
     */
    @Headers("Content-Type: application/json")
    @GET("zones/{zone_identifier}/available_rate_plans")
    fun getZoneRatePlans(
            @Header("X-Auth-Email") email: String,
            @Header("X-Auth-Key") key: String,
            @Path("zone_identifier") zoneIdentifier: String
                        ): Call<CfResponse<RatePlan>>

    /**
     * Gets the zone settings for the specified zone
     *
     * https://api.cloudflare.com/#zone-settings-properties
     */
    @Headers("Content-Type: application/json")
    @GET("zones/{zone_identifier}/settings")
    fun getZoneSettings(
            @Header("X-Auth-Email") email: String,
            @Header("X-Auth-Key") key: String,
            @Path("zone_identifier") zoneIdentifier: String
                       ): Call<CfResponse<ZoneSetting>>

    /**
     * Lists the DNS records for a specified zone
     *
     * https://api.cloudflare.com/#dns-records-for-a-zone-properties
     */
    @Headers("Content-Type: application/json")
    @GET("zones/{zone_identifier}/dns_records")
    fun listDnsRecords(
            @Header("X-Auth-Email") email: String,
            @Header("X-Auth-Key") key: String,
            @Path("zone_identifier") zoneIdentifier: String
                      ): Call<CfResponse<List<DnsRecord>>>


    /**
     * Creates a new DNS record in the specified zone
     *
     * https://api.cloudflare.com/#dns-records-for-a-zone-create-dns-record
     */
    @Headers("Content-Type: application/json")
    @POST("zones/{zone_identifier}/dns_records")
    fun createDnsRecord(
            @Header("X-Auth-Email") email: String,
            @Header("X-Auth-Key") key: String,
            @Path("zone_identifier") zoneIdentifier: String,
            @Body data: CreateDnsRecord
                       ): Call<CfResponse<DnsRecord>>

    /**
     * Updates a DNS record for the specified zone + dns record
     *
     * https://api.cloudflare.com/#dns-records-for-a-zone-update-dns-record
     */
    @Headers("Content-Type: application/json")
    @PUT("zones/{zone_identifier}/dns_records/{identifier}")
    fun updateDnsRecord(
            @Header("X-Auth-Email") email: String,
            @Header("X-Auth-Key") key: String,
            @Path("zone_identifier") zoneIdentifier: String,
            @Path("identifier") identifier: String,
            @Body data: UpdateDnsRecord
                       ): Call<CfResponse<DnsRecord>>

    /**
     * Deletes a DNS record for the specified zone + dns record
     *
     * https://api.cloudflare.com/#dns-records-for-a-zone-delete-dns-record
     */
    @Headers("Content-Type: application/json")
    @DELETE("zones/{zone_identifier}/dns_records/{identifier}")
    fun deleteDnsRecord(
            @Header("X-Auth-Email") email: String,
            @Header("X-Auth-Key") key: String,
            @Path("zone_identifier") zoneIdentifier: String,
            @Path("identifier") identifier: String
                       ): Call<CfResponse<DeleteDnsRecord>>

    /**
     * Lists the access rules for the firewall.
     *
     * https://api.cloudflare.com/#dns-records-for-a-zone-delete-dns-record
     */
    @Headers("Content-Type: application/json")
    @GET("user/firewall/access_rules/rules")
    fun listAccessRules(
            @Header("X-Auth-Email") email: String,
            @Header("X-Auth-Key") key: String
                       ): Call<CfResponse<List<AccessRule>>>

}
