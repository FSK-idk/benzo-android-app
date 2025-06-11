package com.benzo.benzomobile.presentation.screen.service

import com.benzo.benzomobile.domain.model.MobileAppCancelRefuelingMessage
import kotlinx.serialization.json.Json
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.WebSocket
import java.util.concurrent.TimeUnit

class WebSocketClient(
    stationId: String
) {
    private val _json = Json { encodeDefaults = true }

    private val _client: OkHttpClient = OkHttpClient.Builder()
        .readTimeout(0, TimeUnit.MILLISECONDS)
        .build();

    private val _request: Request = Request.Builder()
        .url("ws://10.0.2.2:8000/api/ws/station/$stationId")
        .header("Origin", "http://10.0.2.2:8000")
        .build();

    private val _listener: CustomWebSocketListener = CustomWebSocketListener()

    private var _webSocket: WebSocket? = null

    fun start() {
        _webSocket = _client.newWebSocket(_request, _listener)
    }

    fun sendCancelRefueling() {
        val message = MobileAppCancelRefuelingMessage()
        _webSocket?.send(text = _json.encodeToString(message))
    }

    fun send() {
        _webSocket?.send("TEST")
    }

    fun stop() {
        _webSocket?.close(1000, null)
    }
}