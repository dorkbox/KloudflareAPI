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
package dorkbox.kloudflareApi.api.core

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class ResultInfo {
    @field:[Json(name = "page")]
    var page = 1

    @field:[Json(name = "per_page")]
    var perPage = 20

    @field:[Json(name = "total_pages")]
    var totalPages = 1

    @field:[Json(name = "count")]
    var count = 1

    @field:[Json(name = "total_count")]
    var totalCount = 200

    override fun toString(): String {
        return "ResultInfo(page=$page, perPage=$perPage, count=$count, totalCount=$totalCount)"
    }
}
