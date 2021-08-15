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
import dorkbox.kloudflareApi.api.zone.Zone

/**
 * https://api.cloudflare.com/#dns-records-for-a-zone-create-dns-record
 *
 * NOTE: 'zone' is not part of the Cloudflare API
 *
 *   It is used to associate this dns record with it's zone.
 *   A default value is required by code generation.
 */
@JsonClass(generateAdapter = true)
open class CreateDnsRecord(@Transient val zone: Zone = Zone()) {

    /**
     * Record type
     * A, AAAA, CNAME, TXT, SRV, LOC, MX, NS, SPF, CERT, DNSKEY, DS, NAPTR, SMIMEA, SSHFP, TLSA, URI
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
     * Time to live for DNS record. Value of 1 is 'automatic'
     */
    @field:[Json(name = "ttl")]
    var ttl = 1

    /**
     * Used with some records like MX and SRV to determine priority. If you do not supply a priority for an MX record, a default value of 0 will be set
     */
    @field:[Json(name = "priority")]
    var priority = 0

    /**
     * Whether the record is receiving the performance and security benefits of Cloudflare
     */
    @field:[Json(name = "proxied")]
    var proxied = false

    /**
     * Whether the record is receiving the performance and security benefits of Cloudflare
     */
    @field:[Json(name = "data")]
    var data = mutableMapOf<String, Any>()
}
