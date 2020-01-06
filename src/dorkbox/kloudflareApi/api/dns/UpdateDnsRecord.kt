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
 * https://api.cloudflare.com/#dns-records-for-a-zone-update-dns-record
 *
 * NOTE: 'dnsRecord' is not part of the Cloudflare API
 *
 *   It is used to associate this dns record with it's zone and id.
 *   A default value is required by code generation.
 */
@JsonClass(generateAdapter = true)
class UpdateDnsRecord(@Transient val dnsRecord: DnsRecord = DnsRecord()) : CreateDnsRecord(dnsRecord.zone) {

    /**
     * DNS record identifier tag
     */
    @field:[Json(name = "id")]
    var id = dnsRecord.id

    init {
        type = dnsRecord.type
        name = dnsRecord.name
        content = dnsRecord.content
        ttl = dnsRecord.ttl
        priority = 0
        proxied = dnsRecord.proxied
    }
}
