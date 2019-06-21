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
class Plan {

    /**
     * Plan identifier tag
     */
    @field:[Json(name = "id")]
    val id = ""

    /**
     * The plan name
     */
    @field:[Json(name = "name")]
    val name: String? = null

    /**
     * The price of the subscription that will be billed, in US dollars
     */
    @field:[Json(name = "price")]
    val price = 0

    /**
     * The monetary unit in which pricing information is displayed
     */
    @field:[Json(name = "currency")]
    val currency = "USD"

    /**
     * The frequency at which you will be billed for this plan
     * weekly, monthly, quarterly, yearly
     */
    @field:[Json(name = "frequency")]
    val frequency = "weekly"

    /**
     * A 'friendly' identifier to indicate to the UI what plan the object is
     * free, pro, business, enterprise
     */
    @field:[Json(name = "legacy_id")]
    val legacyId = "free"

    /**
     * If the zone is subscribed to this plan
     */
    @field:[Json(name = "is_subscribed")]
    val isSubscribed = false

    /**
     * If the zone is allowed to subscribe to this plan
     */
    @field:[Json(name = "can_subscribe")]
    val canSubscribe = false

    override fun toString(): String {
        return "Plan(id='$id', name=$name, price=$price, currency='$currency', frequency='$frequency', legacyId='$legacyId', isSubscribed=$isSubscribed, canSubscribe=$canSubscribe)"
    }
}
