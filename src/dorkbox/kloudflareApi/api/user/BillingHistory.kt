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
package dorkbox.kloudflareApi.api.user

import dorkbox.kloudflareApi.api.core.LocalDateTimeSerializer
import dorkbox.kloudflareApi.api.zone.Zone
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.time.LocalDateTime

/**
 * https://api.cloudflare.com/#user-billing-history-billing-history
 */
@Serializable
data class BillingHistory(
    /**
     * Billing item identifier tag
     */
    val id: String = "",

    /**
     * The billing item type
     */
    val type: String = "charge",

    /**
     * The billing item action
     */
    private val action: String = "subscription",

    /**
     * The billing item description
     */
    private val description: String = "The billing item description",

    /**
     * When the billing item was created
     */
    @SerialName("occurred_at")
    @Serializable(LocalDateTimeSerializer::class)
    val occurredAt: LocalDateTime? = null,

    /**
     * The amount associated with this billing item
     */
    val amount: Double = 20.99,

    /**
     * The monetary unit in which pricing information is displayed
     */
    val currency: String = "USD",

    /**
     *
     */
    val zone: Zone = Zone()
)
