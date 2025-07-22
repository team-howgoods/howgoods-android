plugins {
    alias(libs.plugins.cyanlch.android.application.compose)
}

android {
    namespace = "com.cyanlch.side99"
    defaultConfig {
        applicationId = "com.cyanlch.side99"
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