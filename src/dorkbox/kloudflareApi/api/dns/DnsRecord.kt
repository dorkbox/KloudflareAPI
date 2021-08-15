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
package dorkbox.kloudflareApi.api.dns

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import dorkbox.kloudflareApi.api.core.ISO8601
import dorkbox.kloudflareApi.api.zone.Zone
import java.time.LocalDateTime

/**
 * https://api.cloudflare.com/#dns-records-for-a-zone-properties
 */
@JsonClass(generateAdapter = true)
open class DnsRecord {
    /**
     * NOTE: This is not part of the Cloudflare API
     *
     * Which zone this DNS record belongs to
     */
    @Transient
    lateinit var zone: Zone


    fun prettyName(): String {
        return when (val length = name.length - zone.name.length) {
            0    -> "@"
            else ->
                name.subSequence(0, length - 1) as String // -1 because we don't want the '.'
        }
    }

    override fun toString(): String {
        return "DnsRecord(type=$type, name='${name}, content='${content}', ttl='${ttl}')"
    }

    /**
     * DNS record identifier tag
     */
    @field:[Json(name = "id")]
    var id = ""

    /**
     * Record type
     * A, AAAA, CNAME, TXT, SRV, LOC, MX, NS, SPF, CERT, DNSKEY, DS, NAPTR, SMIMEA, SSHFP, TLSA, URI, CCA
     */
    @field:[Json(name = "type")]
    var type = RecordType.A

    /**
     * DNS record name
     */
    @field:[Json(name = "name")]
    var name = ""

    /**
     * A valid IPv4 address
     */
    @field:[Json(name = "content")]
    var content = ""

    /**
     * Whether the record can be proxied by Cloudflare or not
     */
    @field:[Json(name = "proxiable")]
    var proxiable = true

    /**
     * Whether the record is receiving the performance and security benefits of Cloudflare
     */
    @field:[Json(name = "proxied")]
    var proxied = false

    /**
     * Time to live for DNS record. Value of 1 is 'automatic'
     */
    @field:[Json(name = "ttl")]
    var ttl = 1

    /**
     * Whether this record can be modified/deleted (true means it's managed by Cloudflare)
     */
    @field:[Json(name = "locked")]
    var locked = false

    /**
     * Zone identifier tag
     */
    @field:[Json(name = "zone_id")]
    var zoneId = ""

    @field:[Json(name = "zone_name")]
    var zoneName = ""

    /**
     * When the record was last modified
     */
    @field:[Json(name = "modified_on") ISO8601]
    var modifiedOn: LocalDateTime = LocalDateTime.now()

    /**
     * When the record was created
     */
    @field:[Json(name = "created_on") ISO8601]
    var createdOn: LocalDateTime = LocalDateTime.now()

    /**
     * Extra Cloudflare-specific information about the record
     */
    @field:[Json(name = "meta")]
    var meta = Meta()

    /**
     * Metadata about the record
     */
    @field:[Json(name = "data")]
    var data = mutableMapOf<String, Any>()
}
