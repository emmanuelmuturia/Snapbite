plugins {
    alias(notation = libs.plugins.androidApplication)
    alias(notation = libs.plugins.kotlinAndroid)
    alias(notation = libs.plugins.jetBrainsCompose)
}

android {
    namespace = "snapbite.app.android"
    compileSdk = 34
    defaultConfig {
        applicationId = "snapbite.app.android"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.compose.compiler.get()
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
}

dependencies {
    implementation(dependencyNotation = projects.commons)
    implementation(dependencyNotation = libs.compose.ui)
    implementation(dependencyNotation = libs.compose.ui.tooling.preview)
    implementation(dependencyNotation = libs.compose.material3)
    implementation(dependencyNotation = libs.androidx.activity.compose)
    debugImplementation(dependencyNotation = libs.compose.ui.tooling)
}