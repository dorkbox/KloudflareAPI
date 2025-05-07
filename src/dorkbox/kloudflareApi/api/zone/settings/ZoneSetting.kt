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
@file:UseSerializers(LocalDateTimeSerializer::class)

package dorkbox.kloudflareApi.api.zone.settings

import dorkbox.kloudflareApi.api.core.LocalDateTimeSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.UseSerializers
import java.time.LocalDateTime



/**
 * Always Online Mode
 */
@Serializable
data class AlwaysOnline(
    /**
     * Value of the zone setting
     * on, off
     */
    val value: String = "on",

    override val id: String = "",
    override val editable: Boolean = true,
    override val modifiedOn: LocalDateTime? = null
) : ZoneSetting


/**
 * Advanced DDoS Protection
 */
@Serializable
data class AdvancedDDos(
    /**
     * Value of the zone setting
     * notes: Defaults to on for Business+ plans
     * on, off
     */
    val value: String = "off",

    override val id: String = "",
    override val editable: Boolean = true,
    override val modifiedOn: LocalDateTime? = null
): ZoneSetting


/**
 * Brotli Compression
 */
@Serializable
data class Brotli(
    /**
     * Value of the zone setting
     * on, off
     */
    val value: String = "on",

    override val id: String = "",
    override val editable: Boolean = true,
    override val modifiedOn: LocalDateTime? = null
): ZoneSetting


/**
 * Browser Cache TTL
 */
@Serializable
data class BrowserCacheTtl(
    /**
     * Value of the zone setting
     *
     * valid values: 30, 60, 300, 1200, 1800, 3600, 7200, 10800, 14400, 18000, 28800, 43200, 57600, 72000, 86400,
     * 172800, 259200, 345600, 432000, 691200, 1382400, 2073600, 2678400, 5356800, 16070400, 31536000
     *
     * notes: The minimum TTL available depends on the plan level of the zone. (Enterprise = 30, Business = 1800, Pro = 1800, Free = 1800)
     */
    val value: Int = 14400,

    override val id: String = "",
    override val editable: Boolean = true,
    override val modifiedOn: LocalDateTime? = null
): ZoneSetting


/**
 * Browser Check
 */
@Serializable
data class BrowserCheck(
    /**
     * Value of the zone setting
     * on, off
     */
    val value: String = "on",

    override val id: String = "",
    override val editable: Boolean = true,
    override val modifiedOn: LocalDateTime? = null
): ZoneSetting


/**
 * Cloudflare CNAME Flattening
 */
@Serializable
data class FlattenAtRoot(
    /**
     * Value of the zone setting
     * flatten_at_root, flatten_all
     */
    val value: String = "flatten_at_root",

    override val id: String = "",
    override val editable: Boolean = true,
    override val modifiedOn: LocalDateTime? = null
): ZoneSetting


/**
 * Cloudflare Cache Level
 */
@Serializable
data class CacheLevel(
    /**
     * Value of the zone setting
     * aggressive, basic, simplified
     */
    val value: String = "aggressive",

    override val id: String = "",
    override val editable: Boolean = true,
    override val modifiedOn: LocalDateTime? = null
): ZoneSetting


/**
 * Challenge Page TTL
 */
@Serializable
data class ChallengePageTtl(
    /**
     * Value of the zone setting
     *
     * valid values: 300, 900, 1800, 2700, 3600, 7200, 10800, 14400, 28800, 57600, 86400, 604800, 2592000, 31536000
     */
    val value: Int = 1800,

    override val id: String = "",
    override val editable: Boolean = true,
    override val modifiedOn: LocalDateTime? = null
): ZoneSetting


/**
 * Development Mode
 */
@Serializable
data class DevelopmentMode(
    /**
     * Value of the zone setting
     * on, off
     */
    val value: String = "on",

    /**
     * Value of the zone setting
     *
     * The interval (in seconds) from when development mode expires (positive integer) or last expired (negative integer)
     * for the domain. If development mode has never been enabled, this value is false.
     */
    @SerialName("time_remaining")
    var timeRemaining: Int = 0,

    override val id: String = "",
    override val editable: Boolean = true,
    override val modifiedOn: LocalDateTime? = null
): ZoneSetting


/**
 * Edge Cache TTL
 */
