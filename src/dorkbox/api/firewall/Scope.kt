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
package dorkbox.api.firewall

import com.squareup.moshi.Json

/**
 * https://api.cloudflare.com/#user-level-firewall-access-rule-properties
 */
class Scope {

    /**
     * User identifier tag
     */
    @field:[Json(name = "id")]
    val id = ""

    /**
     * Your contact email address
     */
    @field:[Json(name = "email")]
    val email = ""


    /**
     * The scope of the rule
     *
     * valid values: user
     */
    @field:[Json(name = "type")]
    val type = "user"

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Scope

        if (id != other.id) return false
        if (email != other.email) return false
        if (type != other.type) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + email.hashCode()
        result = 31 * result + type.hashCode()
        return result
    }

    override fun toString(): String {
        return "Scope(id='$id', email='$email', type='$type')"
    }
}
