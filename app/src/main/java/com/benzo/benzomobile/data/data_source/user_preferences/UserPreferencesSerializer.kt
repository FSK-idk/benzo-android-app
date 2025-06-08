package com.benzo.benzomobile.data.data_source.user_preferences

import android.util.Log
import androidx.datastore.core.Serializer
import com.benzo.benzomobile.app.TAG
import com.benzo.benzomobile.domain.model.ThemeOption
import kotlinx.serialization.json.Json
import java.io.InputStream
import java.io.OutputStream

class UserPreferencesSerializer : Serializer<UserPreferencesData> {
    override val defaultValue
        get() = UserPreferencesData(
            token = null,
            theme = ThemeOption.SYSTEM,
        )

    override suspend fun readFrom(input: InputStream): UserPreferencesData =
        try {
            Json.decodeFromString(
                deserializer = UserPreferencesData.serializer(),
                string = input.readBytes().decodeToString(),
            )
        } catch (e: Exception) {
            Log.e(TAG, "$e")
            defaultValue
        }

    override suspend fun writeTo(t: UserPreferencesData, output: OutputStream) =
        output.write(
            Json.encodeToString(
                serializer = UserPreferencesData.serializer(),
                value = t,
            ).encodeToByteArray()
        )
}