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
package dorkbox.kloudflareApi.api

import dorkbox.kloudflareApi.api.user.BillingHistory
import dorkbox.kloudflareApi.api.user.BillingProfile
import dorkbox.kloudflareApi.api.user.User
import dorkbox.kloudflareApi.cf
import dorkbox.kloudflareApi.cfList
import io.ktor.client.*
import io.ktor.http.*

class UserActions(private val ktor: HttpClient) {
    /**
     * Gets the User details
     *
     * https://api.cloudflare.com/#user-properties
     */
    suspend fun details(): User {
        return ktor.cf(HttpMethod.Get, "user")
    }


    /**
     * Gets the user's Billing Profile
     *
     * https://api.cloudflare.com/#user-billing-profile-billing-profile
     */
    suspend fun billingProfile(): BillingProfile {
        return ktor.cf(HttpMethod.Get, "user/billing/profile")
    }

    /**
     * Gets the users Billing History
     *
     * https://api.cloudflare.com/#user-billing-history-billing-history
     */
    suspend fun billingHistory(): List<BillingHistory> {
        return ktor.cfList(HttpMethod.Get,"user/billing/history")
    }
}
