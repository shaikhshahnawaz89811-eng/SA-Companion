package com.sa.companion.ai

import okhttp3.*
import java.io.File

class GroqWhisper(private val apiKey: String) {

    private val client = OkHttpClient()

    fun transcribe(audioFile: File): String? {

        val requestBody = MultipartBody.Builder()
            .setType(MultipartBody.FORM)
            .addFormDataPart(
                "file",
                audioFile.name,
                audioFile.asRequestBody("audio/mp4".toMediaType())
            )
            .addFormDataPart(
                "model",
                "whisper-large-v3"
            )
            .build()

        val request = Request.Builder()
            .url("https://api.groq.com/openai/v1/audio/transcriptions")
            .addHeader("Authorization", "Bearer $apiKey")
            .post(requestBody)
            .build()

        client.newCall(request).execute().use { response ->
            return response.body?.string()
        }
    }
}
