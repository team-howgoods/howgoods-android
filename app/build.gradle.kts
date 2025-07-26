plugins {
    alias(libs.plugins.cyanlch.android.application.compose)
    alias(libs.plugins.cyanlch.circuit)
    alias(libs.plugins.cyanlch.hilt)
}

android {
    namespace = "com.cyanlch.side99"
    defaultConfig {
        applicationId = "com.cyanlch.side99"
    }
}

dependencies {
    implementation(libs.splashscreen)
    implementation(libs.androidx.activity.compose)

    implementation(projects.core.ui)
    implementation(projects.feature.login)
}