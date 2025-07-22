plugins {
    alias(libs.plugins.cyanlch.android.application.compose)
}

android {
    namespace = "com.cyanlch.side99"
    defaultConfig {
        applicationId = "com.cyanlch.side99"

        versionCode = 1
        versionName = "1.0"
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
}

dependencies {
}