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
@file:OptIn(ExperimentalSerializationApi::class)

package dorkbox.kloudflareApi.api.dns

import dorkbox.kloudflareApi.api.core.DnsRecordTypeSerializer
import dorkbox.kloudflareApi.api.core.LocalDateTimeSerializer
import dorkbox.kloudflareApi.api.zone.Zone
import dorkbox.kloudflareApi.cf
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.serialization.*
import kotlinx.serialization.json.JsonClassDiscriminator
import java.time.LocalDateTime

/**
 * https://api.cloudflare.com/#dns-records-for-a-zone-properties
 */
@Serializable
@JsonClassDiscriminator("a_type")
sealed class DnsRecord(
    /**
     * Record type
     */
    @Required
    @Serializable(DnsRecordTypeSerializer::class)
    val type: RecordType
) {
    /**
     * DNS record identifier tag
     */
    val id: String = ""

    /**
     * Comments or notes about the DNS record. This field has no effect on DNS responses.
     */
    var comment: String? = null

    /**
     * Content.
     */
    var content: String? = null

    /**
     * DNS record name
     */
    var name: String? = null

    /**
     * Whether the record is receiving the performance and security benefits of Cloudflare
     */
    var proxied: Boolean? = null

    /**
     * DNS record settings for IPv4/6
     */
    var settings: Settings? = null

    /**
     * Custom tags for the DNS record. This field has no effect on DNS responses.
     */
    var tags: Array<String>? = null

    /**
     * Time To Live (TTL) of the DNS record in seconds. Setting to 1 means 'automatic'. Value must be between 60 and 86400, with the minimum reduced to 30 for Enterprise zones.
     */
    var ttl: Int = 1






    /**
     * When the record comment was last modified
     */
    @SerialName("comment_modified_on")
    @Serializable(LocalDateTimeSerializer::class)
    val commentModifiedOn: LocalDateTime? = null

    /**
     * When the record tags were last modified
     */
    @SerialName("tags_modified_on")
    @Serializable(LocalDateTimeSerializer::class)
    val tagsModifiedOn: LocalDateTime? = null

    /**
     * When the record was created
     */
    @SerialName("created_on")
    @Serializable(LocalDateTimeSerializer::class)
    val createdOn: LocalDateTime? = null


    /**
     * When the record was last modified
     */
    @SerialName("modified_on")
    @Serializable(LocalDateTimeSerializer::class)
    val modifiedOn: LocalDateTime? = null

    /**
     * Whether the record can be proxied by Cloudflare or not
     */
    var proxiable: Boolean = false

    /**
     * Extra Cloudflare-specific information about the record
     */
    val meta: Meta = Meta()



    @Transient
    lateinit var ktor: HttpClient

    @Transient
    lateinit var zone: Zone



    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null) return false
        if (other !is DnsRecord) return false

        if (type != other.type) return false
        if (id != other.id) return false
        if (comment != other.comment) return false
        if (content != other.content) return false
        if (name != other.name) return false
        if (proxied != other.proxied) return false
        if (settings != other.settings) return false
        if (!tags.contentEquals(other.tags)) return false
        if (ttl != other.ttl) return false

        if (commentModifiedOn?.equals(other.commentModifiedOn) == false) return false
        if (tagsModifiedOn?.equals(other.tagsModifiedOn) == false) return false
        if (createdOn?.equals(other.createdOn) == false) return false
        if (modifiedOn?.equals(other.modifiedOn) == false) return false
        if (proxiable != other.proxiable) return false
        if (meta != other.meta) return false

        if (zone != other.zone) return false

        return true
    }

    override fun hashCode(): Int {
        var result = proxied.hashCode()
        result = 31 * result + ttl
        result = 31 * result + proxiable.hashCode()
        result = 31 * result + id.hashCode()
        result = 31 * result + comment.hashCode()
        result = 31 * result + content.hashCode()
        result = 31 * result + name.hashCode()
        result = 31 * result + settings.hashCode()
        result = 31 * result + tags.contentHashCode()
        result = 31 * result + type.hashCode()
        result = 31 * result + (commentModifiedOn?.hashCode() ?: 0)
        result = 31 * result + (tagsModifiedOn?.hashCode() ?: 0)
        result = 31 * result + createdOn.hashCode()
        result = 31 * result + meta.hashCode()
        result = 31 * result + modifiedOn.hashCode()
        return result
    }


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

    override fun toString(): String {
        return "DnsRecord(" +
                "\n\t type=$type" +
                "\n\t id='$id'" +
                "\n\t comment=$comment" +
                "\n\t content=$content" +
                "\n\t name=$name" +
                "\n\t proxied=$proxied" +
                "\n\t settings=$settings" +
                "\n\t tags=${tags.contentToString()}" +
                "\n\t ttl=$ttl" +
                "\n\t commentModifiedOn=$commentModifiedOn" +
                "\n\t tagsModifiedOn=$tagsModifiedOn" +
                "\n\t createdOn=$createdOn" +
                "\n\t modifiedOn=$modifiedOn" +
                "\n\t proxiable=$proxiable" +
                "\n\t meta=$meta" +
                "\n\t zone=${zone.toStringIndent("\t")})"
    }
}
