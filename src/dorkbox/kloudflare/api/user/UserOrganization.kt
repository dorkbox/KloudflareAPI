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

/**
 * https://api.cloudflare.com/#user-s-organizations-list-organizations
 */
class UserOrganization {

    /**
     * Organization identifier tag
     */
    @field:[Json(name = "id")]
    val id = ""

    /**
     * Organization Name
     */
    @field:[Json(name = "name")]
    val name = ""

    /**
     * Whether or not the user is a member of the organization or has an inivitation pending
     */
    @field:[Json(name = "status")]
    val status = ""

    /**
     * Access permissions for this User
     */
    @field:[Json(name = "permissions")]
    val permissions = listOf<String>()

    /**
     * List of role names for the User at the Organization
     */
    @field:[Json(name = "roles")]
    val roles= listOf<String>()

    override fun toString(): String {
        return "UserOrganization(id='$id', name='$name', status='$status', permissions=$permissions, roles=$roles)"
    }
}
