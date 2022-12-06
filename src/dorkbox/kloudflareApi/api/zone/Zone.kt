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
package dorkbox.kloudflareApi.api.zone

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import dorkbox.kloudflareApi.Kloudflare
import dorkbox.kloudflareApi.api.dns.DnsRecord
import java.time.LocalDateTime

/**
 * https://api.cloudflare.com/#zone-properties
 */
@JsonClass(generateAdapter = true)
class Zone {

    /**
     * NOTE: This is not part of the Cloudflare API
     */
    @Transient
    lateinit var kloudflare: Kloudflare

    /**
     * NOTE: This is not part of the Cloudflare API
     *
     * DNS records that were retrieved from this zone.
     */
    @delegate:Transient
    val dnsRecords: List<DnsRecord> by lazy { kloudflare.listDnsRecords(this) }



    @field:[Json(name = "id")]
    var id: String = ""

    /**
     * The domain name
     */
    @field:[Json(name = "name")]
    var name = "example.com"

    /**
     * The interval (in seconds) from when development mode expires (positive integer) or last expired (negative integer) for the domain. If development mode has never been enabled, this value is 0.
     */
    @field:[Json(name = "development_mode")]
    var developmentMode = 0

    /**
     * Original name servers before moving to Cloudflare
     */
    @field:[Json(name = "original_name_servers")]
    var originalNameServers: List<String>? = null

    /**
     * Registrar for the domain at the time of switching to Cloudflare
     */
    @field:[Json(name = "original_registrar")]
    var originalRegistrar: String? = null

    /**
     * DNS host at the time of switching to Cloudflare
     */
    @field:[Json(name = "original_dnshost")]
    var originalDnshost: String? = null

    /**
     * When the zone was created
     */
    @field:[Json(name = "created_on")]
    var createdOn: LocalDateTime = LocalDateTime.now()

    /**
     * When the zone was last modified
     */
    @field:[Json(name = "modified_on")]
    var modifiedOn: LocalDateTime = LocalDateTime.now()

    /**
     * Cloudflare-assigned name servers. This is only populated for zones that use Cloudflare DNS
     */
    @field:[Json(name = "name_servers")]
    var nameServers = listOf<String>()

    /**
     * Information about the owner of the zone
     */
    @field:[Json(name = "owner")]
    var owner = Owner()

    /**
     * Information about the account the zone belongs to
     */
    @field:[Json(name = "account")]
    var account = Account()

    /**
     * Available permissions on the zone for the current user requesting the item
     */
    @field:[Json(name = "permissions")]
    var permissions = listOf<String>()

    /**
     * A zone plan
     */
    @field:[Json(name = "plan")]
    var plan = Plan()

    /**
     * A zone plan
     */
    @field:[Json(name = "plan_pending")]
    var planPending = PlanPending()

    /**
     * Status of the zone
     * active, pending, initializing, moved, deleted, deactivated
     */
    @field:[Json(name = "status")]
    var status = "active"

    /**
     * The last time proof of ownership was detected and the zone was made active
     */
    @field:[Json(name = "activated_on")]
    var activatedOn: LocalDateTime? = null

    /**
     * Indicates if the zone is only using Cloudflare DNS services. A true value means the zone will not receive security or performance benefits.
     */
    @field:[Json(name = "paused")]
    var paused = false

    /**
     * A full zone implies that DNS is hosted with Cloudflare. A partial zone is typically a partner-hosted zone or a CNAME setup.
     * full, partial
     */
    @field:[Json(name = "type")]
    var type = "full"

    override fun toString(): String {
        return "Zone(id=$id, name='$name', developmentMode=$developmentMode, originalNameServers=$originalNameServers, originalRegistrar=$originalRegistrar, originalDnshost=$originalDnshost, createdOn=$createdOn, modifiedOn=$modifiedOn, nameServers=$nameServers, owner=$owner, account=$account, permissions=$permissions, plan=$plan, planPending=$planPending, status='$status', activatedOn=$activatedOn, paused=$paused, type='$type')"
    }
}
