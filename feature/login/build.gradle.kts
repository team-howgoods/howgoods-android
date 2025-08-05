import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties
import java.util.Properties

plugins {
    alias(libs.plugins.cyanlch.feature)
    alias(libs.plugins.secrets)
}

android {
    namespace = "com.cyanlch.login"
    buildFeatures {
        buildConfig = true
    }

    defaultConfig {
        val props: Properties = gradleLocalProperties(rootDir, providers)
        val kakaoKey = props.getProperty("KAKAO_NATIVE_APP_KEY") ?: error(
            "KAKAO_NATIVE_APP_KEY not found in local.properties"
        )
        manifestPlaceholders += mapOf(
            "KAKAO_NATIVE_APP_KEY" to kakaoKey
        )
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
    implementation(libs.naver.nid)
    implementation(libs.androidx.startup)

    androidTestImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
}