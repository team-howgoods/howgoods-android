import com.android.build.api.dsl.ApplicationExtension
import com.cyanlch.convention.configureAndroidCompose
import com.cyanlch.convention.configureKotlinAndroid
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
                compileSdk = 35

                defaultConfig {
                    targetSdk = 35
                    minSdk = 31
                    versionCode = providers.gradleProperty("VERSION_CODE").map { it.toInt() }.get()
                    versionName = providers.gradleProperty("VERSION_NAME").get()
                }
                buildTypes {
                    getByName("release") {
                        isMinifyEnabled = true
                        proguardFiles(
                            getDefaultProguardFile("proguard-android-optimize.txt"),
                            "proguard-rules.pro"
                        )
                    }
                }
                configureKotlinAndroid(this)
                configureAndroidCompose(this)
            }

            impl("material")
        }
    }
}