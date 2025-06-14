package com.benzo.benzomobile.domain.model

sealed interface LoadStatus {
    data object Loading : LoadStatus
    data class Error(val message: String? = null) : LoadStatus
    data object Loaded : LoadStatus
}