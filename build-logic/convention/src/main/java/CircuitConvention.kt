import org.gradle.api.Plugin
import org.gradle.api.Project
import com.cyanlch.convention.impl

class CircuitConvention: Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            impl("circuit")
        }
    }
}