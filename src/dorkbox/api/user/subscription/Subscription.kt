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
import dorkbox.api.core.ISO8601
import dorkbox.api.zone.Zone
import java.time.LocalDateTime

/**
 * https://api.cloudflare.com/#user-subscription-properties
 */
class Subscription {

    /**
     * The end of the current period, and also when the next billing is due
     */
    @field:[Json(name = "app")]
    val app = App()

    /**
     * The end of the current period, and also when the next billing is due
     */
    @field:[Json(name = "current_period_end") ISO8601]
    val currentPeriodEnd= LocalDateTime.now()

    /**
     * The list of add-ons subscribed to
     */
    @field:[Json(name = "component_values")]
    val componentValues = listOf<ComponentValue>()

    /**
     * The rate plan applied to the subscription
     */
    @field:[Json(name = "rate_plan")]
    val ratePlan = RatePlan()

    /**
     * The price of the subscription that will be billed, in US dollars
     */
    @field:[Json(name = "price")]
    val price = 0

    /**
     * When the current billing period started, may be the same as InitialPeriodStart if this is the first period
     */
    @field:[Json(name = "current_period_start") ISO8601]
    val currentPeriodStart = LocalDateTime.now()

    /**
     * A simple zone object. May have null properties if not a zone subscription.
     */
    @field:[Json(name = "zone")]
    val zone = Zone()

    /**
     * The monetary unit in which pricing information is displayed
     */
    @field:[Json(name = "currency")]
    val currency = "USD"

    /**
     * The state that the subscription is in
     *
     * Trial, Provisioned, Paid, AwaitingPayment, Cancelled, Failed, Expired
     */
    @field:[Json(name = "state")]
    val state = "Expired"

    /**
     * Subscription identifier tag
     */
    @field:[Json(name = "id")]
    val id = ""

    /**
     * How often the subscription is renewed automatically
     *
     * weekly, monthly, quarterly, yearly
     */
    @field:[Json(name = "frequency")]
    val frequency = "weekly"

    override fun toString(): String {
        return "Subscription(app=$app, currentPeriodEnd=$currentPeriodEnd, componentValues=$componentValues, ratePlan=$ratePlan, price=$price, currentPeriodStart=$currentPeriodStart, zone=$zone, currency='$currency', state='$state', id='$id', frequency='$frequency')"
    }
}
