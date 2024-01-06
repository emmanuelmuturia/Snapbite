plugins {
    alias(notation = libs.plugins.kotlinMultiplatform)
    alias(notation = libs.plugins.androidLibrary)
    alias(notation = libs.plugins.jetBrainsCompose)
    alias(notation = libs.plugins.sql.delight)
}

kotlin {
    androidTarget {
        compilations.all {
            kotlinOptions {
                jvmTarget = "17"
            }
        }
    }

    targets.withType(org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget::class.java).all {
        binaries.withType(org.jetbrains.kotlin.gradle.plugin.mpp.Framework::class.java).all {
            export(dependency = libs.moko.mvvm.core)
        }
    }
    
    /*listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "commons"
            isStatic = true
        }
    }*/

    sourceSets {
        commonMain.dependencies {
            implementation(dependencyNotation = compose.runtime)
            implementation(dependencyNotation = compose.foundation)
            implementation(dependencyNotation = compose.material3)
            implementation(dependencyNotation = compose.materialIconsExtended)
            @OptIn(org.jetbrains.compose.ExperimentalComposeLibrary::class)
            implementation(dependencyNotation = compose.components.resources)
            implementation(dependencyNotation = libs.compose.ui)
            implementation(dependencyNotation = libs.sql.delight.runtime)
            implementation(dependencyNotation = libs.sql.delight.coroutines.extensions)
            implementation(dependencyNotation = libs.kotlinx.date.time)
        }
        commonTest.dependencies {
            implementation(dependencyNotation = libs.kotlin.test)
        }
        androidMain.dependencies {
            implementation(dependencyNotation = libs.sql.delight.android.driver)
            implementation(dependencyNotation = libs.app.compat)
            implementation(dependencyNotation = libs.androidx.activity.compose)
        }
        /*iosMain.dependencies {
            implementation(dependencyNotation = libs.sql.delight.native.driver)
        }*/
    }
}

android {
    namespace = "snapbite.app"
    compileSdk = 34
    defaultConfig {
        minSdk = 24
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}

sqldelight {
    databases {
        create(name = "FoodDatabase") {
            packageName.set("snapbite.app.database")
        }
    }
}

dependencies {
    implementation(dependencyNotation = libs.androidx.core)
    commonMainApi(dependencyNotation = libs.moko.mvvm.core)
    commonMainApi(dependencyNotation = libs.moko.mvvm.compose)
    commonMainApi(dependencyNotation = libs.moko.mvvm.flow)
    commonMainApi(dependencyNotation = libs.moko.mvvm.flow.compose)
}