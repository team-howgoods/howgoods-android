package com.cyanlch.convention

import org.gradle.api.Project
import java.io.FileInputStream
import java.util.Properties

fun getLocalProperty(project: Project, key: String): String {
    val localProperties = Properties()
    val localPropertiesFile = project.rootProject.file("local.properties")
    if (localPropertiesFile.exists()) {
        localProperties.load(FileInputStream(localPropertiesFile))
    }
    return localProperties.getProperty(key, "")
}

/**
 * buildConfigField에 사용할 값을 가져옵니다.
 * 우선순위:
 * 1. CI/CD 환경 변수 (systemProp)
 * 2. local.properties (secret)
 * 3. gradle.properties (config)
 */
fun getBuildConfigValue(project: Project, key: String): String {
    // 1. 시스템 프로퍼티에서 읽기 (CI/CD에서 -Dkey=value 형태로 주입 가능)
    var value = System.getProperty(key)
    if (value != null) {
        return "\"$value\""
    }

    // 2. local.properties에서 읽기
    value = getLocalProperty(project, key)
    if (value.isNotEmpty()) {
        return "\"$value\""
    }

    // 3. gradle.properties에서 읽기
    value = project.providers.gradleProperty(key).orNull
    if (value != null) {
        return "\"$value\""
    }

    // 모두 없으면 빈 문자열 반환
    return "\"\""
}
