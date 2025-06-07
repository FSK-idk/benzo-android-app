package com.benzo.benzomobile.domain.model

enum class StationStatus(value: String) {
    BUSY_OFFLINE("busy_offline"),
    BUSY_ONLINE("busy_online"),
    FREE("free"),
    NOT_WORKING("not_working"),
}