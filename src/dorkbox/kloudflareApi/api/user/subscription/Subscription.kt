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

import dorkbox.kloudflareApi.api.core.LocalDateTimeSerializer
import dorkbox.kloudflareApi.api.zone.Zone
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.time.LocalDateTime

/**
 * https://api.cloudflare.com/#user-subscription-properties
 */
@Serializable
data class Subscription(
    /**
     * The end of the current period, and also when the next billing is due
     */
    val app: App = App(),

    /**
     * The end of the current period, and also when the next billing is due
     */
    @SerialName("current_period_end")
    @Serializable(LocalDateTimeSerializer::class)
    val currentPeriodEnd: LocalDateTime? = null,

    /**
     * The list of add-ons subscribed to
     */
    @SerialName("component_values")
    val componentValues: List<ComponentValue> = listOf(),

    /**
     * The rate plan applied to the subscription
     */
    @SerialName("rate_plan")
    val ratePlan: RatePlan = RatePlan(),

    /**
     * The price of the subscription that will be billed, in US dollars
     */
    val price: Int = 0,

    /**
     * When the current billing period started, may be the same as InitialPeriodStart if this is the first period
     */
    @SerialName("current_period_start")
    @Serializable(LocalDateTimeSerializer::class)
    val currentPeriodStart: LocalDateTime? = null,

    /**
    * A simple zone object. May have null properties if not a zone subscription.
    */
    val zone: Zone = Zone(),

    /**
    * The monetary unit in which pricing information is displayed
    */
    val currency: String = "USD",

    /**
    * The state that the subscription is in
    *
    * Trial, Provisioned, Paid, AwaitingPayment, Cancelled, Failed, Expired
    */
    val state: String = "Expired",

    /**
    * Subscription identifier tag
    */
    val id: String = "",

    /**
    * How often the subscription is renewed automatically
    *
    * weekly, monthly, quarterly, yearly
    */
    val frequency: String = "weekly"
)
