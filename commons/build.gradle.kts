plugins {
    kotlin(module = "multiplatform")
    alias(notation = libs.plugins.com.android.library)
    alias(notation = libs.plugins.compose.multiplatform)
}

kotlin {

    // Targets...
    androidTarget()
    jvm(name = "desktop")

    // Platform-specific Dependencies...
    sourceSets {

        val commonMain by getting {
            dependencies {

                // Compose...
                implementation(dependencyNotation = compose.material3)

                // Voyager...
                implementation(dependencyNotation = libs.voyager.navigator)
                implementation(dependencyNotation = libs.voyager.koin)

                // Koin...
                api(dependencyNotation = libs.koin.multiplatform)

            }
        }

    }

}

android {

    namespace = "snapbite.commons"
    compileSdk = 34

    defaultConfig {
        minSdk = 24
    }

}