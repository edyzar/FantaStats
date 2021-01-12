package com.example.fantastats.notification

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.fantastats.R

class Reminder : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        val builder = NotificationCompat.Builder(context, "notificationOne")
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentText("Remind One")
            .setContentTitle("sdfsdfsdfdsf")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
        val notificationManagerCompat = NotificationManagerCompat.from(context)
        notificationManagerCompat.notify(200, builder.build())
    }
}