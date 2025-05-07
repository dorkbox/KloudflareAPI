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
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.time.LocalDateTime

/**
 * https://api.cloudflare.com/#user-properties
 */
@Serializable
data class User(
    // User identifier tag
    val id: String = "",

    // Your contact email address
    val email: String = "",

    // A username used to access other cloudflare services, like support
    val userName: String = "",

    // User's first name
    @SerialName("first_name")
    val firstName: String? = null,

    // User's last name
    @SerialName("last_name")
    val lastName: String? = null,

    // User's telephone number
    val telephone: String? = null,

    // The country in which the user lives.
    val country: String? = null,

    // The zipcode or postal code where the user lives.
    val zipcode: String? = null,

    // Whether two-factor authentication is enabled for the user account. This does not apply to API authentication
    @SerialName("two_factor_authentication_enabled")
    val twoFactorAuthenticationEnabled: Boolean = false,

    // Whether two-factor authentication is locked for the user account. This does not apply to API authentication
    @SerialName("two_factor_authentication_locked")
    val twoFactorAuthenticationLocked: Boolean = false,

    // When the user signed up.
    @SerialName("created_on")
    @Serializable(LocalDateTimeSerializer::class)
    val createdOn: LocalDateTime? = null,

    // Last time the user was modified
    @SerialName("modified_on")
    @Serializable(LocalDateTimeSerializer::class)
    val modifiedOn: LocalDateTime? = null,

    // A list of the organizations the user is a member of (or invited to) and the permissions granted to them.
    val organizations: List<UserOrganization> = listOf(),

    @SerialName("has_pro_zones")
    val hasProZones: Boolean = false,

    @SerialName("has_business_zones")
    val hasBusinessZones: Boolean = false,

    @SerialName("has_enterprise_zones")
    val hasEnterpriseZones: Boolean = false,

    // Indicates whether the user is prevented from performing certain actions within their account
    val suspended: Boolean = false,

    // A list of betas the user is currently participating in. If a beta is zone-specific, the beta will apply to all zones.
    // ["zone_level_access_beta"]
    val betas: List<String> = listOf(),
)
