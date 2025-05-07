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
package dorkbox.kloudflareApi.api.dns

import dorkbox.kloudflareApi.api.zone.Zone
import dorkbox.kloudflareApi.cf
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient
import java.time.LocalDateTime

/**
 * https://api.cloudflare.com/#dns-records-for-a-zone-properties
 */
@Serializable
sealed interface DnsRecord {
    /**
     * DNS record identifier tag
     */
    val id: String

    /**
     * Comments or notes about the DNS record. This field has no effect on DNS responses.
     */
    var comment: String?

    /**
     * Content.
     */
    var content: String?

    /**
     * DNS record name
     */
    var name: String?

    /**
     * Whether the record is receiving the performance and security benefits of Cloudflare
     */
    var proxied: Boolean?

    /**
     * DNS record settings for IPv4/6
     */
    var settings: Settings?

    /**
     * Custom tags for the DNS record. This field has no effect on DNS responses.
     */
    var tags: Array<String>?

    /**
     * Time To Live (TTL) of the DNS record in seconds. Setting to 1 means 'automatic'. Value must be between 60 and 86400, with the minimum reduced to 30 for Enterprise zones.
     */
    var ttl: Int?

    /**
     * Record type
     */
    var type: RecordType




    /**
     * When the record comment was last modified
     */
    val commentModifiedOn: LocalDateTime?

    /**
     * When the record tags were last modified
     */
    val tagsModifiedOn: LocalDateTime?

    /**
     * When the record was created
     */
    val createdOn: LocalDateTime

    /**
     * Extra Cloudflare-specific information about the record
     */
    val meta: Meta

    /**
     * When the record was last modified
     */
    val modifiedOn: LocalDateTime

    /**
     * Whether the record can be proxied by Cloudflare or not
     */
    var proxiable: Boolean


    @Transient
    var ktor: HttpClient

    @Transient
    var zone: Zone

    fun prettyName(): String {
        return when (val length = name?.length?.minus(zone.name.length)) {
            0    -> "@"
            else ->
                name?.subSequence(0, length?.minus(1) ?: 0) as String // -1 because we don't want the '.'
        }
    }

    /**
     * Updates a DNS record for the specified zone + dns record
     *
     * https://api.cloudflare.com/#dns-records-for-a-zone-update-dns-record
     */
    suspend fun update(): DnsRecord {
        val dnsRecord = ktor.cf<DnsRecord>(HttpMethod.Put, "zones/${zone.id}/dns_records/$id") {
            setBody(this@DnsRecord)
        }

        dnsRecord.ktor = ktor
        dnsRecord.zone = zone

        return dnsRecord
    }

    /**
     * Deletes a DNS record for the specified zone + dns record
     *
     * https://api.cloudflare.com/#dns-records-for-a-zone-delete-dns-record
     */
    suspend fun delete(): String {
        return ktor.cf<DeleteDnsRecord>(HttpMethod.Delete, "zones/${zone.id}/dns_records/$id").id
    }
}
