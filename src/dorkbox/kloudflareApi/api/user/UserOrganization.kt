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

import kotlinx.serialization.Serializable

/**
 * https://api.cloudflare.com/#user-s-organizations-list-organizations
 */
@Serializable
data class UserOrganization(
    // Organization identifier tag
    val id: String = "",

    // Organization Name
    var name: String = "",

    // Whether the user is a member of the organization or has an invitation pending
    val status: String = "",

    // Access permissions for this User
    // "permissions":["#access:edit","#access:read","#analytics:read","#api_gateway:edit","#api_gateway:read","#app:edit","#auditlogs:read","#billing:edit","#billing:read","#blocks:edit","#blocks:read","#cache_purge:edit","#cds_compute_account:edit","#cds_compute_account:read","#cds:edit","#cds:read","#ces_analytics:read","#ces_integration:edit","#ces_integration:read","#ces_phishguard:read","#ces_search:action","#ces_search:preview","#ces_search:raw","#ces_search:read","#ces_search:trace","#ces_settings:edit","#ces_settings:read","#cfone:edit","#cfone:read","#dash_sso:edit","#dash_sso:read","#dex:edit","#dex:read","#dns_records:edit","#dns_records:read","#fbm_acc:edit","#fbm:edit","#fbm:read","#healthchecks:edit","#healthchecks:read","#http_applications:edit","#http_applications:read","#image:edit","#image:read","#integration:edit","#integration:install","#integration:read","#lb:edit","#lb:read","#legal:edit","#legal:read","#logs:edit","#logs:read","#magic:edit","#magic:read","#member:edit","#member:read","#organization:edit","#organization:read","#page_shield:edit","#page_shield:read","#query_cache:edit","#query_cache:read","#r2_bucket:edit","#r2_bucket_item:edit","#r2_bucket_item:read","#r2_bucket:read","#r2_bucket_warehouse:edit","#r2_bucket_warehouse:read","#resilience:edit","#resilience:read","#ssl:edit","#ssl:read","#stream:edit","#stream:read","#subscription:edit","#subscription:read","#teams:edit","#teams:pii","#teams:read","#teams:report","#turnstile:edit","#turnstile:read","#vectorize:edit","#vectorize:read","#waf:edit","#waf:read","#waitingroom:edit","#waitingroom:read","#webhooks:edit","#webhooks:read","#worker:edit","#worker:read","#zaraz:edit","#zaraz:publish","#zaraz:read","#zone:edit","#zone:read","#zone_settings:edit","#zone_settings:read","#zone_versioning:edit","#zone_versioning:read"]
    val permissions: List<String> = listOf<String>(),

    // List of role names for the User at the Organization
    // "roles":["Super Administrator - All Privileges"]}]
    val roles: List<String> = listOf<String>()
)
