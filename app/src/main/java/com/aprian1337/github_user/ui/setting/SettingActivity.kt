package com.aprian1337.github_user.ui.setting

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import com.aprian1337.github_user.data.pref.ReminderPreference
import com.aprian1337.github_user.databinding.ActivitySettingBinding

class SettingActivity : AppCompatActivity(){

    private lateinit var binding: ActivitySettingBinding
    private lateinit var mReminderPreference: ReminderPreference
    private lateinit var alarmReceiver: AlarmReceiver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.title = "Settings"
        alarmReceiver = AlarmReceiver()
        mReminderPreference = ReminderPreference(this)
        val isChecked = mReminderPreference.getReminder()

        if (isChecked) {
            binding.toggleButton.isChecked = true
        }

        binding.tvChangeLanguage.setOnClickListener {
            Intent(Settings.ACTION_LOCALE_SETTINGS).apply {
                startActivity(this)
            }
        }

        binding.toggleButton.setOnCheckedChangeListener { _ , btnIsCheck: Boolean ->
            val reminderPreference = ReminderPreference(this)
            if (btnIsCheck) {
                reminderPreference.setReminder(true)
                alarmReceiver.setRepeatingAlarm(this, binding.root)
            } else {
                reminderPreference.setReminder(false)
                alarmReceiver.setCancelRepeatingAlarm(this, binding.root)
            }
        }
    }

}