plugins {
    alias(libs.plugins.cyanlch.android.library)
    alias(libs.plugins.cyanlch.android.library.compose)
    alias(libs.plugins.cyanlch.circuit)
    alias(libs.plugins.cyanlch.hilt)
}

android {
    namespace = "com.cyanlch.ui"
}

dependencies {
    api(projects.core.designsystem)
}