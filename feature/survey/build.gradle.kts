plugins {
    alias(libs.plugins.cyanlch.feature)
}

android {
    namespace = "com.cyanlch.survey"
}

dependencies {
    implementation(projects.core.navigation)

    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.foundation)

}