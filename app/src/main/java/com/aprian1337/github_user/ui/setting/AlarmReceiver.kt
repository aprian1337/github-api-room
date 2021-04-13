package com.aprian1337.github_user.ui.setting

import android.app.AlarmManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import android.os.Build
import android.view.View
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import com.aprian1337.github_user.R
import com.aprian1337.github_user.ui.search.SearchActivity
import com.aprian1337.github_user.utils.Constants
import com.google.android.material.snackbar.Snackbar
import java.util.*

class AlarmReceiver : BroadcastReceiver() {

    companion object {
        const val EXTRA_MESSAGE = "message"
    }

    override fun onReceive(context: Context, intent: Intent) {
        showAlarmNotification(context)
    }

    fun setRepeatingAlarm(context: Context, view: View) {
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(context, AlarmReceiver::class.java)
        val reminderMessage = context.getString(R.string.reminder_message)
        intent.putExtra(EXTRA_MESSAGE, reminderMessage)
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.HOUR_OF_DAY, Constants.HOUR_TIME)
        calendar.set(Calendar.MINUTE, Constants.MINUTE_TIME)
        calendar.set(Calendar.SECOND, Constants.SECOND_TIME)
        val pendingIntent = PendingIntent.getBroadcast(context, Constants.ID_REPEATING, intent, 0)
        alarmManager.setInexactRepeating(
            AlarmManager.RTC_WAKEUP,
            calendar.timeInMillis,
            AlarmManager.INTERVAL_DAY,
            pendingIntent
        )
        showSnackbar(view, true)
    }

    fun setCancelRepeatingAlarm(context: Context, view: View) {
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(context, AlarmReceiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(context, Constants.ID_REPEATING, intent, 0)
        alarmManager.cancel(pendingIntent)
        showSnackbar(view, false)
    }


    private fun showAlarmNotification(
        context: Context
    ) {
        val intent = Intent(context, SearchActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val title = context.getString(R.string.app_name)
        val pendingIntent: PendingIntent = PendingIntent.getActivity(context, 0, intent, 0)
        val channelId = "channel_01"
        val channelName = "Reminder Channel"
        val reminderMessage = context.getString(R.string.reminder_message)
        val notificationManagerCompat =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
        val builder = NotificationCompat.Builder(context, channelId)
            .setSmallIcon(R.drawable.ic_baseline_favorite_24)
            .setContentTitle(title)
            .setContentIntent(pendingIntent)
            .setColor(ContextCompat.getColor(context, android.R.color.transparent))
            .setVibrate(longArrayOf(1000, 1000, 1000, 1000, 1000))
            .setStyle(NotificationCompat.BigTextStyle().bigText(reminderMessage))
            .setSound(alarmSound)
            .setAutoCancel(true)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                channelId,
                channelName,
                NotificationManager.IMPORTANCE_DEFAULT
            )
            channel.enableVibration(true)
            channel.vibrationPattern = longArrayOf(1000, 1000, 1000, 1000, 1000)
            builder.setChannelId(channelId)
            notificationManagerCompat.createNotificationChannel(channel)
        }
        val notification = builder.build()
        notificationManagerCompat.notify(Constants.ID_REPEATING, notification)
    }

    private fun showSnackbar(view: View, isSet: Boolean) {
        val snackBar = Snackbar.make(
            view, if (isSet) "Reminder has been set" else "Reminder has been canceled",
            Snackbar.LENGTH_LONG
        )
        with(snackBar) {
            this.setAction("Dismiss") {
                this.dismiss()
            }
            this.show()
        }
    }
}