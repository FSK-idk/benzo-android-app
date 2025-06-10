package com.benzo.benzomobile.data.data_source.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetPaymentHistoryResponse(
    @SerialName("history")
    val history: List<Payment>,
) {
    @Serializable
    data class Payment(
        @SerialName("date_time")
        val dateTime: String,

        @SerialName("gas_station_id")
        val gasStationId: Int,

        @SerialName("gas_station_address")
        val gasStationAddress: String,

        @SerialName("station_id")
        val stationId: Int,

        @SerialName("fuel_type")
        val fuelType: String,

        @SerialName("fuel_amount")
        val fuelAmount: Int,

        @SerialName("car_number")
        val carNumber: String,

        @SerialName("payment_amount")
        val paymentAmount: Int,

        @SerialName("bonuses_used")
        val bonusesUsed: Int,

        @SerialName("payment_key")
        val paymentKey: String,
    )
}