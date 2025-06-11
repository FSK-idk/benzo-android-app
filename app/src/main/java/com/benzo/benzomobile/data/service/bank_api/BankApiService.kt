package com.benzo.benzomobile.data.service.bank_api

import com.benzo.benzomobile.data.data_source.dto.RegisterResponse
import com.benzo.benzomobile.domain.model.PayResponse
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface BankApiService {
    @FormUrlEncoded
    @POST("pay")
    suspend fun pay(
        @Field("deposit_card_number") depositCardNumber: String,
        @Field("deposit_card_expiration_date") depositCardExpirationDate: String,
        @Field("deposit_card_holder_name") depositCardHolderName: String,
        @Field("payment_amount") paymentAmount: Int,
    ): Response<PayResponse>
}