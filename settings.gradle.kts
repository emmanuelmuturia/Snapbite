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
include(":about:uilayer")
include(":about:datalayer")
include(":about:domainlayer")
include(":about:dependencyinjection")
include(":commons:uilayer")
include(":home:uilayer")
include(":navigation")
include(":home:dependencyinjection")
include(":search:uilayer")
include(":profile:uilayer")
include(":achievements:uilayer")
include(":settings:uilayer")
include(":faq:uilayer")
include(":notifications:uilayer")
include(":food:uilayer")
include(":food:datalayer")
include(":food:domainlayer")
include(":food:dependencyinjection")
include(":photography:uilayer")
include(":commons:datalayer")
include(":commons:dependencyinjection")
include(":commons:domainlayer")
include(":settings:datalayer")
include(":settings:domainlayer")
include(":settings:dependencyinjection")
include(":notifications:datalayer")
include(":notifications:domainlayer")
include(":notifications:dependencyinjection")
include(":faq:datalayer")
include(":faq:domainlayer")
include(":faq:dependencyinjection")
include(":home:datalayer")
include(":home:domainlayer")
include(":profile:datalayer")
include(":profile:dependencyinjection")
include(":profile:domainlayer")
