package com.sa.companion.commands

class TaskParser {

    fun parse(command: String): List<Task> {

        val tasks = mutableListOf<Task>()

        val text = command
            .lowercase()
            .trim()

        if (text.contains("time")) {
            tasks.add(Task("TIME", command))
        }

        if (text.contains("battery")) {
            tasks.add(Task("BATTERY", command))
        }

        if (text.contains("volume")) {
            tasks.add(Task("VOLUME", command))
        }

        if (
            text.contains("message") ||
            text.contains("whatsapp")
        ) {
            tasks.add(Task("WHATSAPP", command))
        }

        if (tasks.isEmpty()) {
            tasks.add(Task("AI", command))
        }

        return tasks
    }
}
