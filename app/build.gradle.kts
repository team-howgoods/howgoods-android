plugins {
    alias(libs.plugins.cyanlch.android.application.compose)
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

    implementation(project(":feature:login"))
}