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

import dorkbox.kloudflareApi.api.core.DnsRecordTypeSerializer
import dorkbox.kloudflareApi.api.core.LocalDateTimeSerializer
import dorkbox.kloudflareApi.api.zone.Zone
import io.ktor.client.*
import kotlinx.serialization.Required
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient
import java.time.LocalDateTime

/**
 * https://api.cloudflare.com/#dns-records-for-a-zone-properties
 */
@Serializable
data class CAARecord(
    /**
     * DNS record identifier tag
     */
    override val id: String = "",

    /**
     * Comments or notes about the DNS record. This field has no effect on DNS responses.
     */
    override var comment: String? = null,

    /**
     * Formatted CAA content. See 'data' to set CAA properties.
     *
     */
    override var content: String? = null,

    /**
     * Components of a CAA record.
     */
    var data: CAAData? = null,

    /**
     * DNS record name
     */
    override var name: String? = null,

    /**
     * Whether the record is receiving the performance and security benefits of Cloudflare
     */
    override var proxied: Boolean? = null,

    /**
     * DNS record settings for IPv4/6
     */
    override var settings: Settings? = null,

    /**
     * Custom tags for the DNS record. This field has no effect on DNS responses.
     */
    override var tags: Array<String>? = null,

    /**
     * Time To Live (TTL) of the DNS record in seconds. Setting to 1 means 'automatic'. Value must be between 60 and 86400, with the minimum reduced to 30 for Enterprise zones.
     */
    override var ttl: Int? = null,

    /**
     * Record type
     */
    @Required
    @Serializable(DnsRecordTypeSerializer::class)
    override var type: RecordType = RecordType.CAA,




    /**
     * When the record comment was last modified
     */
    @SerialName("comment_modified_on")
    @Serializable(LocalDateTimeSerializer::class)
    override val commentModifiedOn: LocalDateTime? = null,

    /**
     * When the record tags were last modified
     */
    @SerialName("tags_modified_on")
    @Serializable(LocalDateTimeSerializer::class)
    override val tagsModifiedOn: LocalDateTime? = null,

    /**
     * When the record was created
     */
    @SerialName("created_on")
    @Serializable(LocalDateTimeSerializer::class)
    override val createdOn: LocalDateTime = LocalDateTime.now(),

    /**
     * Extra Cloudflare-specific information about the record
     */
    override val meta: Meta = Meta(),

    /**
     * When the record was last modified
     */
    @SerialName("modified_on")
    @Serializable(LocalDateTimeSerializer::class)
    override val modifiedOn: LocalDateTime = LocalDateTime.now(),

    /**
     * Whether the record can be proxied by Cloudflare or not
     */
    override var proxiable: Boolean = false

    ): DnsRecord {

    @Transient
    override lateinit var ktor: HttpClient

    @Transient
    override lateinit var zone: Zone


    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is CAARecord) return false

        if (proxied != other.proxied) return false
        if (ttl != other.ttl) return false
        if (proxiable != other.proxiable) return false
        if (id != other.id) return false
        if (comment != other.comment) return false
        if (content != other.content) return false
        if (data != other.data) return false
        if (name != other.name) return false
        if (settings != other.settings) return false
        if (!tags.contentEquals(other.tags)) return false
        if (type != other.type) return false
        if (commentModifiedOn != other.commentModifiedOn) return false
        if (tagsModifiedOn != other.tagsModifiedOn) return false
        if (createdOn != other.createdOn) return false
        if (meta != other.meta) return false
        if (modifiedOn != other.modifiedOn) return false
        if (ktor != other.ktor) return false
        if (zone != other.zone) return false

        return true
    }

    override fun hashCode(): Int {
        var result = proxied.hashCode()
        result = 31 * result + (ttl ?: 0)
        result = 31 * result + proxiable.hashCode()
        result = 31 * result + id.hashCode()
        result = 31 * result + comment.hashCode()
        result = 31 * result + content.hashCode()
        result = 31 * result + (data?.hashCode() ?: 0)
        result = 31 * result + name.hashCode()
        result = 31 * result + settings.hashCode()
        result = 31 * result + tags.contentHashCode()
        result = 31 * result + type.hashCode()
        result = 31 * result + (commentModifiedOn?.hashCode() ?: 0)
        result = 31 * result + (tagsModifiedOn?.hashCode() ?: 0)
        result = 31 * result + createdOn.hashCode()
        result = 31 * result + meta.hashCode()
        result = 31 * result + modifiedOn.hashCode()
        result = 31 * result + ktor.hashCode()
        result = 31 * result + zone.hashCode()
        return result
    }
}
