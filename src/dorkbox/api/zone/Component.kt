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
package dorkbox.api.zone

import com.squareup.moshi.Json

/**
 * https://api.cloudflare.com/#zone-properties
 */
class Component {

    /**
     * The unique component
     * zones, page_rules, dedicated_certificates, dedicated_certificates_custom
     */
    @field:[Json(name = "name")]
    val name: String? = null

    /**
     * The default amount allocated
     */
    @field:[Json(name = "default")]
    val default = 5

    /**
     * The unit price of the addon
     */
    @field:[Json(name = "unit_price")]
    val unitPrice = 0

    override fun toString(): String {
        return "Components(name=$name, default=$default, unitPrice=$unitPrice)"
    }
}
