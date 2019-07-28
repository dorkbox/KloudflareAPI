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
import dorkbox.kloudflare.api.core.ISO8601
import java.time.LocalDateTime

/**
 * https://api.cloudflare.com/#user-billing-profile-billing-profile
 */
class BillingProfile {

    /**
     * The email address associated with this payment type
     */
    @field:[Json(name = "payment_email")]
    val paymentEmail = ""

    /**
     * Billing profile identifier tag
     */
    @field:[Json(name = "id")]
    val id = ""

    /**
     * The first name on the billing profile
     */
    @field:[Json(name = "first_name")]
    val firstName = ""

    /**
     * The last name on the billing profile
     */
    @field:[Json(name = "last_name")]
    val lastName = ""

    /**
     * Street address on the billing profile
     */
    @field:[Json(name = "address")]
    val address = ""

    /**
     * Street address continued, apartment/suite, etc (optional)
     */
    @field:[Json(name = "address2")]
    val address2 = ""

    /**
     * The company name on the billing profile
     */
    @field:[Json(name = "company")]
    val company = ""

    /**
     * The city on the billing profile
     */
    @field:[Json(name = "city")]
    val city = ""

    /**
     * the state/province on the billing profile
     */
    @field:[Json(name = "state")]
    val state = ""

    /**
     * The zipcode on the billing profile
     */
    @field:[Json(name = "zipCode")]
    val zipcode = ""

    /**
     * the country of the address on the billing profile
     */
    @field:[Json(name = "country")]
    val country = ""

    /**
     * The telephone associated with the billing profile
     */
    @field:[Json(name = "telephone")]
    val telephone = ""

    /**
     * The last four digits of the credit card on file
     */
    @field:[Json(name = "card_number")]
    val cardNumber = ""

    /**
     * The month number (1-12) of when the credit card on file expires
     */
    @field:[Json(name = "card_expiry_month")]
    val cardExpiryMonth = 1

    /**
     * The year when the credit card on file expires
     */
    @field:[Json(name = "card_expiry_year")]
    val cardExpiryYear = 2020

    /**
     * Value Added Tax ID
     */
    @field:[Json(name = "vat")]
    val vat = ""

    /**
     * Information about a customer's device collected by client SDK
     */
    @field:[Json(name = "device_data")]
    val deviceData = ""

    /**
     * The gateway which was used to tokenize the payment method
     *  braintree, paypal
     */
    @field:[Json(name = "payment_gateway")]
    val paymentGateway = ""

    /**
     * The string returned by the client SDK to represent a payment method
     */
    @field:[Json(name = "payment_nonce")]
    val paymentNonce = ""

    /**
     * When the profile was last modified
     */
    @field:[Json(name = "edited_on") ISO8601]
    val editedOn = LocalDateTime.now()

    /**
     * When the profile was created
     */
    @field:[Json(name = "created_on") ISO8601]
    val createdOn = LocalDateTime.now()

    override fun toString(): String {
        return "BillingProfile(paymentEmail='$paymentEmail', id='$id', firstName='$firstName', lastName='$lastName', address='$address', address2='$address2', company='$company', city='$city', state='$state', zipcode='$zipcode', country='$country', telephone='$telephone', cardNumber='$cardNumber', cardExpiryMonth=$cardExpiryMonth, cardExpiryYear=$cardExpiryYear, vat='$vat', deviceData='$deviceData', paymentGateway='$paymentGateway', paymentNonce='$paymentNonce', editedOn=$editedOn, createdOn=$createdOn)"
    }
}
