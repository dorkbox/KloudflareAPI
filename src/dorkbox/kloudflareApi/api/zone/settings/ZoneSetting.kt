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
package dorkbox.kloudflareApi.api.zone.settings

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import dorkbox.kloudflareApi.api.core.ISO8601
import java.time.LocalDateTime

/**
 * Always Online Mode
 */
@JsonClass(generateAdapter = true)
class AlwaysOnline : ZoneSetting() {
    /**
     * Value of the zone setting
     * on, off
     */
    @field:[Json(name = "value")]
    var value = "on"

    override fun toString(): String {
        return "AlwaysOnline(value='$value')" + super.toString()
    }
}

/**
 * Advanced DDoS Protection
 */
@JsonClass(generateAdapter = true)
class AdvancedDDos : ZoneSetting() {
    /**
     * Value of the zone setting
     * notes: Defaults to on for Business+ plans
     * on, off
     */
    @field:[Json(name = "value")]
    var value = "off"

    override fun toString(): String {
        return "AdvancedDDos(value='$value')" + super.toString()
    }
}

/**
 * Brotli Compression
 */
@JsonClass(generateAdapter = true)
class Brotli : ZoneSetting() {
    /**
     * Value of the zone setting
     * on, off
     */
    @field:[Json(name = "value")]
    var value = "on"

    override fun toString(): String {
        return "Brotli(value='$value')" + super.toString()
    }
}

/**
 * Browser Cache TTL
 */
@JsonClass(generateAdapter = true)
class BrowserCacheTtl : ZoneSetting() {
    /**
     * Value of the zone setting
     *
     * valid values: 30, 60, 300, 1200, 1800, 3600, 7200, 10800, 14400, 18000, 28800, 43200, 57600, 72000, 86400,
     * 172800, 259200, 345600, 432000, 691200, 1382400, 2073600, 2678400, 5356800, 16070400, 31536000
     *
     * notes: The minimum TTL available depends on the plan level of the zone. (Enterprise = 30, Business = 1800, Pro = 1800, Free = 1800)
     */
    @field:[Json(name = "value")]
    var value = 14400

    override fun toString(): String {
        return "BrowserCacheTtl(value=$value)" + super.toString()
    }
}

/**
 * Browser Check
 */
@JsonClass(generateAdapter = true)
class BrowserCheck : ZoneSetting() {
    /**
     * Value of the zone setting
     * on, off
     */
    @field:[Json(name = "value")]
    var value = "on"

    override fun toString(): String {
        return "BrowserCheck(value='$value')" + super.toString()
    }
}


/**
 * Cloudflare CNAME Flattening
 */
@JsonClass(generateAdapter = true)
class FlattenAtRoot: ZoneSetting() {
    /**
     * Value of the zone setting
     * flatten_at_root, flatten_all
     */
    @field:[Json(name = "value")]
    var value = "flatten_at_root"

    override fun toString(): String {
        return "FlattenAtRoot(value='$value')" + super.toString()
    }
}


/**
 * Cloudflare Cache Level
 */
@JsonClass(generateAdapter = true)
class CacheLevel: ZoneSetting() {
    /**
     * Value of the zone setting
     * aggressive, basic, simplified
     */
    @field:[Json(name = "value")]
    var value = "aggressive"

    override fun toString(): String {
        return "CacheLevel(value='$value')" + super.toString()
    }
}

/**
 * Challenge Page TTL
 */
@JsonClass(generateAdapter = true)
class ChallengePageTtl: ZoneSetting() {
    /**
     * Value of the zone setting
     *
     * valid values: 300, 900, 1800, 2700, 3600, 7200, 10800, 14400, 28800, 57600, 86400, 604800, 2592000, 31536000
     */
    @field:[Json(name = "value")]
    var value = 1800

    override fun toString(): String {
        return "ChallengePageTtl(value=$value)" + super.toString()
    }
}

/**
 * Development Mode
 */
@JsonClass(generateAdapter = true)
class DevelopmentMode: ZoneSetting() {
    /**
     * Value of the zone setting
     * on, off
     */
    @field:[Json(name = "value")]
    var value = "on"

