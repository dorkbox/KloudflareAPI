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
package dorkbox.kloudflareApi.api.firewall

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import dorkbox.kloudflareApi.api.core.ISO8601
import java.time.LocalDateTime

/**
 * https://api.cloudflare.com/#user-level-firewall-access-rule-properties
 */
@JsonClass(generateAdapter = true)
class AccessRule {

    /**
     * Access rule identifier tag
     */
    @field:[Json(name = "id")]
    var id = ""

    /**
     * A personal note about the rule. Typically used as a reminder or explanation for the rule.
     */
    @field:[Json(name = "notes")]
    var notes = ""

    /**
     * The possible modes the rule can be in.
     *
     * valid values: block, challenge, whitelist, js_challenge
     */
    @field:[Json(name = "allowed_modes")]
    var allowedModes = listOf<String>()

    /**
     * The action to apply to a matched request
     *
     * valid values: block, challenge, whitelist, js_challenge
     */
    @field:[Json(name = "mode")]
    var mode = ""

    /**
     * Rule configuration
     */
    @field:[Json(name = "configuration")]
    var configuration = Configuration()

    @field:[Json(name = "scope")]
    var scope = Scope()

    /**
     * When the record was last modified
     */
    @field:[Json(name = "modified_on") ISO8601]
    var modifiedOn: LocalDateTime = LocalDateTime.now()

    /**
     * When the record was created
     */
    @field:[Json(name = "created_on") ISO8601]
    var createdOn: LocalDateTime = LocalDateTime.now()

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as AccessRule

        if (id != other.id) return false
        if (notes != other.notes) return false
        if (allowedModes != other.allowedModes) return false
        if (mode != other.mode) return false
        if (configuration != other.configuration) return false
        if (scope != other.scope) return false
        if (modifiedOn != other.modifiedOn) return false
        if (createdOn != other.createdOn) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + notes.hashCode()
        result = 31 * result + allowedModes.hashCode()
        result = 31 * result + mode.hashCode()
        result = 31 * result + configuration.hashCode()
        result = 31 * result + scope.hashCode()
        result = 31 * result + modifiedOn.hashCode()
        result = 31 * result + createdOn.hashCode()
        return result
    }

    override fun toString(): String {
        return "AccessRule(id='$id', notes='$notes', allowedModes=$allowedModes, mode='$mode', configuration=$configuration, scope=$scope, modifiedOn=$modifiedOn, createdOn=$createdOn)"
    }
}
