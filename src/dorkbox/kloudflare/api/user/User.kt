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
package dorkbox.kloudflare.api.user

import com.squareup.moshi.Json
import dorkbox.kloudflare.api.core.ISO8601
import java.time.LocalDateTime

/**
 * https://api.cloudflare.com/#user-properties
 */
class User {

    /**
     * A list of betas the user is currently participating in. If a beta is zone-specific, the beta will apply to all zones.
     */
    @field:[Json(name = "betas")]
    val betas = listOf<String>()

    /**
     * A list of the organizations the user is a member of (or invited to) and the permissions granted to them.
     */
    @field:[Json(name = "organizations")]
    val organizations = listOf<UserOrganization>()

    /**
     * User's telephone number
     */
    @field:[Json(name = "telephone")]
    val telephone: String? = null

    /**
     * The zipcode or postal code where the user lives.
     */
    @field:[Json(name = "zipcode")]
    val zipcode: String? = null

    /**
     * User's last name
     */
    @field:[Json(name = "last_name")]
    val lastName: String? = null

    /**
     * Last time the user was modified
     */
    @field:[Json(name = "modified_on") ISO8601]
    val modifiedOn= LocalDateTime.now()

    /**
     * A username used to access other cloudflare services, like support
     */
    @field:[Json(name = "username")]
    val userName = ""

    /**
     * When the user signed up.
     */
    @field:[Json(name = "created_on") ISO8601]
    val createdOn= LocalDateTime.now()

    /**
     * The country in which the user lives.
     */
    @field:[Json(name = "country")]
    val country: String? = null

    /**
     * Whether two-factor authentication is enabled for the user account. This does not apply to API authentication
     */
    @field:[Json(name = "two_factor_authentication_enabled")]
    val twoFactorAuthenticationEnabled = false

    /**
     * User's first name
     */
    @field:[Json(name = "first_name")]
    val firstName: String? = null

    /**
     * User identifier tag
     */
    @field:[Json(name = "id")]
    val id = ""

    /**
     * Indicates whether the user is prevented from performing certain actions within their account
     */
    @field:[Json(name = "suspended")]
    val suspended = false

    /**
     * Your contact email address
     */
    @field:[Json(name = "email")]
    val email = ""

    override fun toString(): String {
        return "User(betas=$betas, organizations=$organizations, telephone=$telephone, zipcode=$zipcode, lastName=$lastName, modifiedOn=$modifiedOn, userName='$userName', createdOn=$createdOn, country=$country, twoFactorAuthenticationEnabled=$twoFactorAuthenticationEnabled, firstName=$firstName, id='$id', suspended=$suspended, email='$email')"
    }
}
