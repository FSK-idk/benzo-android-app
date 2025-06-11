package com.benzo.benzomobile.presentation.screen.service

import android.util.Log
import com.benzo.benzomobile.app.TAG
import com.benzo.benzomobile.domain.model.MobileAppConnectMessage
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.jsonObject
import okhttp3.Response
import okhttp3.WebSocket
import okhttp3.WebSocketListener
import okio.ByteString

class CustomWebSocketListener : WebSocketListener() {
    private val _json = Json { encodeDefaults = true }

    override fun onOpen(webSocket: WebSocket, response: Response) {
        val message = MobileAppConnectMessage()
        Log.d(TAG, _json.encodeToString(message))
        webSocket.send(text = _json.encodeToString(message))
    }

    override fun onMessage(webSocket: WebSocket, text: String) {
        val json = Json.parseToJsonElement(text)
        val map = json.jsonObject.toMap()

        val messageType = map["message_type"]

        Log.d(TAG, "MESSAGE: $text $messageType")
    }

    override fun onMessage(webSocket: WebSocket, bytes: ByteString) {
        Log.d(TAG, "MESSAGE: ${bytes.hex()}")
    }

    override fun onClosing(webSocket: WebSocket, code: Int, reason: String) {
        webSocket.close(1000, null);
        Log.d(TAG, "CLOSE: $code $reason")
    }

    override fun onFailure(webSocket: WebSocket, t: Throwable, response: Response?) {
        t.printStackTrace();
    }
}