plugins {
    `kotlin-dsl`
}
group = "com.multi.module.buildlogic"

dependencies {
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.kotlin.gradlePlugin)
    compileOnly(libs.compose.gradlePlugin)
    compileOnly(libs.ksp.gradlePlugin)
    compileOnly(libs.firebase.crashlytics.gradlePlugin)
    compileOnly(libs.firebase.performance.gradlePlugin)
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
        register("androidLibrary") {
            id = "com.cyanlch.convention.library"
            implementationClass = "AndroidLibraryConventionPlugin"
        }
        register("circuitConvention") {
            id = "com.cyanlch.convention.circuit"
            implementationClass = "CircuitConventionPlugin"
        }
        register("hiltConvention") {
            id = "com.cyanlch.convention.hilt"
            implementationClass = "HiltConventionPlugin"
        }
        register("featureConvention") {
            id = "com.cyanlch.convention.feature"
            implementationClass = "AndroidFeatureConventionPlugin"
        }
        register("androidApplicationFirebase") {
            id = "com.cyanlch.convention.application.firebase"
            implementationClass = "AndroidApplicationFirebaseConventionPlugin"
        }
    }
}