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
package dorkbox.kloudflareApi.api.zone

import dorkbox.kloudflareApi.api.core.LocalDateTimeSerializer
import dorkbox.kloudflareApi.api.dns.DnsRecord
import dorkbox.kloudflareApi.api.zone.settings.ZoneSetting
import dorkbox.kloudflareApi.cf
import dorkbox.kloudflareApi.cfList
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient
import java.time.LocalDateTime

/**
 * https://api.cloudflare.com/#zone-properties
 */
@Serializable
data class Zone(
    val id: String = "",

    /**
     * The domain name
     */
    val name: String = "example.com",

    /**
     * The interval (in seconds) from when development mode expires (positive integer) or last expired (negative integer) for the domain. If development mode has never been enabled, this value is 0.
     */
    @SerialName("development_mode")
    val developmentMode: Int = 0,

    /**
     * Original name servers before moving to Cloudflare
     */
    @SerialName("original_name_servers")
    val originalNameServers: List<String>? = null,

    /**
     * Registrar for the domain at the time of switching to Cloudflare
     */
    @SerialName("original_registrar")
    val originalRegistrar: String? = null,

    /**
     * DNS host at the time of switching to Cloudflare
     */
    @SerialName("original_dnshost")
    val originalDnsHost: String? = null,

    /**
     * When the zone was created
     */
    @SerialName("created_on")
    @Serializable(LocalDateTimeSerializer::class)
    val createdOn: LocalDateTime? = null,

    /**
     * When the zone was last modified
     */
    @SerialName("modified_on")
    @Serializable(LocalDateTimeSerializer::class)
    val modifiedOn: LocalDateTime? = null,

    /**
     * Cloudflare-assigned name servers. This is only populated for zones that use Cloudflare DNS
     */
    @SerialName("name_servers")
    val nameServers: List<String> = listOf<String>(),

    /**
     * Information about the owner of the zone
     */
    val owner: Owner = Owner(),

    /**
     * Information about the account the zone belongs to
     */
    val account: Account = Account(),

    /**
     * Available permissions on the zone for the current user requesting the item
     */
    val permissions: List<String> = listOf<String>(),

    /**
     * A zone plan
     */
    val plan: Plan = Plan(),

    /**
     * A zone plan
     */
    @SerialName("plan_pending")
    val planPending: PlanPending = PlanPending(),

    /**
     * Status of the zone
     * active, pending, initializing, moved, deleted, deactivated
     */
    private val status: String = "active",

    /**
     * The last time proof of ownership was detected and the zone was made active
     */
    @SerialName("activated_on")
    @Serializable(LocalDateTimeSerializer::class)
    val activatedOn: LocalDateTime? = null,

    /**
     * Indicates if the zone is only using Cloudflare DNS services. A true value means the zone will not receive security or performance benefits.
     */
    val paused: Boolean = false,

    /**
     * A full zone implies that DNS is hosted with Cloudflare. A partial zone is typically a partner-hosted zone or a CNAME setup.
     * full, partial
     */
    val type: String = "full",
) {
    @Transient
    lateinit var ktor: HttpClient

    /**
     * Gets the zone rate plan for the specified zone from the billing service
     *
     * https://api.cloudflare.com/#zone-rate-plan-properties
     */
    suspend fun ratePlans(): List<RatePlan> {
        return ktor.cfList<RatePlan>(HttpMethod.Get,"zones/$id/available_rate_plans")
    }

    /**
     * Gets the zone settings for the specified zone
     *
     * https://api.cloudflare.com/#zone-settings-properties
     */
    suspend fun settings(): ZoneSetting {
        return ktor.cf(HttpMethod.Get, "zones/$id/settings")
    }

    /**
     * Lists the DNS records for a specified zone
     *
     * https://api.cloudflare.com/#dns-records-for-a-zone-properties
     */
    suspend fun dnsRecords(): List<DnsRecord> {
        val records = ktor.cfList<DnsRecord>(HttpMethod.Get, "zones/$id/dns_records") {
            parameter(key = "per_page", value = 20)  // not too many at once!
        }

        records.forEach { record ->
            record.ktor = ktor
            record.zone = this
        }

        return records
    }

    /**
     * Creates a new DNS record in the specified zone
     *
     * https://api.cloudflare.com/#dns-records-for-a-zone-create-dns-record
     */
    suspend inline fun <reified T:DnsRecord> createDnsRecord(newRecord: T): DnsRecord {
        val record = ktor.cf<DnsRecord>(HttpMethod.Post,"zones/$id/dns_records") {
            setBody(newRecord)
        }

        record.ktor = ktor
        record.zone = this

        return record
    }
}