@Serializable
data class EdgeCacheTtl(
    /**
     * Value of the zone setting
     *
     * valid values: 30, 60, 300, 1200, 1800, 3600, 7200, 10800, 14400, 18000, 28800, 43200, 57600, 72000, 86400, 172800, 259200, 345600, 432000, 518400, 604800
     *
     * notes: The minimum TTL available depends on the plan level of the zone. (Enterprise = 30, Business = 1800, Pro = 3600, Free = 7200)
     */
    val value: Int = 1800,

    override val id: String = "",
    override val editable: Boolean = true,
    override val modifiedOn: LocalDateTime? = null
): ZoneSetting


/**
 * Error Pages On
 */
@Serializable
data class ErrorPagesOn(
    /**
     * Value of the zone setting
     * on, off
     */
    val value: String = "on",

    override val id: String = "",
    override val editable: Boolean = true,
    override val modifiedOn: LocalDateTime? = null
): ZoneSetting


/**
 * Get String Sort
 */
@Serializable
data class StringSort(
    /**
     * Value of the zone setting
     * on, off
     */
    val value: String = "on",

    override val id: String = "",
    override val editable: Boolean = true,
    override val modifiedOn: LocalDateTime? = null
): ZoneSetting


/**
 * Email Obfuscation
 */
@Serializable
data class EmailObfuscation(
    /**
     * Value of the zone setting
     * on, off
     */
    val value: String = "on",

    override val id: String = "",
    override val editable: Boolean = true,
    override val modifiedOn: LocalDateTime? = null
): ZoneSetting


/**
 * Hotlink Protection
 */
@Serializable
data class HotlinkProtection(
    /**
     * Value of the zone setting
     * on, off
     */
    val value: String = "on",

    override val id: String = "",
    override val editable: Boolean = true,
    override val modifiedOn: LocalDateTime? = null
): ZoneSetting


/**
 * IP Geolocation
 */
@Serializable
data class IpGeolocation(
    /**
     * Value of the zone setting
     * on, off
     */
    val value: String = "on",

    override val id: String = "",
    override val editable: Boolean = true,
    override val modifiedOn: LocalDateTime? = null
): ZoneSetting


/**
 * IPv6
 */
@Serializable
data class IPv6(
    /**
     * Value of the zone setting
     * on, off
     */
    val value: String = "on",

    override val id: String = "",
    override val editable: Boolean = true,
    override val modifiedOn: LocalDateTime? = null
): ZoneSetting


/**
 * WebSockets
 */
@Serializable
data class Websockets(
    /**
     * Value of the zone setting
     * on, off
     */
    val value: String = "on",

    override val id: String = "",
    override val editable: Boolean = true,
    override val modifiedOn: LocalDateTime? = null
): ZoneSetting


/**
 * Toggle SHA1 support
 */
@Serializable
data class ToggleSha1(
    /**
     * Value of the zone setting
     * on, off
     */
    val value: String = "on",

    override val id: String = "",
    override val editable: Boolean = true,
    override val modifiedOn: LocalDateTime? = null
): ZoneSetting


/**
 * TLS1.2 Only
 */
@Serializable
data class Tls1_2Only(
    /**
     * Value of the zone setting
     * on, off
     */
    val value: String = "on",

    override val id: String = "",
    override val editable: Boolean = true,
    override val modifiedOn: LocalDateTime? = null
): ZoneSetting


/**
 * Auto-Minify Assets
 */
@Serializable
data class AutoMinify(
    /**
     * Value of the zone setting
     * on, off
     */
    val value: MinifyAssetsSetting = MinifyAssetsSetting(),

    override val id: String = "",
    override val editable: Boolean = true,
    override val modifiedOn: LocalDateTime? = null
): ZoneSetting


/**
 * Max Upload
 */
@Serializable
data class MaxUpload(
    /**
     * Value of the zone setting
     * valid values: 100, 200, 500
     *
     * notes: The size depends on the plan level of the zone. (Enterprise = 500, Business = 200, Pro = 100, Free = 100)
     */
    val value: Int = 100,

    override val id: String = "",
    override val editable: Boolean = true,
    override val modifiedOn: LocalDateTime? = null
): ZoneSetting


/**
 * Mobile Redirect
 */
@Serializable
data class MobileRedirect(
    /**
     * Value of the zone setting
     * on, off
     */
    val value: MobileRedirectSetting = MobileRedirectSetting(),

    override val id: String = "",
    override val editable: Boolean = true,
    override val modifiedOn: LocalDateTime? = null
): ZoneSetting


