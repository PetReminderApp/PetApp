package com.example.petapp

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.petapp.util.ParseUtil
import java.text.SimpleDateFormat
import java.time.Duration
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.util.*

class AlarmReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        try {
            showTaskNotifications(context)
        } catch (e: Exception) {
            Log.e(TAG, "onReceive: ${e.printStackTrace()}")
        }
    }

    private fun showTaskNotifications(context: Context) {
        //unique ID for each notification
        var notificationId = 0

        ParseUtil.queryPets { pet ->
            ParseUtil.queryPetTasks(
                pet,
                callback = { task ->
                    if (task.getCompleted() == false) {
                        //get curr time with same formatting as Task reminderTime
                        val currentTime = SimpleDateFormat("hh:mm a", Locale.getDefault()).format(Date())
                        val formattedReminderTime = task.getReminderTime()!!
                            .uppercase()
                            .padStart(8, '0') //4:10 pm -> 04:10 PM

                        val hoursUntilTask = getHourDifference(formattedReminderTime, currentTime)

                        if (hoursUntilTask in 0..2) { // 0 - 2:59 hours remaining until Task
                            //create notification for task
                            showNotification(context, "Incomplete Task", task.getTitle()!!, notificationId++)

                            Log.d(TAG, "Incomplete Task: ${task.getTitle()} in $hoursUntilTask hours")
                            Log.d(TAG, "$formattedReminderTime - $currentTime = ${getHourDifference(formattedReminderTime, currentTime)}\n\n")
                        }
                    }
                },
                {}
            )
        }
    }

    private fun getHourDifference(reminderTime: String, currentTime: String): Int {
        val timeFormatter: DateTimeFormatter =
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                DateTimeFormatter.ofPattern("hh:mm a", Locale.ENGLISH)
            } else {
                TODO("VERSION.SDK_INT < O")
            }

        val start: LocalTime = LocalTime.parse(currentTime, timeFormatter)
        val end: LocalTime = LocalTime.parse(reminderTime, timeFormatter)

        val diff: Duration = Duration.between(start, end)

        val hours: Long = diff.toHours()
        val minutes: Long = diff.minusHours(hours).toMinutes()

        return hours.toInt()
    }

    private fun showNotification(
        context: Context,
        title: String,
        description: String,
        notificationId: Int
    ) {
        val manager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        val i = Intent(context, MainActivity::class.java)
        //allows clicking notification to open app
        val pendingIntent = PendingIntent.getActivity(context, 0, i, 0)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel =
                NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_DEFAULT)
            manager.createNotificationChannel(channel)
        }

        val notification = NotificationCompat.Builder(context, channelId)
            .setContentTitle(title)
            .setContentText(description)
            .setSmallIcon(R.drawable.ic_chat)
            .setAutoCancel(true)
            .setDefaults(NotificationCompat.DEFAULT_ALL)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setContentIntent(pendingIntent)

        val notificationManager = NotificationManagerCompat.from(context)

        notificationManager.notify(notificationId, notification.build())
    }

    companion object {
        val channelId = "channelId"
        val channelName = "channelName"
        const val TAG = "AlarmReceiver"
    }
}