package com.cyanlch.login.social

import android.content.Context

interface SocialLogin {
    suspend fun login(context: Context): Result<String>
    fun logout()
}
