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
package dorkbox.api.dns

import com.squareup.moshi.Json
import dorkbox.api.core.ISO8601
import java.time.LocalDateTime

/**
 * https://api.cloudflare.com/#dns-records-for-a-zone-properties
 */
open class DnsRecord {
    /**
     * DNS record identifier tag
     */
    @field:[Json(name = "id")]
    var id = ""

    /**
     * Record type
     * A, AAAA, CNAME, TXT, SRV, LOC, MX, NS, SPF, CERT, DNSKEY, DS, NAPTR, SMIMEA, SSHFP, TLSA, URI
     */
    @field:[Json(name = "type") DnsType]
    var type = RecordType.A

    /**
     * DNS record name
     */
    @field:[Json(name = "name")]
    var name= ""

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
    var modifiedOn = LocalDateTime.now()

    /**
     * When the record was created
     */
    @field:[Json(name = "created_on") ISO8601]
    var createdOn = LocalDateTime.now()

    @field:[Json(name = "meta")]
    var meta = Meta()

    /**
     * Metadata about the record
     */
    var data: String? = null
}
