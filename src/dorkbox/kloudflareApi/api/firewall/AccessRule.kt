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
package dorkbox.kloudflareApi.api.firewall

import dorkbox.kloudflareApi.api.core.LocalDateTimeSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.time.LocalDateTime

/**
 * https://api.cloudflare.com/#user-level-firewall-access-rule-properties
 */
@Serializable
data class AccessRule(
    /**
     * Access rule identifier tag
     */
    val id: String = "",

    /**
     * A personal note about the rule. Typically used as a reminder or explanation for the rule.
     */
    val notes: String = "",

    /**
     * The possible modes the rule can be in.
     *
     * valid values: block, challenge, whitelist, js_challenge
     */
    @SerialName("allowed_modes")
    val allowedModes: List<String> = listOf<String>(),

    /**
     * The action to apply to a matched request
     *
     * valid values: block, challenge, whitelist, js_challenge
     */
    val mode: String = "",

    /**
     * Rule configuration
     */
    val configuration: Configuration = Configuration(),

    val scope: Scope = Scope(),

    /**
     * When the record was last modified
     */
    @SerialName("modified_on")
    @Serializable(LocalDateTimeSerializer::class)
    val modifiedOn: LocalDateTime? = null,

    /**
     * When the record was created
     */
    @SerialName("created_on")
    @Serializable(LocalDateTimeSerializer::class)
    val createdOn: LocalDateTime = LocalDateTime.now()
)
