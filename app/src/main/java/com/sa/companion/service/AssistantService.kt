package com.sa.companion.service

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log

class AssistantService : Service() {

    override fun onCreate() {
        super.onCreate()
        Log.d("SA", "Assistant Service Started")
    }

    override fun onStartCommand(
        intent: Intent?,
        flags: Int,
        startId: Int
    ): Int {

        Log.d("SA", "Assistant Running...")

        // Yahin baad me:
        // Wake Word
        // Audio Recorder
        // Groq
        // Kokoro
        // Offline AI
        // Command Router connect honge

        return START_STICKY
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("SA", "Assistant Stopped")
    }
}
