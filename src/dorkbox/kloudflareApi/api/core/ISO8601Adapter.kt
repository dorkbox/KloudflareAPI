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

import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

/** Converts byte arrays to base64 (so it looks better as a string...) */
internal class ISO8601Adapter {
    companion object {
        // 2014-05-28T18:46:18.764425Z
        private val format: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    }


    @ToJson
    fun toJson(date: LocalDateTime): String {
        return format.format(date)
    }

    @FromJson
    fun fromJson(dateString: String): LocalDateTime {
        return try {
            return LocalDateTime.parse(dateString, format)
        }
        catch (ignored: Exception) {
            // if there is an error, return the epoc
            LocalDateTime.now()
        }
    }
}
