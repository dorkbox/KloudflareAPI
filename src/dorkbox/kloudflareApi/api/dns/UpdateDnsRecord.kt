package dorkbox.kloudflareApi.api.dns

import com.squareup.moshi.Json

/**
 * https://api.cloudflare.com/#dns-records-for-a-zone-update-dns-record
 */
class UpdateDnsRecord(dnsRecord: DnsRecord) : CreateDnsRecord(dnsRecord.zone) {

    /**
     * DNS record identifier tag
     */
    @field:[Json(name = "id")]
    var id = dnsRecord.id

    init {
        type = dnsRecord.type
        name = dnsRecord.name
        content = dnsRecord.content
        ttl = dnsRecord.ttl
        priority = 0
        proxied = dnsRecord.proxied
    }
}
