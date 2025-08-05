import com.android.build.api.dsl.ApplicationExtension
import com.cyanlch.convention.configureAndroidCompose
import com.cyanlch.convention.configureKotlinAndroid
import com.cyanlch.convention.getSigningValue
import com.cyanlch.convention.impl
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class AndroidApplicationComposeConventionPlugin: Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.application")
                apply("org.jetbrains.kotlin.android")
                apply("org.jetbrains.kotlin.plugin.compose")
            }
            extensions.configure<ApplicationExtension> {
                defaultConfig {
                    versionCode = providers.gradleProperty("VERSION_CODE").map { it.toInt() }.get()
                    versionName = providers.gradleProperty("VERSION_NAME").get()
                }
                signingConfigs {
                    getByName("debug") {
                        val storeFilePath = getSigningValue(rootProject, "DEBUG_STORE_FILE")
                        if (storeFilePath.isNotEmpty()) {
                            storeFile = rootProject.file(storeFilePath)
                        }
                        storePassword = getSigningValue(rootProject, "DEBUG_STORE_PASSWORD")
                        keyAlias = getSigningValue(rootProject, "DEBUG_KEY_ALIAS")
                        keyPassword = getSigningValue(rootProject, "DEBUG_KEY_PASSWORD")
                    }
                    create("release") {
                        val storeFilePath = getSigningValue(rootProject, "RELEASE_STORE_FILE")
                        if (storeFilePath.isNotEmpty()) {
                            storeFile = rootProject.file(storeFilePath)
                        }
                        storePassword = getSigningValue(rootProject, "RELEASE_STORE_PASSWORD")
                        keyAlias = getSigningValue(rootProject, "RELEASE_KEY_ALIAS")
                        keyPassword = getSigningValue(rootProject, "RELEASE_KEY_PASSWORD")
                    }
                }
                buildTypes {
                    getByName("release") {
                        isMinifyEnabled = true
                        proguardFiles(
                            getDefaultProguardFile("proguard-android-optimize.txt"),
                            "proguard-rules.pro"
                        )
                        signingConfig = signingConfigs.getByName("release")
                    }
                    getByName("debug") {
                        signingConfig = signingConfigs.getByName("debug")
                    }
                }
                configureKotlinAndroid(this)
                configureAndroidCompose(this)
            }

            impl("material")
        }
    }
}