package com.benzo.benzomobile.data.service.benzo_api

import com.benzo.benzomobile.data.data_source.dto.GetStationFuelsResponse
import com.benzo.benzomobile.data.data_source.dto.GetGasStationsResponse
import com.benzo.benzomobile.data.data_source.dto.GetLoyaltyCardResponse
import com.benzo.benzomobile.data.data_source.dto.GetPaymentHistoryResponse
import com.benzo.benzomobile.data.data_source.dto.GetStationsResponse
import com.benzo.benzomobile.data.data_source.dto.RegisterResponse
import com.benzo.benzomobile.data.data_source.dto.GetUserResponse
import com.benzo.benzomobile.data.data_source.dto.LoginResponse
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface BenzoApiService {
    @FormUrlEncoded
    @POST("user/register/")
    suspend fun registerUser(
        @Field("login") login: String,
        @Field("password") password: String,
    ): Response<RegisterResponse>

    @FormUrlEncoded
    @POST("user/login/")
    suspend fun loginUser(
        @Field("login") login: String,
        @Field("password") password: String,
    ): Response<LoginResponse>

    @GET("user/")
    suspend fun getUser(
        @Header("Authorization") token: String,
    ): Response<GetUserResponse>

    @FormUrlEncoded
    @POST("user/data/")
    suspend fun updateUser(
        @Header("Authorization") token: String,
        @Field("name") name: String,
        @Field("birth_date") birthDate: String,
        @Field("car_number") carNumber: String,
        @Field("phone_number") phoneNumber: String,
        @Field("email") email: String,
        @Field("gender") gender: String,
    ): Response<Unit>

    @GET("user/loyalty-card/")
    suspend fun getLoyaltyCard(
        @Header("Authorization") token: String,
    ): Response<GetLoyaltyCardResponse>

    @GET("user/payment-history/")
    suspend fun getPaymentHistory(
        @Header("Authorization") token: String,
    ): Response<GetPaymentHistoryResponse>

    @GET("gas-stations/")
    suspend fun getGasStations(): Response<List<GetGasStationsResponse>>

    @GET("gas-station/{id}/stations/")
    suspend fun getGasStationStations(
        @Path("id") gasStationId: Int,
    ): Response<GetStationsResponse>


    @GET("station/{id}/fuels/")
    suspend fun getStationFuels(
        @Path("id") stationId: Int,
    ): Response<GetStationFuelsResponse>
}