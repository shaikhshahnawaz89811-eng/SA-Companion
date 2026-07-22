package com.sa.companion.service

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Intent
import android.os.Build
import android.os.IBinder
import androidx.core.app.NotificationCompat
import com.sa.companion.R

class AssistantService : Service() {

    companion object {
        private const val CHANNEL_ID = "sa_companion"
    }

    override fun onCreate() {
        super.onCreate()

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                CHANNEL_ID,
                "SA Companion",
                NotificationManager.IMPORTANCE_LOW
            )

            val manager =
                getSystemService(NotificationManager::class.java)

            manager.createNotificationChannel(channel)
        }

        val notification: Notification =
            NotificationCompat.Builder(this, CHANNEL_ID)
                .setContentTitle("SA Companion")
                .setContentText("Assistant is running")
                .setSmallIcon(R.drawable.ic_sa)
                .setOngoing(true)
                .build()

        startForeground(1, notification)
    }

    override fun onStartCommand(
        intent: Intent?,
        flags: Int,
        startId: Int
    ): Int {

        // Agle step me yahin se
        // AudioRecorder
        // Whisper
        // VAD
        // Brain
        // start honge.

        return START_STICKY
    }

    override fun onBind(intent: Intent?): IBinder? = null
}
