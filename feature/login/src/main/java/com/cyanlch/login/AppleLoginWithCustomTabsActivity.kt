package com.cyanlch.login


import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.browser.customtabs.CustomTabsIntent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import java.security.SecureRandom
import java.util.Base64

class AppleLoginWithCustomTabsActivity: ComponentActivity() {

    private var currentState: String? = null

    private val clientId = "net.sytes.goods"
    private val redirectUri = "https://goods.sytes.net/api/auth/apple/callback"

    private var loginInfo: String = "로그인 안됨"

    companion object {
        private const val TAG = "AppleLogin"
        private const val STATE_KEY = "apple_login_state"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            var loginText by remember { mutableStateOf(loginInfo) }
            LaunchedEffect(key1 = loginInfo) {
                loginText = loginInfo
            }

            Scaffold(
                modifier = Modifier.fillMaxSize()
            ) { padding ->
                Box(
                    modifier = Modifier.fillMaxSize().padding(padding).background(Color.Gray)
                ) {
                    Text(
                        text = loginText
                    )
                }
            }
        }

        currentState = savedInstanceState?.getString(STATE_KEY) ?: generateState()
        val appleAuthUri = Uri.Builder()
            .scheme("https")
            .authority("appleid.apple.com")
            .appendPath("auth")
            .appendPath("authorize")
            .appendQueryParameter("response_mode", "form_post")
            .appendQueryParameter("response_type", "code id_token")
            .appendQueryParameter("scope", "name email")
            .appendQueryParameter("client_id", clientId)
            .appendQueryParameter("redirect_uri", redirectUri)
            .appendQueryParameter("state", currentState)
            .build()

        val customTabsIntent = CustomTabsIntent.Builder().build()
        customTabsIntent.launchUrl(this, appleAuthUri)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(STATE_KEY, currentState)
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        setIntent(intent)
        Log.d(TAG, "intent data : ${intent.data}")

        intent.data?.let { uri ->
            loginInfo = intent.data.toString()

            parseUri(uri)
        }
    }

    private fun parseUri(uri: Uri) {
        val token = uri.getQueryParameter("token")
        Log.d(TAG, "token : $token")
    }

    private fun generateState(): String {
        val random = SecureRandom()
        val bytes = ByteArray(16)
        random.nextBytes(bytes)
        return Base64.getUrlEncoder().withoutPadding().encodeToString(bytes)
    }
}