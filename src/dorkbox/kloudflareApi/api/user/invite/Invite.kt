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
package dorkbox.kloudflareApi.api.user.invite

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.time.LocalDateTime

/**
 * https://api.cloudflare.com/#user-s-invites-properties
 */
@JsonClass(generateAdapter = true)
class Invite {

    /**
     * When the invite was sent
     */
    @field:[Json(name = "invited_on")]
    var invitedOn: LocalDateTime = LocalDateTime.now()

    /**
     * ID of the Organization the user will be added to
     */
    @field:[Json(name = "organization_id")]
    var organizationId: String? = null

    /**
     * When the invite is no longer active
     */
    @field:[Json(name = "expires_on")]
    var expiresOn: String? = null

    /**
     * Current status of the invitation
     * pending, accepted, rejected, expired
     */
    @field:[Json(name = "status")]
    var status = "pending"

    /**
     * Organization Name
     */
    @field:[Json(name = "organization_name")]
    var organizationName = "Cloudflare, Inc."

    /**
     * The email address of the user who created the invite
     */
    @field:[Json(name = "invited_by")]
    var invitedBy: String? = null

    /**
     * Email address of the user to be added to the Organization
     */
    @field:[Json(name = "invited_member_email")]
    var invitedMemberEmail: String? = null

    /**
     * Invite identifier tag
     */
    @field:[Json(name = "id")]
    var id = ""

    /**
     * Id of the user to be added to the Organization
     */
    @field:[Json(name = "invited_member_id")]
    var invitedMemberId: String? = null

    /**
     * Roles to be assigned to this Member
     */
    @field:[Json(name = "roles")]
    var roles = listOf<Role>()

    override fun toString(): String {
        return "Invite(invitedOn=$invitedOn, organizationId=$organizationId, expiresOn=$expiresOn, status='$status', organizationName='$organizationName', invitedBy=$invitedBy, invitedMemberEmail=$invitedMemberEmail, id='$id', invitedMemberId=$invitedMemberId, roles=$roles)"
    }
}
