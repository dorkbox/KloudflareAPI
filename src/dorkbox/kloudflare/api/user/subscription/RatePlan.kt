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
package dorkbox.kloudflare.api.user.subscription

import com.squareup.moshi.Json

/**
 * https://api.cloudflare.com/#user-subscription-properties
 */
class RatePlan {

    /**
     * The ID of the rate_plan
     */
    @field:[Json(name = "id")]
    val id = ""

    /**
     * The full name of the rate plan
     */
    @field:[Json(name = "public_name")]
    val publicName = ""

    /**
     * The currency applied to the rate_plan subscription
     */
    @field:[Json(name = "currency")]
    val currency = ""

    /**
     * The scope that this rate_plan applies to
     */
    @field:[Json(name = "scope")]
    val scope = ""

    /**
     * The list of sets this rate_plan applies to
     */
    @field:[Json(name = "sets")]
    val sets = listOf<String>()

    /**
     * Whether or not a rate_plan is enterprise-based (or newly adopted term contract)
     */
    @field:[Json(name = "is_contract")]
    val isContract = false

    /**
     * Whether this rate_plan is managed externally from Cloudflare
     */
    @field:[Json(name = "externally_managed")]
    val externallyManaged = false

    override fun toString(): String {
        return "RatePlan(id='$id', publicName='$publicName', currency='$currency', scope='$scope', sets=$sets, isContract=$isContract, externallyManaged=$externallyManaged)"
    }
}
