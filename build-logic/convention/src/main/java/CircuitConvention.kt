import org.gradle.api.Plugin
import org.gradle.api.Project
import com.cyanlch.convention.impl
import com.cyanlch.convention.ksp

class CircuitConvention: Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.google.devtools.ksp")
            }
            impl("circuit")
            ksp("circuit-codegen")
        }
    }
}