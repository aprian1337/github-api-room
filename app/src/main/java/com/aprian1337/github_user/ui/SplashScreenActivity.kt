package com.aprian1337.github_user.ui

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.aprian1337.github_user.R
import com.aprian1337.github_user.ui.search.SearchActivity


class SplashScreenActivity : AppCompatActivity() {
    private var splashScreenTime:Long= 4000
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        Handler(Looper.getMainLooper()).postDelayed({
            val mainActivityIntent = Intent(this@SplashScreenActivity, SearchActivity::class.java)
            startActivity(mainActivityIntent)
            finish()
        }, splashScreenTime)
    }
}