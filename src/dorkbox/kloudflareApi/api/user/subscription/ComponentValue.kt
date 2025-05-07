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
package dorkbox.kloudflareApi.api.user.subscription

import kotlinx.serialization.Serializable

/**
 * https://api.cloudflare.com/#user-subscription-properties
 */
@Serializable
data class ComponentValue(
    /**
     * The name of the component_value
     */
    var name: String = "",

    /**
     * The amount of the component value assigned
     */
    val value: Int = 0,

    /**
     * The default amount assigned.
     */
    val default: Int = 0,

    /**
     * The unit price for the component value
     */
    val price: Int = 0
)
