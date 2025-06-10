package com.benzo.benzomobile.domain.model

sealed class Resource<T>() {
    class Loading<T> : Resource<T>()
    class Loaded<T>(val data: T) : Resource<T>()
}