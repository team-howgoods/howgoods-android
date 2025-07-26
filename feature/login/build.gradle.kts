plugins {
    alias(libs.plugins.cyanlch.feature)
}

android {
    namespace = "com.cyanlch.login"
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.browser)

    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.foundation)
}