package com.benzo.benzomobile.data.data_source.loyalty_card

import android.util.Log
import com.benzo.benzomobile.app.TAG
import com.benzo.benzomobile.domain.model.Result
import com.benzo.benzomobile.data.service.benzo_api.BenzoApi
import com.benzo.benzomobile.data.data_source.authentication.AuthenticationDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first

class LoyaltyCardDataSourceImpl(
    val benzoApi: BenzoApi,
    val authenticationDataSource: AuthenticationDataSource,
) : LoyaltyCardDataSource {
    private val _loyaltyCardData = MutableStateFlow<Result<LoyaltyCardData>>(Result.Loading())

    override fun getLoyaltyCardData(): Flow<Result<LoyaltyCardData>> = _loyaltyCardData

    override suspend fun fetchLoyaltyCardData() {
        val token = authenticationDataSource.authenticationData.first().token

        if (token == null) {
            Log.e(TAG, "Token not found")
            _loyaltyCardData.value = Result.Error(message = "Error loading user data")
            return
        }

        val getLoyaltyCardResponse = try {
            benzoApi.retrofitService.getLoyaltyCard(
                token = token
            )
        } catch (e: Exception) {
            Log.e(TAG, "$e")
            _loyaltyCardData.value = Result.Error(message = "Error loading user data")
            return
        }

        if (!getLoyaltyCardResponse.isSuccessful) {
            Log.e(TAG, "Response is not successful")
            _loyaltyCardData.value = Result.Error(message = "Error loading user data")
            return
        }

        val loyaltyCardDto = getLoyaltyCardResponse.body()!!

        _loyaltyCardData.value = Result.Success(
            data = LoyaltyCardData(
                number = loyaltyCardDto.number,
                balance = loyaltyCardDto.balance,
            )
        )
    }
}