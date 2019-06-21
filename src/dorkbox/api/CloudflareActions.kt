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
import dorkbox.api.dns.DnsRecord
import dorkbox.api.user.BillingHistory
import dorkbox.api.user.BillingProfile
import dorkbox.api.user.User
import dorkbox.api.zone.RatePlan
import dorkbox.api.zone.Zone
import dorkbox.api.zone.settings.ZoneSetting
import retrofit2.Call
import retrofit2.http.*

interface CloudflareActions {
    @Headers("Content-Type: application/json")
    @GET("user")
    fun getUser(
            @Header("X-Auth-Email") email: String,
            @Header("X-Auth-Key") key: String
               ): Call<CfResponse<User>>

    @Headers("Content-Type: application/json")
    @GET("user/billing/profile")
    fun getUserBillingProfile(
            @Header("X-Auth-Email") email: String,
            @Header("X-Auth-Key") key: String
                             ): Call<CfResponse<BillingProfile>>

    @Headers("Content-Type: application/json")
    @GET("user/billing/history")
    fun getUserBillingHistory(
            @Header("X-Auth-Email") email: String,
            @Header("X-Auth-Key") key: String
                             ): Call<CfResponse<BillingHistory>>

    @Headers("Content-Type: application/json")
    @GET("zones")
    fun listZones(
            @Header("X-Auth-Email") email: String,
            @Header("X-Auth-Key") key: String,
            @QueryMap options: Map<String, String>
                 ): Call<CfResponse<List<Zone>>>


    @Headers("Content-Type: application/json")
    @GET("zones/{zone_identifier}/available_rate_plans")
    fun getZoneRatePlans(
            @Header("X-Auth-Email") email: String,
            @Header("X-Auth-Key") key: String,
            @Path("zone_identifier") zoneIdentifier: String
                        ): Call<CfResponse<RatePlan>>


    @Headers("Content-Type: application/json")
    @GET("zones/{zone_identifier}/settings")
    fun getZoneSettings(
            @Header("X-Auth-Email") email: String,
            @Header("X-Auth-Key") key: String,
            @Path("zone_identifier") zoneIdentifier: String
                       ): Call<CfResponse<ZoneSetting>>

    @Headers("Content-Type: application/json")
    @GET("zones/{zone_identifier}/dns_records")
    fun listDnsRecords(
            @Header("X-Auth-Email") email: String,
            @Header("X-Auth-Key") key: String,
            @Path("zone_identifier") zoneIdentifier: String
                      ): Call<CfResponse<List<DnsRecord>>>

}
