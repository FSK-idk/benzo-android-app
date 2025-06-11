package com.benzo.benzomobile.domain.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class PayRequest(
    @SerialName("deposit_card_number")
    val depositCardNumber: String,

    @SerialName("deposit_card_expiration_date")
    val depositCardExpirationDate: String,

    @SerialName("deposit_card_holder_name")
    val depositCardHolderName: String,

    @SerialName("payment_amount")
    val paymentAmount: Int,
)

@Serializable
class PayResponse(
    @SerialName("payment_key")
    val paymentKey: String,
)