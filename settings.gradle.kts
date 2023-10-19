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
include(":commons:domainlayer")
include(":commons:uilayer")
include(":welcome:uilayer")
include(":navigation:uilayer")
