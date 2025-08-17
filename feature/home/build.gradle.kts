plugins {
    alias(libs.plugins.cyanlch.feature)
}

android {
    namespace = "com.cyanlch.home"
}

dependencies {
    implementation(projects.core.ui)
    implementation(projects.core.navigation)
    implementation(projects.feature.shell.main)
}