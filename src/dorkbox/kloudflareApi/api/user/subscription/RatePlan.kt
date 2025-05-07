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

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * https://api.cloudflare.com/#user-subscription-properties
 */
@Serializable
data class RatePlan(
    /**
     * The ID of the rate_plan
     */
    val id: String = "",

    /**
     * The full name of the rate plan
     */
    @SerialName("public_name")
    val publicName: String = "",

    /**
     * The currency applied to the rate_plan subscription
     */
    val currency: String = "",

    /**
     * The scope that this rate_plan applies to
     */
    val scope: String = "",

    /**
     * The list of sets this rate_plan applies to
     */
    val sets: List<String> = listOf(),

    /**
     * Whether or not a rate_plan is enterprise-based (or newly adopted term contract)
     */
    @SerialName("is_contract")
    val isContract: Boolean = false,

    /**
     * Whether this rate_plan is managed externally from Cloudflare
     */
    @SerialName("externally_managed")
    val externallyManaged: Boolean = false
)
