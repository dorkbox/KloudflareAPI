/*
 * Copyright 2024 dorkbox, llc
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

/**
 * https://api.cloudflare.com/#dns-records-for-a-zone-delete-dns-record
 */
@JsonClass(generateAdapter = true)
open class DeleteDnsRecord {
    /**
     * DNS record identifier tag
     */
    @field:[Json(name = "id")]
    var id = ""

    override fun toString(): String {
        return "DeleteDnsRecord(id=$id)"
    }
}
