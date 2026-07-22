package com.sa.companion.commands

import android.content.Context
import android.os.BatteryManager
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class TaskExecutor(private val context: Context) {

    fun execute(tasks: List<Task>): String {

        if (tasks.isEmpty()) {
            return "Mujhe command samajh nahi aayi."
        }

        val reply = StringBuilder()

        for (task in tasks) {

            when (task.type) {

                "TIME" -> {

                    val time = SimpleDateFormat(
                        "hh:mm a",
                        Locale.getDefault()
                    ).format(Date())

                    reply.append("Abhi $time baj rahe hain.\n")
                }


                "BATTERY" -> {

                    val manager =
                        context.getSystemService(Context.BATTERY_SERVICE)
                                as BatteryManager

                    val battery =
                        manager.getIntProperty(
                            BatteryManager.BATTERY_PROPERTY_CAPACITY
                        )

                    reply.append(
                        "Battery $battery percent hai.\n"
                    )
                }


                "VOLUME" -> {

                    reply.append(
                        "Volume control module ready hai.\n"
                    )
                }


                "WHATSAPP" -> {

                    reply.append(
                        "WhatsApp module ready hai.\n"
                    )
                }


                "AI" -> {

                    reply.append(
                        "AI module se connect karunga.\n"
                    )
                }


                else -> {

                    reply.append(
                        "Command nahi mili.\n"
                    )
                }
            }
        }

        return reply.toString().trim()
    }
}
