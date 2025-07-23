plugins {
    alias(libs.plugins.cyanlch.android.library.compose)
}

android {
    namespace = "com.cyanlch.login"

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
}

dependencies {
}