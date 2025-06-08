package com.benzo.benzomobile.data.storage.user_preferences

import android.util.Log
import androidx.datastore.core.Serializer
import com.benzo.benzomobile.app.TAG
import com.benzo.benzomobile.domain.model.ThemeOption
import com.benzo.benzomobile.domain.model.UserPreferences
import kotlinx.serialization.json.Json
import java.io.InputStream
import java.io.OutputStream

class UserPreferencesSerializer : Serializer<UserPreferences> {
    override val defaultValue
        get() = UserPreferences(
            token = null,
            theme = ThemeOption.SYSTEM,
        )

    override suspend fun readFrom(input: InputStream): UserPreferences =
        try {
            Json.decodeFromString(
                deserializer = UserPreferences.serializer(),
                string = input.readBytes().decodeToString(),
            )
        } catch (e: Exception) {
            Log.e(TAG, "$e")
            defaultValue
        }

    override suspend fun writeTo(t: UserPreferences, output: OutputStream) =
        output.write(
            Json.encodeToString(
                serializer = UserPreferences.serializer(),
                value = t,
            ).encodeToByteArray()
        )
}