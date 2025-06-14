package com.benzo.benzomobile.domain.model

enum class StationStatus(value: String) {
    BUSY_OFFLINE("busy_offline"),
    BUSY_ONLINE("busy_online"),
    FREE("free"),
    NOT_WORKING("not_working"),
}

fun getStationStatusName(status: StationStatus) =
    when (status) {
        StationStatus.BUSY_OFFLINE -> "Занято"
        StationStatus.BUSY_ONLINE -> "Занято"
        StationStatus.FREE -> "Свободно"
        StationStatus.NOT_WORKING -> "Не работает"
    }