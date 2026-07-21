package com.sa.companion.commands

class TaskExecutor {

    fun execute(tasks: List<Task>): String {

        val result = StringBuilder()

        for (task in tasks) {

            when (task.type) {

                "TIME" ->
                    result.append("Time command detected\n")

                "BATTERY" ->
                    result.append("Battery command detected\n")

                "VOLUME" ->
                    result.append("Volume command detected\n")

                "WHATSAPP" ->
                    result.append("WhatsApp command detected\n")

                "AI" ->
                    result.append("Sending to AI\n")
            }
        }

        return result.toString()
    }
}
