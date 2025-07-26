plugins {
    `kotlin-dsl`
}
group = "com.multi.module.buildlogic"

dependencies {
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.kotlin.gradlePlugin)
    compileOnly(libs.compose.gradlePlugin)
    compileOnly(libs.ksp.gradlePlugin)
}

gradlePlugin {
    plugins {
        register("androidApplicationCompose") {
            id = "com.cyanlch.convention.application.compose"
            implementationClass = "AndroidApplicationComposeConventionPlugin"
        }
        register("androidLibraryCompose") {
            id = "com.cyanlch.convention.library.compose"
            implementationClass = "AndroidLibraryComposeConventionPlugin"
        }
        register("circuitConvention") {
            id = "com.cyanlch.convention.circuit"
            implementationClass = "CircuitConventionPlugin"
        }
        register("hiltConvention") {
            id = "com.cyanlch.convention.hilt"
            implementationClass = "HiltConventionPlugin"
        }
    }
}