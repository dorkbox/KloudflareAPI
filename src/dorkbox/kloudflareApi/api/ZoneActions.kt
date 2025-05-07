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
package dorkbox.kloudflareApi.api

import dorkbox.kloudflareApi.api.zone.Zone
import dorkbox.kloudflareApi.cfList
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.http.*

class ZoneActions(val ktor: HttpClient) {
    /**
     * Gets the list of Zone's owned by this user
     *
     * https://api.cloudflare.com/#zone-properties
     */
    suspend fun list(): List<Zone> {
        val zones = ktor.cfList<Zone>(HttpMethod.Get, "zones") {
            parameter(key = "per_page", value = 20)  // not too many at once!
        }

        zones.forEach { zone ->
            zone.ktor = ktor
        }

        return zones
    }
}
