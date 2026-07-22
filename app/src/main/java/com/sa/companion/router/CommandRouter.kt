package com.sa.companion.router

class CommandRouter {

    data class RouteResult(
        val action: String,
        val originalCommand: String
    )

    fun route(command: String): RouteResult {

        val text = command.lowercase().trim()

        return when {

            text.contains("time") ->
                RouteResult("TIME", command)

            text.contains("battery") ->
                RouteResult("BATTERY", command)

            text.contains("volume") ->
                RouteResult("VOLUME", command)

            text.contains("message") ||
            text.contains("whatsapp") ->
                RouteResult("WHATSAPP", command)

            else ->
                RouteResult("GROQ", command)
        }
    }
}
