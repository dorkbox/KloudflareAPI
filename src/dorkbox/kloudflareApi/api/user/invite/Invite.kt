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
package dorkbox.kloudflareApi.api.user.invite

import dorkbox.kloudflareApi.api.core.LocalDateTimeSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.time.LocalDateTime

/**
 * https://api.cloudflare.com/#user-s-invites-properties
 */
@Serializable
data class Invite(
    /**
     * When the invite was sent
     */
    @SerialName("invited_on")
    @Serializable(LocalDateTimeSerializer::class)
    val invitedOn: LocalDateTime? = null,

    /**
     * ID of the Organization the user will be added to
     */
    @SerialName("organization_id")
    val organizationId: String? = null,

    /**
     * When the invite is no longer active
     */
    @SerialName("expires_on")
    val expiresOn: String? = null,

    /**
     * Current status of the invitation
     * pending, accepted, rejected, expired
     */
    val status: String = "pending",

    /**
     * Organization Name
     */
    @SerialName("organization_name")
    val organizationName: String = "Cloudflare, Inc.",

    /**
     * The email address of the user who created the invite
     */
    @SerialName("invited_by")
    val invitedBy: String? = null,

    /**
     * Email address of the user to be added to the Organization
     */
    @SerialName("invited_member_email")
    val invitedMemberEmail: String? = null,

    /**
     * Invite identifier tag
     */
    val id: String = "",

    /**
     * Id of the user to be added to the Organization
     */
    @SerialName("invited_member_id")
    val invitedMemberId: String? = null,

    /**
     * Roles to be assigned to this Member
     */
    val roles: List<Role> = listOf()
)
