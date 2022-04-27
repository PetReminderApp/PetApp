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
import java.lang.Exception

class AlarmReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        try {
            showNotification(context, "Title", "description")
        } catch (e: Exception) {
            Log.e(TAG, "onReceive: ${e.printStackTrace()}")
        }
    }

    private fun showNotification(context: Context, title: String, description: String) {
        val manager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        val i = Intent(context, MainActivity::class.java)
        //allows clicking notification to open app
        val pendingIntent = PendingIntent.getActivity(context, 0, i, 0)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_DEFAULT)
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

        notificationManager.notify(1, notification.build())
    }

    companion object {
        val channelId = "channelId"
        val channelName = "channelName"
        const val TAG = "AlarmReceiver"
    }
}