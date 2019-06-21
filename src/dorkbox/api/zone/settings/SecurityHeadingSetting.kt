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
package dorkbox.api.zone.settings

import com.squareup.moshi.Json

class SecurityHeadingSetting {
    /**
     * Whether or not strict transport security is enabled
     */
    @field:[Json(name = "enabled")]
    val enabled = false

    /**
     * Max age in seconds of the strict transport security
     */
    @field:[Json(name = "max_age")]
    val maxAge = 1234

    /**
     * Include all subdomains for strict transport security
     */
    @field:[Json(name = "include_subdomains")]
    val includeSubdomains = true

    /**
     * Whether or not to include 'X-Content-Type-Options: nosniff' header
     */
    @field:[Json(name = "nosniff")]
    val noSniff = true

    override fun toString(): String {
        return "SecurityHeadingSetting(enabled=$enabled, maxAge=$maxAge, includeSubdomains=$includeSubdomains, noSniff=$noSniff)"
    }
}