/**
 * Mirage Image Optimization
 */
@Serializable
data class Mirage(
    /**
     * Value of the zone setting
     * on, off
     */
    val value: String = "on",

    override val id: String = "",
    override val editable: Boolean = true,
    override val modifiedOn: LocalDateTime? = null
): ZoneSetting


/**
 * Polish Image Optimization
 */
@Serializable
data class PolishImage(
    /**
     * Value of the zone setting
     *  off, lossless, lossy
     */
    val value: String = "off",

    override val id: String = "",
    override val editable: Boolean = true,
    override val modifiedOn: LocalDateTime? = null
): ZoneSetting


/**
 * Polish WebP Conversion
 */
@Serializable
data class PolishWebP(
    /**
     * Value of the zone setting
     * on, off
     */
    val value: String = "on",

    override val id: String = "",
    override val editable: Boolean = true,
    override val modifiedOn: LocalDateTime? = null
): ZoneSetting


/**
 * Prefetch Preload
 */
@Serializable
data class PrefetchPreload(
    /**
     * Value of the zone setting
     * on, off
     */
    val value: String = "on",

    override val id: String = "",
    override val editable: Boolean = true,
    override val modifiedOn: LocalDateTime? = null
): ZoneSetting


/**
 * Privacy Pass
 */
@Serializable
data class PrivatePass(
    /**
     * Value of the zone setting
     * on, off
     */
    val value: String = "on",

    override val id: String = "",
    override val editable: Boolean = true,
    override val modifiedOn: LocalDateTime? = null
): ZoneSetting


/**
 * Response Buffering
 */
@Serializable
data class ReponseBuffering(
    /**
     * Value of the zone setting
     * on, off
     */
    val value: String = "on",

    override val id: String = "",
    override val editable: Boolean = true,
    override val modifiedOn: LocalDateTime? = null
): ZoneSetting


/**
 * Rocket Loader
 */
@Serializable
data class RocketLoader(
    /**
     * Value of the zone setting
     * on, off
     */
    val value: String = "on",

    override val id: String = "",
    override val editable: Boolean = true,
    override val modifiedOn: LocalDateTime? = null
): ZoneSetting


/**
 * Security Header
 */
@Serializable
data class SecurityHeader(
    /**
     * Current value of the zone setting
     */
    val value: SecurityHeadingSetting = SecurityHeadingSetting(),

    override val id: String = "",
    override val editable: Boolean = true,
    override val modifiedOn: LocalDateTime? = null
): ZoneSetting


/**
 * Security Level
 */
@Serializable
data class SecurityLevel(
    /**
     * Value of the zone setting
     * on, off
     */
    val value: SecurityHeadingSetting = SecurityHeadingSetting(),

    override val id: String = "",
    override val editable: Boolean = true,
    override val modifiedOn: LocalDateTime? = null
): ZoneSetting


/**
 * Server Side Exclude
 */
@Serializable
data class ServerSideExclude(
    /**
     * Value of the zone setting
     * on, off
     */
    val value: String = "on",

    override val id: String = "",
    override val editable: Boolean = true,
    override val modifiedOn: LocalDateTime? = null
): ZoneSetting


/**
 * SSL
 */
@Serializable
data class SSL(
    /**
     * Value of the zone setting
     * off, flexible, full, strict
     *
     * notes: Depends on the zone's plan level
     */
    val value: String = "off",

    override val id: String = "",
    override val editable: Boolean = true,
    override val modifiedOn: LocalDateTime? = null
): ZoneSetting


/**
 * TLS Client Authentication
 */
@Serializable
data class TlsClientAuth(
    /**
     * Value of the zone setting
     * off, flexible, full, strict
     *
     * notes: Depends on the zone's plan level
     */
    val value: String = "off",

    override val id: String = "",
    override val editable: Boolean = true,
    override val modifiedOn: LocalDateTime? = null
): ZoneSetting


/**
 * True Client IP Header
 */
@Serializable
data class TrueClientIPHeader(
    /**
     * Value of the zone setting
     * off, flexible, full, strict
     *
     * notes: Depends on the zone's plan level
     */
    val value: String = "off",

    override val id: String = "",
    override val editable: Boolean = true,
    override val modifiedOn: LocalDateTime? = null
): ZoneSetting


