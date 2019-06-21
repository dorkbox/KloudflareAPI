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

import com.squareup.moshi.FromJson
import com.squareup.moshi.JsonQualifier
import com.squareup.moshi.ToJson

@Retention(AnnotationRetention.RUNTIME)
@JsonQualifier
annotation class DnsType

/** Converts byte arrays to base64 (so it looks better as a string...) */
internal class DnsRecordTypeAdapter {
    @ToJson
    fun toJson(@DnsType recordType: RecordType): String {
        return recordType.name
    }

    @FromJson
    @DnsType
    fun fromJson(recordType: String): RecordType {
       return RecordType.valueOf(recordType)
    }
}
