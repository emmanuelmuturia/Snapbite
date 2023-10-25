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
include(":commons:uilayer")
include(":welcome:uilayer")
include(":home:uilayer")
include(":navigation")
include(":home:dependencyinjection")
include(":search:uilayer")
include(":profile:uilayer")
include(":achievements:uilayer")
include(":settings:uilayer")
include(":faq:uilayer")
include(":termination:uilayer")
include(":notifications:uilayer")
include(":update:uilayer")
include(":food:uilayer")
include(":food:datalayer")
include(":food:domainlayer")
include(":food:dependencyinjection")
