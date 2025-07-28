package com.cyanlch.convention

import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalog
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.getByType

val Project.libs: VersionCatalog
    get() = extensions.getByType<VersionCatalogsExtension>().named("libs")

fun Project.impl(alias: String) {
    libs.findLibrary(alias).ifPresent {
        dependencies.add("implementation", it)
    }
}

fun Project.debugImpl(alias: String) {
    libs.findLibrary(alias).ifPresent {
        dependencies.add("debugImplementation", it)
    }
}

fun Project.platform(alias: String) {
    libs.findLibrary(alias).ifPresent {
        dependencies.add("implementation", dependencies.platform(it))
    }
}

fun Project.ksp(alias: String) {
    libs.findLibrary(alias).ifPresent {
        dependencies.add("ksp", it)
    }
}

fun Project.bundle(alias: String) {
    libs.findBundle(alias).ifPresent {
        dependencies.add("implementation", it)
    }
}
