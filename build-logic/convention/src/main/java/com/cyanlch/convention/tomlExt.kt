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