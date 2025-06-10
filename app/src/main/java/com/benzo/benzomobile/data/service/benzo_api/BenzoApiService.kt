package com.benzo.benzomobile.data.service.benzo_api

import com.benzo.benzomobile.data.data_source.dto.LoyaltyCardDto
import com.benzo.benzomobile.data.data_source.dto.TokenDto
import com.benzo.benzomobile.data.data_source.dto.UserDto
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST


interface BenzoApiService {
    @FormUrlEncoded
    @POST("user/register/")
    suspend fun registerUser(
        @Field("login") login: String,
        @Field("password") password: String,
    ): Response<TokenDto>

    @FormUrlEncoded
    @POST("user/login/")
    suspend fun loginUser(
        @Field("login") login: String,
        @Field("password") password: String,
    ): Response<TokenDto>

    @GET("user/")
    suspend fun getUser(
        @Header("Authorization") token: String,
    ): Response<UserDto>

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

    @GET("loyalty-card/")
    suspend fun getLoyaltyCard(
        @Header("Authorization") token: String,
    ): Response<LoyaltyCardDto>
}