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
package dorkbox.kloudflareApi.api.zone

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * https://api.cloudflare.com/#zone-properties
 */
@Serializable
data class PlanPending(
    /**
     * Plan identifier tag
     */
    val id: String = "",

    /**
     * The plan name
     */
    var name: String = "",

    /**
     * The price of the subscription that will be billed, in US dollars
     */
    val price: Int = 0,

    /**
     * The monetary unit in which pricing information is displayed
     */
    val currency: String = "USD",

    /**
     * The frequency at which you will be billed for this plan
     * weekly, monthly, quarterly, yearly
     */
    val frequency: String = "weekly",

    /**
     * A 'friendly' identifier to indicate to the UI what plan the object is
     * free, pro, business, enterprise
     */
    @SerialName("legacy_id")
    val legacyId: String = "free",

    /**
     * If the zone is subscribed to this plan
     */
    @SerialName("is_subscribed")
    val isSubscribed: Boolean = false,

    /**
     * If the zone is allowed to subscribe to this plan
     */
    @SerialName("can_subscribe")
    val canSubscribe: Boolean = false
)
