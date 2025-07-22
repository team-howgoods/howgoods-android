plugins {
    `kotlin-dsl`
}
group = "com.multi.module.buildlogic"

dependencies {
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.kotlin.gradlePlugin)
}

gradlePlugin {
    plugins {
        register("androidApplicationCompose") {
            id = "com.cyanlch.convention.application.compose"
            implementationClass = "AndroidApplicationComposeConventionPlugin"
        }
    }
}