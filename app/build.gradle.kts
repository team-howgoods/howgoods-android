plugins {
    alias(libs.plugins.cyanlch.android.application.compose)
    alias(libs.plugins.cyanlch.android.application.firebase)
    alias(libs.plugins.cyanlch.circuit)
    alias(libs.plugins.cyanlch.hilt)
}

android {
    namespace = "com.cyanlch.howgoods"
    defaultConfig {
        applicationId = "com.cyanlch.howgoods"
    }
}

dependencies {
    implementation(projects.core.ui)
    implementation(projects.core.designsystem)
    implementation(projects.core.navigation)
    implementation(projects.core.data)
    implementation(projects.core.network)
    implementation(projects.core.domain)
    implementation(projects.core.datastore)

    implementation(projects.feature.shell.main)
    implementation(projects.feature.login)

    implementation(libs.splashscreen)
    implementation(libs.androidx.startup)
    implementation(libs.androidx.activity.compose)

    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.foundation)
}
