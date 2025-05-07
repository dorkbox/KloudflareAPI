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

import dorkbox.kloudflareApi.api.core.LocalDateTimeSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.time.LocalDateTime

/**
 * https://api.cloudflare.com/#user-billing-profile-billing-profile
 */
@Serializable
data class BillingProfile(
    /**
     * The email address associated with this payment type
     */
    @SerialName("payment_email")
    val paymentEmail: String = "",

    /**
     * Billing profile identifier tag
     */
    val id: String = "",

    /**
     * The first name on the billing profile
     */
    @SerialName("first_name")
    val firstName: String = "",

    /**
     * The last name on the billing profile
     */
    @SerialName("last_name")
    val lastName: String = "",

    /**
     * Street address on the billing profile
     */
    val address: String = "",

    /**
     * Street address continued, apartment/suite, etc (optional)
     */
    val address2: String = "",

    /**
     * The company name on the billing profile
     */
    val company: String = "",

    /**
     * The city on the billing profile
     */
    val city: String = "",

    /**
     * the state/province on the billing profile
     */
    val state: String = "",

    /**
     * The zipcode on the billing profile
     */
    val zipcode: String = "",

    /**
     * the country of the address on the billing profile
     */
    val country: String = "",

    /**
     * The telephone associated with the billing profile
     */
    val telephone: String = "",

    /**
     * The last four digits of the credit card on file
     */
    @SerialName("card_number")
    val cardNumber: String = "",

    /**
     * The month number (1-12) of when the credit card on file expires
     */
    @SerialName("card_expiry_month")
    val cardExpiryMonth: Int = 1,

    /**
     * The year when the credit card on file expires
     */
    @SerialName("card_expiry_year")
    val cardExpiryYear: Int = 2020,

    /**
     * value Added Tax ID
     */
    val vat: String = "",

    /**
     * Information about a customer's device collected by client SDK
     */
    @SerialName("device_data")
    val deviceData: String = "",

    /**
     * The gateway which was used to tokenize the payment method
     *  braintree, paypal
     */
    @SerialName("payment_gateway")
    val paymentGateway: String = "",

    /**
     * The string returned by the client SDK to represent a payment method
     */
    @SerialName("payment_nonce")
    val paymentNonce: String = "",

    /**
     * When the profile was last modified
     */
    @SerialName("edited_on")
    @Serializable(LocalDateTimeSerializer::class)
    val editedOn: LocalDateTime? = null,

    /**
     * When the profile was created
     */
    @SerialName("created_on")
    @Serializable(LocalDateTimeSerializer::class)
    val createdOn: LocalDateTime = LocalDateTime.now()
)
