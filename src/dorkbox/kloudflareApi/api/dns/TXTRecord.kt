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
 * Content:
 *
 * Text content for the record. The content must consist of quoted "character strings" (RFC 1035), each with a length of up to 255 bytes.
 *  Strings exceeding this allowed maximum length are automatically split.
 *
 * Learn more at https://www.cloudflare.com/learning/dns/dns-records/dns-txt-record/.
*/
@Serializable
class TXTRecord : DnsRecord(RecordType.TXT)
