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
package dorkbox.api.user.subscription

import com.squareup.moshi.Json

/**
 * https://api.cloudflare.com/#user-subscription-properties
 */
class ComponentValue {

    /**
     * The name of the component_value
     */
    @field:[Json(name = "name")]
    val name = ""

    /**
     * The amount of the component value assigned
     */
    @field:[Json(name = "value")]
    val value = 0

    /**
     * The default amount assigned.
     */
    @field:[Json(name = "default")]
    val default = 0

    /**
     * The unit price for the component value
     */
    @field:[Json(name = "price")]
    val price = 0
}
