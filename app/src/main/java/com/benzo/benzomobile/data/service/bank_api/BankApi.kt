package com.benzo.benzomobile.data.service.bank_api

import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import java.util.concurrent.TimeUnit

class BankApi {
    private val json = Json {
        ignoreUnknownKeys = true
        explicitNulls = false
    }

    private val okHttpClient: OkHttpClient by lazy {
        OkHttpClient.Builder()
            .readTimeout(1000, TimeUnit.MILLISECONDS)
            .connectTimeout(1000, TimeUnit.MILLISECONDS)
            .build()
    }

    val retrofitService: BankApiService by lazy {
        Retrofit.Builder()
            .baseUrl("http://10.0.2.2:8081/api/")
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
            .client(okHttpClient)
            .build()
            .create(BankApiService::class.java)
    }
}