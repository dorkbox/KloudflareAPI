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
package dorkbox.kloudflareApi.api.zone.settings

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MobileRedirectSetting(
    /**
     * Whether or not the mobile redirection is enabled
     * on, off
     */
    val status: String = "",

    /**
     * Which subdomain prefix you wish to redirect visitors on mobile devices to (subdomain must already exist).
     */
    @SerialName("mobile_subdomain")
    val mobileSubdomain: String? = null,

    /**
     * Whether to drop the current page path and redirect to the mobile subdomain URL root or to keep the path and redirect to the same page on the mobile subdomain
     * on, off
     */
    @SerialName("strip_uri")
    val strip_uri: Boolean = false
)
