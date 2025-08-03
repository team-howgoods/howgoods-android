package com.cyanlch.login.social.naver

import android.content.Context
import com.cyanlch.login.BuildConfig
import androidx.startup.Initializer
import com.navercorp.nid.NaverIdLoginSDK

class NaverInitializer : Initializer<Unit> {
    override fun create(context: Context) {
        val appName = context.applicationInfo.loadLabel(context.packageManager).toString()

        NaverIdLoginSDK.initialize(
            context = context,
            clientId = BuildConfig.NAVER_CLIENT_ID,
            clientSecret = BuildConfig.NAVER_CLIENT_SECRET,
            clientName = appName
        )
    }
    override fun dependencies(): List<Class<out Initializer<*>>> = emptyList()
}