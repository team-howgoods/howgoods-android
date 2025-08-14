plugins {
    alias(libs.plugins.cyanlch.android.library)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.cyanlch.datastore"
}

dependencies {
    api(projects.core.data)
    
    implementation(libs.androidx.datastore.preferences)
    implementation(libs.kotlinx.serialization.json)
}