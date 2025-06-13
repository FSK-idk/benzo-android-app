package com.benzo.benzomobile.presentation.screen.service

import android.util.Log
import com.benzo.benzomobile.app.TAG
import com.benzo.benzomobile.domain.model.GasNozzleUsedT2Message
import com.benzo.benzomobile.domain.model.MessageType
import com.benzo.benzomobile.domain.model.MobileAppConnectMessage
import com.benzo.benzomobile.domain.model.MobileAppSavePaymentMessage
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromJsonElement
import kotlinx.serialization.json.jsonObject
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.WebSocket
import okhttp3.WebSocketListener
import okio.ByteString
import java.util.concurrent.TimeUnit

class WebSocketClient(
    stationId: String,
    onServiceStart: () -> Unit,
    onServiceEnd: () -> Unit,
    onMobileAppUsed: () -> Unit,
) {
    private val _json = Json { encodeDefaults = true }

    private val _client: OkHttpClient = OkHttpClient.Builder()
        .readTimeout(0, TimeUnit.MILLISECONDS)
        .build();

    private val _request: Request = Request.Builder()
        .url("ws://10.0.2.2:8000/api/ws/station/$stationId")
        .header("Origin", "http://10.0.2.2:8000")
        .build();

    private val _listener = object : WebSocketListener() {
        override fun onOpen(webSocket: WebSocket, response: Response) {
            Log.d(TAG, "onOpen")
            val message = MobileAppConnectMessage()
            webSocket.send(text = _json.encodeToString(message))
        }

        override fun onMessage(webSocket: WebSocket, text: String) {
            Log.d(TAG, "LISTENER onMessage text = $text")

            val json = Json.parseToJsonElement(text)
            val map = json.jsonObject.toMap()

            val messageType = _json.decodeFromJsonElement<String>(map["message_type"]!!)
            Log.d(TAG, "GOT MESSAGE: $messageType")

            when (messageType) {
                MessageType.MOBILE_APP_CONNECTED.value -> {
                    onServiceStart()
                }
                MessageType.MOBILE_APP_USED_T2.value -> {
                    onMobileAppUsed()
                }
                else -> {
                    Log.d(TAG, "UNKNOWN MESSAGE")
                }
            }
        }

        override fun onMessage(webSocket: WebSocket, bytes: ByteString) {
            Log.d(TAG, "LISTENER onMessage bytes = ${bytes.hex()}")
        }

        override fun onClosing(webSocket: WebSocket, code: Int, reason: String) {
            Log.d(TAG, "LISTENER onClosing")
            webSocket.close(1000, null);
            onServiceEnd()
        }

        override fun onFailure(webSocket: WebSocket, t: Throwable, response: Response?) {
            Log.d(TAG, "LISTENER onFailure")
            onServiceEnd()
        }
    }

    private var _webSocket: WebSocket? = null

    fun start() {
        _webSocket = _client.newWebSocket(_request, _listener)
    }

    fun stop() {
        _webSocket?.close(1000, null)
    }

    fun savePayment(message: MobileAppSavePaymentMessage) {
        _webSocket?.send(text = _json.encodeToString(message))
    }

    fun useGasNozzle() {
        val message = GasNozzleUsedT2Message()
        _webSocket?.send(text = _json.encodeToString(message))
    }
}