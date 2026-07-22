package com.sa.companion

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.speech.RecognitionListener
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import com.sa.companion.commands.TaskParser
import com.sa.companion.commands.TaskExecutor
import android.speech.tts.TextToSpeech
import java.util.Locale
import android.media.MediaPlayer
import java.io.File
import java.net.URL

class MainActivity : ComponentActivity() {
    
    private lateinit var tts: TextToSpeech
    
    private lateinit var speechRecognizer: SpeechRecognizer

    private var mediaPlayer: MediaPlayer? = null

    private var spokenText by mutableStateOf("AI Assistant Ready")
    private var isListening by mutableStateOf(false)

    private val permissionLauncher =
        registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { granted ->
            if (granted) {
                startListening()
            } else {
                spokenText = "Microphone permission denied"
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        tts = TextToSpeech(this) {
            if (it == 
        TextToSpeech.SUCCESS) {
                tts.language = 
        Locale("hi", "IN")
            }
        }

        speechRecognizer = SpeechRecognizer.createSpeechRecognizer(this)

        speechRecognizer.setRecognitionListener(object : RecognitionListener {

            override fun onReadyForSpeech(params: Bundle?) {
                isListening = true
                spokenText = "Listening..."
            }

            override fun onBeginningOfSpeech() {}

            override fun onRmsChanged(rmsdB: Float) {}

            override fun onBufferReceived(buffer: ByteArray?) {}

            override fun onEndOfSpeech() {
                isListening = false
            }

            override fun onError(error: Int) {
                isListening = false
                spokenText = "Speech Error: $error"
            }

            override fun onResults(results: Bundle?) {
                isListening = false

                val text = results
                    ?.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION)
                    ?.firstOrNull()

                val command = text ?: "Nothing heard"

                val tasks = TaskParser().parse(command)

                val result = TaskExecutor().execute(tasks)

                spokenText = """
                You Said:
                $command

                $result
                """.trimIndent()

                playSAVoice()

                }

            override fun onPartialResults(partialResults: Bundle?) {}

            override fun onEvent(eventType: Int, params: Bundle?) {}
        })

        setContent {
            SACompanionApp()
        }
    }

    private fun startListening() {

        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.RECORD_AUDIO
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            permissionLauncher.launch(Manifest.permission.RECORD_AUDIO)
            return
        }

        val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH).apply {
            putExtra(
                RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM
            )
            putExtra(RecognizerIntent.EXTRA_LANGUAGE, "en-IN")
        }

        speechRecognizer.startListening(intent)
    }

    private fun playSAVoice() {

    Thread {

        try {

            val url = URL("http://127.0.0.1:8080/voice")

            val file = File(cacheDir, "sa_voice.wav")

            url.openStream().use { input ->
                file.outputStream().use { output ->
                    input.copyTo(output)
                }
            }

            runOnUiThread {

                mediaPlayer?.release()

                mediaPlayer = MediaPlayer().apply {
                    setDataSource(file.absolutePath)
                    prepare()
                    start()
                }

            }

        } catch (e: Exception) {
            e.printStackTrace()
        }

    }.start()
}

    override fun onDestroy() {
        super.onDestroy()
        speechRecognizer.destroy()
    }

    @Composable
    fun SACompanionApp() {

        MaterialTheme {

            Surface(
                modifier = Modifier.fillMaxSize()
            ) {

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {

                    Text(
                        text = "SA Companion",
                        style = MaterialTheme.typography.headlineLarge
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    Text(spokenText)

                    Spacer(modifier = Modifier.height(30.dp))

                    Button(
                        onClick = {
                            startListening()
                        }
                    ) {
                        Text(
                            if (isListening)
                                "Listening..."
                            else
                                "Start Listening"
                        )
                    }
                }
            }
        }
    }
}
