import com.cyanlch.convention.bundle
import org.gradle.api.Plugin
import org.gradle.api.Project
import com.cyanlch.convention.ksp
import com.google.devtools.ksp.gradle.KspExtension
import org.gradle.kotlin.dsl.configure

class CircuitConventionPlugin: Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.google.devtools.ksp")
                apply("kotlin-parcelize")
            }

            extensions.configure<KspExtension> {
                arg("circuit.codegen.mode", "hilt")
            }

            bundle("circuit")
            ksp("circuit-codegen")
        }
    }
}