    /**
     * Value of the zone setting
     *
     * The interval (in seconds) from when development mode expires (positive integer) or last expired (negative integer)
     * for the domain. If development mode has never been enabled, this value is false.
     */
    @field:[Json(name = "time_remaining")]
    var timeRemaining = 0

    override fun toString(): String {
        return "DevelopmentMode(value='$value', timeRemaining=$timeRemaining)" + super.toString()
    }
}

/**
 * Edge Cache TTL
 */
@JsonClass(generateAdapter = true)
class EdgeCacheTtl: ZoneSetting() {
    /**
     * Value of the zone setting
     *
     * valid values: 30, 60, 300, 1200, 1800, 3600, 7200, 10800, 14400, 18000, 28800, 43200, 57600, 72000, 86400, 172800, 259200, 345600, 432000, 518400, 604800
     *
     * notes: The minimum TTL available depends on the plan level of the zone. (Enterprise = 30, Business = 1800, Pro = 3600, Free = 7200)
     */
    @field:[Json(name = "value")]
    var value = 1800

    override fun toString(): String {
        return "EdgeCacheTtl(value=$value)" + super.toString()
    }
}

/**
 * Error Pages On
 */
@JsonClass(generateAdapter = true)
class ErrorPagesOn: ZoneSetting() {
    /**
     * Value of the zone setting
     * on, off
     */
    @field:[Json(name = "value")]
    var value = "on"

    override fun toString(): String {
        return "ErrorPagesOn(value='$value')" + super.toString()
    }
}


/**
 * Get String Sort
 */
@JsonClass(generateAdapter = true)
class StringSort: ZoneSetting() {
    /**
     * Value of the zone setting
     * on, off
     */
    @field:[Json(name = "value")]
    var value = "on"

    override fun toString(): String {
        return "StringSort(value='$value')" + super.toString()
    }
}

/**
 * Email Obfuscation
 */
@JsonClass(generateAdapter = true)
class EmailObfuscation: ZoneSetting() {
    /**
     * Value of the zone setting
     * on, off
     */
    @field:[Json(name = "value")]
    var value = "on"

    override fun toString(): String {
        return "EmailObfuscation(value='$value')" + super.toString()
    }
}

/**
 * Hotlink Protection
 */
@JsonClass(generateAdapter = true)
class HotlinkProtection: ZoneSetting() {
    /**
     * Value of the zone setting
     * on, off
     */
    @field:[Json(name = "value")]
    var value = "on"

    override fun toString(): String {
        return "HotlinkProtection(value='$value')" + super.toString()
    }
}

/**
 * IP Geolocation
 */
@JsonClass(generateAdapter = true)
class IpGeolocation: ZoneSetting() {
    /**
     * Value of the zone setting
     * on, off
     */
    @field:[Json(name = "value")]
    var value = "on"

    override fun toString(): String {
        return "IpGeolocation(value='$value')" + super.toString()
    }
}

/**
 * IPv6
 */
@JsonClass(generateAdapter = true)
class IPv6: ZoneSetting() {
    /**
     * Value of the zone setting
     * on, off
     */
    @field:[Json(name = "value")]
    var value = "on"

    override fun toString(): String {
        return "IPv6(value='$value')" + super.toString()
    }
}

/**
 * WebSockets
 */
@JsonClass(generateAdapter = true)
class Websockets: ZoneSetting() {
    /**
     * Value of the zone setting
     * on, off
     */
    @field:[Json(name = "value")]
    var value = "on"

    override fun toString(): String {
        return "Websockets(value='$value')" + super.toString()
    }
}

/**
 * Toggle SHA1 support
 */
@JsonClass(generateAdapter = true)
class ToggleSha1: ZoneSetting() {
    /**
     * Value of the zone setting
     * on, off
     */
    @field:[Json(name = "value")]
    var value = "on"

    override fun toString(): String {
        return "ToggleSha1(value='$value')" + super.toString()
    }
}

/**
 * TLS1.2 Only
 */
