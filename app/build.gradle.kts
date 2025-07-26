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
    implementation(projects.core.ui)
    implementation(projects.core.designsystem)
    implementation(projects.feature.login)

    implementation(libs.splashscreen)
    implementation(libs.androidx.activity.compose)

    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.foundation)
}