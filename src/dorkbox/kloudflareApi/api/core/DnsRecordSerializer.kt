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

package dorkbox.kloudflareApi.api.core

import dorkbox.kloudflareApi.api.dns.*
import kotlinx.serialization.InternalSerializationApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.*
import kotlinx.serialization.serializer

object DnsRecordSerializer : KSerializer<DnsRecord> {
    override val descriptor: SerialDescriptor
        get() = PrimitiveSerialDescriptor("dorkbox.kloudflareApi.api.dns.DnsRecordSerializer", PrimitiveKind.STRING)

    override fun serialize(encoder: Encoder, value: DnsRecord) {
//        encoder.encodeString(value.name)
    }

    @OptIn(InternalSerializationApi::class)
    override fun deserialize(decoder: Decoder): DnsRecord {
        decoder as JsonDecoder

        val jsonElement = decoder.decodeJsonElement()
        val jsonObject = decoder.json.decodeFromJsonElement(JsonElement.serializer(), jsonElement) as JsonObject

        val recordType = jsonObject.jsonObject["type"]!!.jsonPrimitive.content.let { RecordType.valueOf(it) }
        return when (recordType) {
            RecordType.A          -> decoder.json.decodeFromJsonElement(ARecord::class.serializer(), jsonObject)
            RecordType.AAAA       -> decoder.json.decodeFromJsonElement(AAAARecord::class.serializer(), jsonObject)
            RecordType.CNAME      -> decoder.json.decodeFromJsonElement(CNAMERecord::class.serializer(), jsonObject)
            RecordType.TXT        -> decoder.json.decodeFromJsonElement(TXTRecord::class.serializer(), jsonObject)
            RecordType.SRV        -> decoder.json.decodeFromJsonElement(SRVRecord::class.serializer(), jsonObject)
            RecordType.LOC        -> decoder.json.decodeFromJsonElement(LOCRecord::class.serializer(), jsonObject)
            RecordType.MX         -> decoder.json.decodeFromJsonElement(MXRecord::class.serializer(), jsonObject)
            RecordType.NS         -> decoder.json.decodeFromJsonElement(NSRecord::class.serializer(), jsonObject)
            RecordType.CERT       -> decoder.json.decodeFromJsonElement(CERTRecord::class.serializer(), jsonObject)
            RecordType.DNSKEY     -> decoder.json.decodeFromJsonElement(DNSKEYRecord::class.serializer(), jsonObject)
            RecordType.DS         -> decoder.json.decodeFromJsonElement(DSRecord::class.serializer(), jsonObject)
            RecordType.NAPTR      -> decoder.json.decodeFromJsonElement(NAPTRecord::class.serializer(), jsonObject)
            RecordType.SMIMEA     -> decoder.json.decodeFromJsonElement(SMIMEARecord::class.serializer(), jsonObject)
            RecordType.SSHFP      -> decoder.json.decodeFromJsonElement(SSHFPRecord::class.serializer(), jsonObject)
            RecordType.TLSA       -> decoder.json.decodeFromJsonElement(TLSARecord::class.serializer(), jsonObject)
            RecordType.URI        -> decoder.json.decodeFromJsonElement(URIRecord::class.serializer(), jsonObject)
            RecordType.CAA        -> decoder.json.decodeFromJsonElement(CAARecord::class.serializer(), jsonObject)
            RecordType.OPENPGPKEY -> decoder.json.decodeFromJsonElement(Openpgpkey::class.serializer(), jsonObject)
            RecordType.HTTPS      -> decoder.json.decodeFromJsonElement(HTTPSRecord::class.serializer(), jsonObject)
            RecordType.PTR        -> decoder.json.decodeFromJsonElement(PTRRecord::class.serializer(), jsonObject)
            RecordType.SVCB       -> decoder.json.decodeFromJsonElement(SVCBRecord::class.serializer(), jsonObject)
        }
    }
}
