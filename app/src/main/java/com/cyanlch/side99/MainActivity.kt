package com.cyanlch.side99

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import com.cyanlch.login.AppleLoginWithCustomTabsActivity

class MainActivity: ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        this.startActivity(Intent(this, AppleLoginWithCustomTabsActivity::class.java))
    }
}