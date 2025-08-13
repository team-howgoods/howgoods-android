plugins {
    alias(libs.plugins.cyanlch.android.library)
    alias(libs.plugins.secrets)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.cyanlch.network"
    buildFeatures {
        buildConfig = true
    }
}

dependencies {
    api(projects.core.data)
    implementation(platform(libs.ktor.bom))
    implementation(libs.bundles.ktor)
    implementation(libs.kotlinx.serialization.json)
}