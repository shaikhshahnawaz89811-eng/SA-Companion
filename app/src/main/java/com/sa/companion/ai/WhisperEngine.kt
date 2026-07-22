package com.sa.companion.ai

class WhisperEngine {

    companion object {

        init {
            System.loadLibrary("sa")
        }
    }

    external fun test(): String
}
