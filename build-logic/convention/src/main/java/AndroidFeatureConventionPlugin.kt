import com.cyanlch.convention.libs
import com.google.devtools.ksp.gradle.KspExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

class AndroidFeatureConventionPlugin: Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.cyanlch.convention.library")
                apply("com.cyanlch.convention.library.compose")
                apply("com.cyanlch.convention.circuit")
                apply("com.cyanlch.convention.hilt")
            }

            pluginManager.withPlugin("com.google.devtools.ksp") {
                extensions.configure<KspExtension> {
                    arg("dagger.experimentalDaggerErrorMessages", "enabled")
                }
            }

            dependencies {
                "implementation"(project(":core:ui"))
                "implementation"(project(":core:domain"))
                "implementation"(project(":core:navigation"))

                "implementation"(libs.findLibrary("androidx.hilt.navigation.compose").get())
                "implementation"(libs.findLibrary("androidx.lifecycle.runtime.compose").get())
                "implementation"(libs.findLibrary("androidx.lifecycle.viewmodel.compose").get())
                "implementation"(libs.findLibrary("androidx.navigation.compose").get())
                "implementation"(libs.findLibrary("kotlinx.serialization.json").get())
                "implementation"(libs.findLibrary("androidx.tracing.ktx").get())
            }
        }
    }
}