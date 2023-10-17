pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "Snapbite"
include(":app")
include(":home")
include(":search")
include(":profile")
include(":achievements")
include(":settings")
include(":faq")
include(":termination")
include(":commons")
include(":navigation")
include(":notifications")
include(":update")
include(":food")
include(":camera")
