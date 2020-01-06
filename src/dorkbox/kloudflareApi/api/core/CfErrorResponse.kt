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
package dorkbox.kloudflareApi.api.core

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Date fields will always be in UTC ISO-8601 format, including microseconds.
 */
@JsonClass(generateAdapter = true)
open class CfErrorResponse {
    // HTTP response codes
    //200	OK	request successful
    //304	Not Modified
    //400	Bad Request	request was invalid
    //401	Unauthorized	user does not have permission
    //403	Forbidden	request not authenticated
    //429	Too many requests	client is rate limited
    //405	Method Not Allowed	incorrect HTTP method provided
    //415	Unsupported Media Type	response is not valid JSON

    @field:[Json(name = "success")]
    var success = false

    @field:[Json(name = "errors")]
    var errors = listOf<Error>()

    @field:[Json(name = "messages")]
    var messages = listOf<String>()

    @field:[Json(name = "result_info")]
    var resultInfo: ResultInfo? = null

    override fun toString(): String {
        return "Response(success=$success, errors=$errors, messages=$messages, resultInfo=$resultInfo)"
    }
}