@JsonClass(generateAdapter = true)
class Tls1_2Only: ZoneSetting() {
    /**
     * Value of the zone setting
     * on, off
     */
    @field:[Json(name = "value")]
    var value = "on"

    override fun toString(): String {
        return "Tls1_2Only(value='$value')" + super.toString()
    }
}

/**
 * Auto-Minify Assets
 */
@JsonClass(generateAdapter = true)
class AutoMinify: ZoneSetting() {
    /**
     * Value of the zone setting
     * on, off
     */
    @field:[Json(name = "value")]
    var value = MinifyAssetsSetting()

    override fun toString(): String {
        return "AutoMinify(value=$value)" + super.toString()
    }
}

/**
 * Max Upload
 */
@JsonClass(generateAdapter = true)
class MaxUpload: ZoneSetting() {
    /**
     * Value of the zone setting
     * valid values: 100, 200, 500
     *
     * notes: The size depends on the plan level of the zone. (Enterprise = 500, Business = 200, Pro = 100, Free = 100)
     */
    @field:[Json(name = "value")]
    var value = 100

    override fun toString(): String {
        return "MaxUpload(value=$value)" + super.toString()
    }
}

/**
 * Mobile Redirect
 */
@JsonClass(generateAdapter = true)
class MobileRedirect: ZoneSetting() {
    /**
     * Value of the zone setting
     * on, off
     */
    @field:[Json(name = "value")]
    var value = MobileRedirectSetting()

    override fun toString(): String {
        return "MobileRedirect(value=$value)" + super.toString()
    }
}

/**
 * Mirage Image Optimization
 */
@JsonClass(generateAdapter = true)
class Mirage: ZoneSetting() {
    /**
     * Value of the zone setting
     * on, off
     */
    @field:[Json(name = "value")]
    var value = "on"

    override fun toString(): String {
        return "Mirage(value='$value')" + super.toString()
    }
}

/**
 * Polish Image Optimization
 */
@JsonClass(generateAdapter = true)
class PolishImage: ZoneSetting() {
    /**
     * Value of the zone setting
     *  off, lossless, lossy
     */
    @field:[Json(name = "value")]
    var value = "off"

    override fun toString(): String {
        return "PolishImage(value='$value')" + super.toString()
    }
}

/**
 * Polish WebP Conversion
 */
@JsonClass(generateAdapter = true)
class PolishWebP: ZoneSetting() {
    /**
     * Value of the zone setting
     * on, off
     */
    @field:[Json(name = "value")]
    var value = "on"

    override fun toString(): String {
        return "PolishWebP(value='$value')" + super.toString()
    }
}

/**
 * Prefetch Preload
 */
@JsonClass(generateAdapter = true)
class PrefetchPreload: ZoneSetting() {
    /**
     * Value of the zone setting
     * on, off
     */
    @field:[Json(name = "value")]
    var value = "on"

    override fun toString(): String {
        return "PrefetchPreload(value='$value')" + super.toString()
    }
}

/**
 * Privacy Pass
 */
@JsonClass(generateAdapter = true)
class PrivatePass: ZoneSetting() {
    /**
     * Value of the zone setting
     * on, off
     */
    @field:[Json(name = "value")]
    var value = "on"

    override fun toString(): String {
        return "PrivatePass(value='$value')" + super.toString()
    }
}

/**
 * Response Buffering
 */
@JsonClass(generateAdapter = true)
class ReponseBuffering: ZoneSetting() {
    /**
     * Value of the zone setting
     * on, off
     */
    @field:[Json(name = "value")]
    var value = "on"

    override fun toString(): String {
        return "ReponseBuffering(value='$value')" + super.toString()
    }
}

/**
 * Rocket Loader
 */
@JsonClass(generateAdapter = true)
class RocketLoader: ZoneSetting() {
    /**
     * Value of the zone setting
     * on, off
     */
    @field:[Json(name = "value")]
    var value = "on"

    override fun toString(): String {
        return "RocketLoader(value='$value')" + super.toString()
    }
}

/**
 * Security Header
 */
