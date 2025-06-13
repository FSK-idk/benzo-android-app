package com.benzo.benzomobile.domain.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class MessageType(
    val value: String
) {
    @SerialName("mobile_app_connect")
    MOBILE_APP_CONNECT("mobile_app_connect"),

    @SerialName("mobile_app_connected")
    MOBILE_APP_CONNECTED("mobile_app_connected"),

    @SerialName("mobile_app_save_payment")
    MOBILE_APP_SAVE_PAYMENT("mobile_app_save_payment"),

    @SerialName("gas_nozzle_used_t2")
    GAS_NOZZLE_USED_T2("gas_nozzle_used_t2"),

    @SerialName("mobile_app_used_t2")
    MOBILE_APP_USED_T2("mobile_app_used_t2"),
}

@Serializable
class MobileAppConnectMessage(
    @SerialName("message_type")
    val messageType: MessageType = MessageType.MOBILE_APP_CONNECT,
)

@Serializable
class MobileAppConnectedMessage(
    @SerialName("message_type")
    val messageType: MessageType = MessageType.MOBILE_APP_CONNECTED,
)

@Serializable
class MobileAppSavePaymentMessage(
    @SerialName("message_type")
    val messageType: MessageType = MessageType.MOBILE_APP_SAVE_PAYMENT,
    
    @SerialName("fuel_type")
    val fuelType: String,

    @SerialName("fuel_amount")
    val fuelAmount: Int,

    @SerialName("car_number")
    val carNumber: String,

    @SerialName("payment_amount")
    val paymentAmount: Int,

    @SerialName("payment_key")
    val paymentKey: String,

    @SerialName("used_bonuses")
    val usedBonuses: Int,
)

@Serializable
class GasNozzleUsedT2Message (
    @SerialName("message_type")
    val messageType: MessageType = MessageType.GAS_NOZZLE_USED_T2
)

@Serializable
class MobileAppUsedT2Message (
    @SerialName("message_type")
    val messageType: MessageType = MessageType.MOBILE_APP_USED_T2
)