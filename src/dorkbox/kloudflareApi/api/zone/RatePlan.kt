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
package dorkbox.kloudflareApi.api.zone

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * https://api.cloudflare.com/#zone-rate-plan-properties
 */
@JsonClass(generateAdapter = true)
class RatePlan {

    /**
     * Plan identifier tag
     */
    @field:[Json(name = "id")]
    var id = "free"

    /**
     * The plan name
     */
    @field:[Json(name = "name")]
    var name = "Free Plan"

    /**
     * The monetary unit in which pricing information is displayed
     */
    @field:[Json(name = "currency")]
    var currency = "USD"

    /**
     * The duration of the plan subscription
     */
    @field:[Json(name = "duration")]
    var duration = 1

    /**
     * The frequency at which you will be billed for this plan
     * weekly, monthly, quarterly, yearly
     */
    @field:[Json(name = "frequency")]
    var frequency = "monthly"

    /**
     * Array of available components values for the plan
     */
    @field:[Json(name = "components")]
    var components = listOf<Component>()

    override fun toString(): String {
        return "RatePlan(id='$id', name='$name', currency='$currency', duration=$duration, frequency='$frequency', components=$components)"
    }
}
