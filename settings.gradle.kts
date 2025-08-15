pluginManagement {
    includeBuild("build-logic")

    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven {
            url = java.net.URI("https://devrepo.kakao.com/nexus/content/groups/public/")
            content {
                includeGroup("com.kakao.sdk")
            }
        }
    }
}

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
rootProject.name = "HowGoods"
include(":app")
include(":core:domain")
include(":feature:login")
include(":core:ui")
include(":core:designsystem")
include(":core:navigation")
include(":core:data")
include(":core:model")
include(":core:network")
include(":core:datastore")
include(":feature:shell:main")