@JsonClass(generateAdapter = true)
class SecurityHeader: ZoneSetting() {
    /**
     * Current value of the zone setting
     */
    @field:[Json(name = "value")]
    var value = SecurityHeadingSetting()

    override fun toString(): String {
        return "SecurityHeader(value=$value)" + super.toString()
    }
}

/**
 * Security Level
 */
@JsonClass(generateAdapter = true)
class SecurityLevel: ZoneSetting() {
    /**
     * Value of the zone setting
     * on, off
     */
    @field:[Json(name = "value")]
    var value = SecurityHeadingSetting()

    override fun toString(): String {
        return "SecurityLevel(value=$value)" + super.toString()
    }
}

/**
 * Server Side Exclude
 */
@JsonClass(generateAdapter = true)
class ServerSideExclude: ZoneSetting() {
    /**
     * Value of the zone setting
     * on, off
     */
    @field:[Json(name = "value")]
    var value = "on"

    override fun toString(): String {
        return "ServerSideExclude(value='$value')" + super.toString()
    }
}

/**
 * SSL
 */
@JsonClass(generateAdapter = true)
class SSL: ZoneSetting() {
    /**
     * Value of the zone setting
     * off, flexible, full, strict
     *
     * notes: Depends on the zone's plan level
     */
    @field:[Json(name = "value")]
    var value = "off"

    override fun toString(): String {
        return "SSL(value='$value')" + super.toString()
    }
}

/**
 * TLS Client Authentication
 */
@JsonClass(generateAdapter = true)
class TlsClientAuth: ZoneSetting() {
    /**
     * Value of the zone setting
     * off, flexible, full, strict
     *
     * notes: Depends on the zone's plan level
     */
    @field:[Json(name = "value")]
    var value = "off"

    override fun toString(): String {
        return "TlsClientAuth(value='$value')" + super.toString()
    }
}

/**
 * True Client IP Header
 */
@JsonClass(generateAdapter = true)
class TrueClientIPHeader: ZoneSetting() {
    /**
     * Value of the zone setting
     * off, flexible, full, strict
     *
     * notes: Depends on the zone's plan level
     */
    @field:[Json(name = "value")]
    var value = "off"

    override fun toString(): String {
        return "TrueClientIPHeader(value='$value')" + super.toString()
    }
}

/**
 * Web Application Firewall
 */
@JsonClass(generateAdapter = true)
class WebApplicationFirewall: ZoneSetting() {
    /**
     * Value of the zone setting
     * on, off
     */
    @field:[Json(name = "value")]
    var value = "on"

    override fun toString(): String {
        return "WebApplicationFirewall(value='$value')" + super.toString()
    }
}


/**
 * Zone Minimum TLS Version Value
 */
@JsonClass(generateAdapter = true)
class ZoneMinimumTLSVersionValue: ZoneSetting() {
    /**
     * Value of the zone setting
     * 1.0, 1.1, 1.2, 1.3
     */
    @field:[Json(name = "value")]
    var value = 1.0

    override fun toString(): String {
        return "ZoneMinimumTLSVersionValue(value=$value)" + super.toString()
    }
}


/**
 * Zone Enable TLS 1.3
 */
@JsonClass(generateAdapter = true)
class ZoneEnableTLS1_3: ZoneSetting() {
    /**
     * Value of the zone setting
     * on, off, zrt
     */
    @field:[Json(name = "value")]
    var value = "on"

    override fun toString(): String {
        return "ZoneEnableTLS1_3(value='$value')" + super.toString()
    }
}

/**
 * Zone Enable Opportunistic Encryption
 */
@JsonClass(generateAdapter = true)
class ZoneEnableOpportunisticEncryption : ZoneSetting() {
    /**
     * Value of the zone setting
     * on, off, zrt
     */
    @field:[Json(name = "value")]
    var value = "on"

    override fun toString(): String {
        return "ZoneEnableOpportunisticEncryption(value='$value')" + super.toString()
    }
}

/**
 * Zone Enable Automatic HTTPS Rewrites
 */
@JsonClass(generateAdapter = true)
class ZoneEnableAutomaticHTTPSRewrites : ZoneSetting() {
    /**
     * Value of the zone setting
     * on, off, zrt
     */
    @field:[Json(name = "value")]
    var value = "on"

