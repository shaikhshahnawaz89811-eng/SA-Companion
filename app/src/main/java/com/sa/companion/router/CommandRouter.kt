package com.sa.companion.router

class CommandRouter {

    fun route(command: String): String {

        val text = command.lowercase().trim()

        return when {

            text.contains("time") ->
                "TIME"

            text.contains("battery") ->
                "BATTERY"

            text.contains("volume") ->
                "VOLUME"

            text.contains("message") ||
            text.contains("whatsapp") ->
                "WHATSAPP"

            else ->
                "GROQ"
        }
    }
}
