plugins {
    alias(libs.plugins.cyanlch.android.library)
    alias(libs.plugins.cyanlch.android.library.compose)
}

android {
    namespace = "com.cyanlch.designsystem"
}

dependencies {
    api(libs.androidx.compose.foundation)
    api(libs.androidx.compose.ui)
    api(libs.androidx.compose.material3)
}