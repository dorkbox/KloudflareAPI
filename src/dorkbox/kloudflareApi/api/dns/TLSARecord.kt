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

import kotlinx.serialization.Serializable

/**
 * https://api.cloudflare.com/#dns-records-for-a-zone-properties
 * Content: Formatted TLSA content. See 'data' to set TLSA properties.
 */
@Serializable
class TLSARecord : DnsRecord(RecordType.TLSA) {

    /**
     * Components of a TLSA record.
     */
    var data: TLSAData? = null

    override fun equals(other: Any?): Boolean {
        if (!super.equals(other)) return false
        if (other !is TLSARecord) return false

        if (data != other.data) return false
        return true
    }

    override fun hashCode(): Int {
        var result = super.hashCode()
        result = 31 * result + (data?.hashCode() ?: 0)
        return result
    }
}
