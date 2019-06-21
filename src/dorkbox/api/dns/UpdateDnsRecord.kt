package dorkbox.api.dns

/**
 * https://api.cloudflare.com/#dns-records-for-a-zone-update-dns-record
 *
 * This is the "same" object as creating a new record. This is a different type in order to prevent confusion and simplify naming conventions
 */
class UpdateDnsRecord : CreateDnsRecord()
