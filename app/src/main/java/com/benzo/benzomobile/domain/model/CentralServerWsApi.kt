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

    @SerialName("mobile_app_cancel_refueling")
    MOBILE_APP_CANCEL_REFUELING("mobile_app_cancel_refueling"),

    @SerialName("mobile_app_save_payment")
    MOBILE_APP_SAVE_PAYMENT("mobile_app_save_payment"),

    @SerialName("use_gas_nozzle_t2")
    USE_GAS_NOZZLE_T2("use_gas_nozzle_t2"),

    @SerialName("gas_nozzle_used_t2")
    GAS_NOZZLE_USED_T2("gas_nozzle_used_t2"),

    @SerialName("use_mobile_app")
    USE_MOBILE_APP("use_mobile_app"),

    @SerialName("mobile_app_used")
    MOBILE_APP_USED("mobile_app_used"),
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
class MobileAppCancelRefuelingMessage(
    @SerialName("message_type")
    val messageType: MessageType = MessageType.MOBILE_APP_CANCEL_REFUELING,
)

@Serializable
class MobileAppSavePaymentMessage(
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
) {
    @SerialName("message_type")
    val messageType: MessageType = MessageType.MOBILE_APP_SAVE_PAYMENT
}

@Serializable
class UseGasNozzleT2Message {
    @SerialName("message_type")
    val messageType: MessageType = MessageType.USE_GAS_NOZZLE_T2
}

@Serializable
class GasNozzleUsedT2Message {
    @SerialName("message_type")
    val messageType: MessageType = MessageType.GAS_NOZZLE_USED_T2
}

@Serializable
class UseMobileAppMessage {
    @SerialName("message_type")
    val messageType: MessageType = MessageType.USE_MOBILE_APP
}

@Serializable
class MobileAppUsedMessage {
    @SerialName("message_type")
    val messageType: MessageType = MessageType.MOBILE_APP_USED
}