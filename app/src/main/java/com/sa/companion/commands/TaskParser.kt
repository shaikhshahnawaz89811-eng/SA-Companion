package com.sa.companion.commands

class TaskParser {

    fun parse(command: String): List<Task> {

        val tasks = mutableListOf<Task>()

        val text = command.lowercase()

        if ("time" in text)
            tasks.add(Task("TIME", ""))

        if ("battery" in text)
            tasks.add(Task("BATTERY", ""))

        if ("volume" in text)
            tasks.add(Task("VOLUME", ""))

        if ("message" in text || "whatsapp" in text)
            tasks.add(Task("WHATSAPP", text))

        if (tasks.isEmpty())
            tasks.add(Task("AI", text))

        return tasks
    }
}
