package com.cyanlch.side99

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen

class RoutingActivity: ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        this.startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
    }
}