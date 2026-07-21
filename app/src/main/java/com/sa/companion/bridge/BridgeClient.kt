package com.sa.companion.bridge

import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody

class BridgeClient {

    private val client = OkHttpClient()

    fun chat(text: String): String {

        val json = """
            {
                "text":"$text"
            }
        """.trimIndent()

        val body = json.toRequestBody(
            "application/json".toMediaType()
        )

        val request = Request.Builder()
            .url("http://127.0.0.1:8080/chat")
            .post(body)
            .build()

        return try {

            client.newCall(request)
                .execute()
                .body
                ?.string()

                ?: "No Response"

        } catch (e: Exception) {

            e.message ?: "Connection Error"

        }

    }

}