/**
 * Web Application Firewall
 */
@Serializable
data class WebApplicationFirewall(
    /**
     * Value of the zone setting
     * on, off
     */
    val value: String = "on",

    override val id: String = "",
    override val editable: Boolean = true,
    override val modifiedOn: LocalDateTime? = null
): ZoneSetting


/**
 * Zone Minimum TLS Version Value
 */
@Serializable
data class ZoneMinimumTLSVersionValue(
    /**
     * Value of the zone setting
     * 1.0, 1.1, 1.2, 1.3
     */
    val value: String = "on",

    override val id: String = "",
    override val editable: Boolean = true,
    override val modifiedOn: LocalDateTime? = null
): ZoneSetting


/**
 * Zone Enable TLS 1.3
 */
@Serializable
data class ZoneEnableTLS1_3(
    /**
     * Value of the zone setting
     * on, off, zrt
     */
    val value: String = "on",

    override val id: String = "",
    override val editable: Boolean = true,
    override val modifiedOn: LocalDateTime? = null
): ZoneSetting


/**
 * Zone Enable Opportunistic Encryption
 */
@Serializable
data class ZoneEnableOpportunisticEncryption(
    /**
     * Value of the zone setting
     * on, off, zrt
     */
    val value: String = "on",

    override val id: String = "",
    override val editable: Boolean = true,
    override val modifiedOn: LocalDateTime? = null
) : ZoneSetting


/**
 * Zone Enable Automatic HTTPS Rewrites
 */
@Serializable
data class ZoneEnableAutomaticHTTPSRewrites(
    /**
     * Value of the zone setting
     * on, off, zrt
     */
    val value: String = "on",

    override val id: String = "",
    override val editable: Boolean = true,
    override val modifiedOn: LocalDateTime? = null
) : ZoneSetting


/**
 * HTTP2
 */
@Serializable
data class HTTP2(
    /**
     * Value of the zone setting
     * on, off, zrt
     */
    val value: String = "on",

    override val id: String = "",
    override val editable: Boolean = true,
    override val modifiedOn: LocalDateTime? = null
) : ZoneSetting


/**
 * Pseudo IPv4 Value
 */
@Serializable
data class PseudoIPv4(
    /**
     * Value of the zone setting
     * off, add_header, overwrite_header
     */
    val value: String = "on",

    override val id: String = "",
    override val editable: Boolean = true,
    override val modifiedOn: LocalDateTime? = null
) : ZoneSetting


/**
 * Zone Enable Always Use HTTPS
 */
@Serializable
data class ZoneEnableAlwaysUseHTTPS(
    /**
     * Value of the zone setting
     * on, off
     */
    val value: String = "on",
    override val id: String = "",
    override val editable: Boolean = true,
    override val modifiedOn: LocalDateTime? = null
) : ZoneSetting


/**
 * Zone Enable Onion Routing
 */
@Serializable
data class ZoneEnableOnionRouting(
    /**
     * Value of the zone setting
     * on, off
     */
    val value: String = "on",

    override val id: String = "",
    override val editable: Boolean = true,
    override val modifiedOn: LocalDateTime? = null
) : ZoneSetting


/**
 * Image Resizing
 */
@Serializable
data class ImageResizing(
    /**
     * Value of the zone setting
     *on, off
     */
    val value:String = "on",

    override val id: String = "",
    override val editable: Boolean = true,
    override val modifiedOn: LocalDateTime? = null
) : ZoneSetting


/**
 * HTTP/2 Edge Prioritization
 */
@Serializable
data class HTTP2EdgePrioritization(
    /**
     * Value of the zone setting
     * on, off, custom
     */
    val value: String = "on",

    override val id: String = "",
    override val editable: Boolean = true,
    override val modifiedOn: LocalDateTime? = null
) : ZoneSetting


/**
 * https://api.cloudflare.com/#zone-settings-properties
 */
interface ZoneSetting {
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
    val id: String

    /**
     * Whether this setting can be modified for this zone (based on your Cloudflare plan level)
     */
    val editable: Boolean

    /**
     * last time this setting was modified
     */
    @SerialName("modified_on")
    @Serializable(LocalDateTimeSerializer::class)
    val modifiedOn: LocalDateTime?
}
