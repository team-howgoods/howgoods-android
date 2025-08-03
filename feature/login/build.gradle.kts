plugins {
    alias(libs.plugins.cyanlch.feature)
    alias(libs.plugins.secrets)
}

android {
    namespace = "com.cyanlch.login"
    buildFeatures {
        buildConfig = true
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.browser)

    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.foundation)

    implementation(libs.kakao.v2.user)
    implementation(libs.androidx.startup)
}