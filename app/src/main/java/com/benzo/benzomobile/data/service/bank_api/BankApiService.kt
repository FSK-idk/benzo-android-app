package com.benzo.benzomobile.data.service.bank_api

import com.benzo.benzomobile.domain.model.PayRequest
import com.benzo.benzomobile.domain.model.PayResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface BankApiService {
    @POST("pay")
    suspend fun pay(
        @Body body: PayRequest
    ): Response<PayResponse>
}