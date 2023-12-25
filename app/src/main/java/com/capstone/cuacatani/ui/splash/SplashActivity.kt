package com.capstone.cuacatani.ui.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.capstone.cuacatani.R
import com.capstone.cuacatani.ui.login.LoginActivity
import com.capstone.cuacatani.ui.main.MainActivity
import com.capstone.cuacatani.ui.welcome.WelcomeActivity

class SplashActivity : AppCompatActivity() {

    private val SPLASH_TIME_OUT: Long = 1000
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler().postDelayed({
            val mainIntent = Intent(this@SplashActivity, WelcomeActivity::class.java)
            startActivity(mainIntent)

            finish()
        }, SPLASH_TIME_OUT)
    }
}