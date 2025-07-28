import com.android.build.gradle.api.AndroidBasePlugin
import com.cyanlch.convention.impl
import com.cyanlch.convention.ksp
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply

class HiltConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            apply(plugin = "com.google.devtools.ksp")
            ksp("hilt-compiler")

            /** Add support for Android modules, based on [AndroidBasePlugin] */
            pluginManager.withPlugin("com.android.base") {
                apply(plugin = "dagger.hilt.android.plugin")
                impl("hilt-android")
            }
        }
    }
}