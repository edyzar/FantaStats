package com.example.fantastats.notification

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.fantastats.R

class ThirtyMinutes : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        val builder = NotificationCompat.Builder(context, "thirtyMinutes")
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentText("Round starts in 30 minutes.")
            .setContentTitle("Gameweek deadline")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
        val notificationManagerCompat = NotificationManagerCompat.from(context)
        notificationManagerCompat.notify(400, builder.build())
    }
}