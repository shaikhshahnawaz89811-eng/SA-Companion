package com.sa.companion.commands

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class TaskExecutor {

    fun execute(tasks: List<Task>): String {

        val result = StringBuilder()

        for (task in tasks) {

            when (task.type) {

                "TIME" -> {
                    val time = SimpleDateFormat(
                        "hh:mm a",
                        Locale.getDefault()
                    ).format(Date())

                    result.append("Abhi time $time hai\n")
                }

                "BATTERY" ->
                    result.append("Battery module ready\n")

                "VOLUME" ->
                    result.append("Volume module ready\n")

                "WHATSAPP" ->
                    result.append("WhatsApp module ready\n")

                "AI" ->
                    result.append("AI se connect kar raha hu\n")
            }
        }

        return result.toString()
    }
}
