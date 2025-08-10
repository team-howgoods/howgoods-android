plugins {
    alias(libs.plugins.cyanlch.android.library)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.cyanlch.data"
}

dependencies {
    api(projects.core.domain)

    implementation(libs.kotlinx.serialization.json)
    implementation(libs.kotlinx.coroutines.core)
}