    override fun toString(): String {
        return "ZoneEnableAutomaticHTTPSRewrites(value='$value')" + super.toString()
    }
}

/**
 * HTTP2
 */
@JsonClass(generateAdapter = true)
class HTTP2 : ZoneSetting() {
    /**
     * Value of the zone setting
     * on, off, zrt
     */
    @field:[Json(name = "value")]
    var value = "on"

    override fun toString(): String {
        return "HTTP2(value='$value')" + super.toString()
    }
}

/**
 * Pseudo IPv4 Value
 */
@JsonClass(generateAdapter = true)
class PseudoIPv4 : ZoneSetting() {
    /**
     * Value of the zone setting
     * off, add_header, overwrite_header
     */
    @field:[Json(name = "value")]
    var value = "on"

    override fun toString(): String {
        return "PseudoIPv4(value='$value')" + super.toString()
    }
}

/**
 * Zone Enable Always Use HTTPS
 */
@JsonClass(generateAdapter = true)
class ZoneEnableAlwaysUseHTTPS : ZoneSetting() {
    /**
     * Value of the zone setting
     * on, off
     */
    @field:[Json(name = "value")]
    var value = "on"

    override fun toString(): String {
        return "ZoneEnableAlwaysUseHTTPS(value='$value')" + super.toString()
    }
}

/**
 * Zone Enable Onion Routing
 */
@JsonClass(generateAdapter = true)
class ZoneEnableOnionRouting : ZoneSetting() {
    /**
     * Value of the zone setting
     * on, off
     */
    @field:[Json(name = "value")]
    var value = "on"

    override fun toString(): String {
        return "ZoneEnableOnionRouting(value='$value')" + super.toString()
    }
}

/**
 * Image Resizing
 */
@JsonClass(generateAdapter = true)
class ImageResizing : ZoneSetting() {
    /**
     * Value of the zone setting
     *on, off
     */
    @field:[Json(name = "value")]
    var value = "on"

    override fun toString(): String {
        return "ImageResizing(value='$value')" + super.toString()
    }
}

/**
 * HTTP/2 Edge Prioritization
 */
@JsonClass(generateAdapter = true)
class HTTP2EdgePrioritization : ZoneSetting() {
    /**
     * Value of the zone setting
     * on, off, custom
     */
    @field:[Json(name = "value")]
    var value = "on"

    override fun toString(): String {
        return "HTTP2EdgePrioritization(value='$value')" + super.toString()
    }
}



/**
 * https://api.cloudflare.com/#zone-settings-properties
 */
@JsonClass(generateAdapter = true)
open class ZoneSetting {

    /**
     * ID of the zone setting
     *
     * always_online, advanced_ddos, brotli, browser_cache_ttl, browser_check, flatten_at_root, cache_level, challenge_ttl,
     * development_mode, edge_cache_ttl, origin_error_page_pass_thru, sort_query_string_for_cache, email_obfuscation,
     * hotlink_protection, ip_geolocation, ipv6, websockets, sha1_support, tls_1_2_only, minify, max_upload, mobile_redirect,
     * mirage, polish, webp, prefetch_preload, privacy_pass, response_buffering, rocket_loader, security_header, security_level,
     * server_side_exclude, ssl, tls_client_auth, true_client_ip_header, waf, min_tls_version, tls_1_3, opportunistic_encryption,
     * automatic_https_rewrites, http2, , pseudo_ipv4, always_use_https, opportunistic_onion, image_resizing, h2_prioritization
     */
    @field:[Json(name = "id")]
    var id = ""

    /**
     * Whether or not this setting can be modified for this zone (based on your Cloudflare plan level)
     */
    @field:[Json(name = "editable")]
    var editable = true

    /**
     * last time this setting was modified
     */
    @field:[Json(name = "modified_on") ISO8601]
    var modifiedOn: LocalDateTime? = null

    override fun toString(): String {
        return "ZoneSetting(id='$id', editable=$editable, modifiedOn=$modifiedOn)"
    }
}
