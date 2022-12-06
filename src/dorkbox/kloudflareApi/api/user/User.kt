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
import com.squareup.moshi.JsonClass
import java.time.LocalDateTime

/**
 * https://api.cloudflare.com/#user-properties
 */
@JsonClass(generateAdapter = true)
class User {

    /**
     * A list of betas the user is currently participating in. If a beta is zone-specific, the beta will apply to all zones.
     */
    @field:[Json(name = "betas")]
    var betas = listOf<String>()

    /**
     * A list of the organizations the user is a member of (or invited to) and the permissions granted to them.
     */
    @field:[Json(name = "organizations")]
    var organizations = listOf<UserOrganization>()

    /**
     * User's telephone number
     */
    @field:[Json(name = "telephone")]
    var telephone: String? = null

    /**
     * The zipcode or postal code where the user lives.
     */
    @field:[Json(name = "zipcode")]
    var zipcode: String? = null

    /**
     * User's last name
     */
    @field:[Json(name = "last_name")]
    var lastName: String? = null

    /**
     * Last time the user was modified
     */
    @field:[Json(name = "modified_on")]
    var modifiedOn: LocalDateTime = LocalDateTime.now()

    /**
     * A username used to access other cloudflare services, like support
     */
    @field:[Json(name = "username")]
    var userName = ""

    /**
     * When the user signed up.
     */
    @field:[Json(name = "created_on")]
    var createdOn: LocalDateTime = LocalDateTime.now()

    /**
     * The country in which the user lives.
     */
    @field:[Json(name = "country")]
    var country: String? = null

    /**
     * Whether two-factor authentication is enabled for the user account. This does not apply to API authentication
     */
    @field:[Json(name = "two_factor_authentication_enabled")]
    var twoFactorAuthenticationEnabled = false

    /**
     * User's first name
     */
    @field:[Json(name = "first_name")]
    var firstName: String? = null

    /**
     * User identifier tag
     */
    @field:[Json(name = "id")]
    var id = ""

    /**
     * Indicates whether the user is prevented from performing certain actions within their account
     */
    @field:[Json(name = "suspended")]
    var suspended = false

    /**
     * Your contact email address
     */
    @field:[Json(name = "email")]
    var email = ""

    override fun toString(): String {
        return "User(betas=$betas, organizations=$organizations, telephone=$telephone, zipcode=$zipcode, lastName=$lastName, modifiedOn=$modifiedOn, userName='$userName', createdOn=$createdOn, country=$country, twoFactorAuthenticationEnabled=$twoFactorAuthenticationEnabled, firstName=$firstName, id='$id', suspended=$suspended, email='$email')"
    }
}
