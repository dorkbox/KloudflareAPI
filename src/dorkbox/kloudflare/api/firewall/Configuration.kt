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
package dorkbox.kloudflare.api.firewall

import com.squareup.moshi.Json

/**
 * https://api.cloudflare.com/#user-level-firewall-access-rule-properties
 */
class Configuration {

    /**
     * The request property to target
     *
     * ip, ip_range, asn, country
     *
     */
    @field:[Json(name = "target")]
    val target = ""


    /**
     * IP       : The IP address to target in requests (198.51.100.4)
     * IP_RANGE : The IP range to target in requests. Limited to /16 and /24  (198.51.100.4/16)
     * ASN      : The AS number to target in requests. (AS12345)
     * COUNTRY  : US, DE, etc
     */
    @field:[Json(name = "value")]
    val value = ""

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Configuration

        if (target != other.target) return false
        if (value != other.value) return false

        return true
    }

    override fun hashCode(): Int {
        var result = target.hashCode()
        result = 31 * result + value.hashCode()
        return result
    }

    override fun toString(): String {
        return "Configuration(target='$target', value='$value')"
    }
}
