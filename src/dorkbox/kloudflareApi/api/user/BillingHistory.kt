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
package dorkbox.kloudflareApi.api.user

import com.squareup.moshi.Json
import dorkbox.kloudflareApi.api.core.ISO8601
import dorkbox.kloudflareApi.api.zone.Zone
import java.time.LocalDateTime

/**
 * https://api.cloudflare.com/#user-billing-history-billing-history
 */
class BillingHistory  {

    /**
     * Billing item identifier tag
     */
    @field:[Json(name = "id")]
    val id = ""

    /**
     * The billing item type
     */
    @field:[Json(name = "type")]
    val type = "charge"

    /**
     * The billing item action
     */
    @field:[Json(name = "action")]
    val action = "subscription"

    /**
     * The billing item description
     */
    @field:[Json(name = "description")]
    val description = "The billing item description"

    /**
     * When the billing item was created
     */
    @field:[Json(name = "occurred_at") ISO8601]
    val occurredAt= LocalDateTime.now()

    /**
     * The amount associated with this billing item
     */
    @field:[Json(name = "amount")]
    val amount: Double = 20.99

    /**
     * The monetary unit in which pricing information is displayed
     */
    @field:[Json(name = "currency")]
    val currency = "USD"

    /**
     *
     */
    @field:[Json(name = "zone")]
    val zone = Zone()

    override fun toString(): String {
        return "BillingHistory(id='$id', type='$type', action='$action', description='$description', occurredAt=$occurredAt, amount=$amount, currency='$currency', zone=$zone)"
    }
}
