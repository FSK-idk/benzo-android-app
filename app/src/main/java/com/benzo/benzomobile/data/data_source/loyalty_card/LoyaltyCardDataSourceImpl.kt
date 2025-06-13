package com.benzo.benzomobile.data.data_source.loyalty_card

import android.util.Log
import com.benzo.benzomobile.app.TAG
import com.benzo.benzomobile.domain.model.Resource
import com.benzo.benzomobile.data.service.benzo_api.BenzoApi
import com.benzo.benzomobile.data.data_source.user_preferences.UserPreferencesDataSource
import com.benzo.benzomobile.domain.model.LoyaltyCard
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first

class LoyaltyCardDataSourceImpl(
    val benzoApi: BenzoApi,
    val userPreferencesDataSource: UserPreferencesDataSource,
) : LoyaltyCardDataSource {
    override suspend fun getLoyaltyCard(): LoyaltyCard {
        val token = userPreferencesDataSource.userPreferences.first().token

        if (token == null) {
            Log.e(TAG, "Token not found")
            throw Exception("Ошибка сети")
        }

        val response = try {
            benzoApi.retrofitService.getLoyaltyCard(
                token = token
            )
        } catch (e: Exception) {
            Log.e(TAG, "$e")
            throw Exception("Ошибка сети")
        }

        if (!response.isSuccessful) {
            Log.e(TAG, "Response is not successful")
            throw Exception("Ошибка сети")
        }

        val body = response.body()

        if (body == null) {
            Log.e(TAG, "Body is empty")
            throw Exception("Ошибка сети")
        }

        return LoyaltyCard(
            number = body.number,
            balance = body.balance,
        )
    }
}