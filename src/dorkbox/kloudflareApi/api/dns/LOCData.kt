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

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * https://api.cloudflare.com/#dns-records-for-a-zone-properties
 */
@Serializable
data class LOCData(
    /**
     * Altitude of location in meters.
     */
    var altitude: Double? = null,

    /**
     * Degrees of latitude.
     */
    @SerialName("lat_degrees")
    var latDegrees: Int? = null,

    /**
     * Latitude direction.
     */
    @SerialName("lat_direction")
    var latDirection: String? = null,

    /**
     * Minutes of latitude.
     */
    @SerialName("lat_minutes")
    var latMinutes: Int? = null,

    /**
     * Seconds of latitude.
     */
    @SerialName("lat_seconds")
    var latSeconds: Double? = null,


    /**
     * Degrees of longitude.
     */
    @SerialName("long_degrees")
    var longDegrees: Int? = null,

    /**
     * Longitude direction.
     */
    @SerialName("long_direction")
    var longDirection: String? = null,

    /**
     * Minutes of longitude.
     */
    @SerialName("long_minutes")
    var longMinutes: Int? = null,

    /**
     * Seconds of longitude.
     */
    @SerialName("long_seconds")
    var longSeconds: Double? = null,

    /**
     * Horizontal precision of location.
     */
    @SerialName("precision_horz")
    var precisionHorz: Int? = null,

    /**
     * Vertical precision of location.
     */
    @SerialName("precision_vert")
    var precisionVert: Int? = null,

    /**
     * Size of location in meters.
     */
    var size: Int? = null,
)
