plugins {
    alias(notation = libs.plugins.com.android.application)
    kotlin(module = "android")
    alias(notation = libs.plugins.compose.multiplatform)
    alias(notation = libs.plugins.com.google.devtools.ksp)
    alias(notation = libs.plugins.com.google.gms.google.services)
    alias(notation = libs.plugins.com.google.firebase.crashlytics)
    alias(notation = libs.plugins.com.google.firebase.performance)
    alias(notation = libs.plugins.com.guardsquare.appsweep)
    alias(notation = libs.plugins.plugin.serialization)
    alias(notation = libs.plugins.com.google.android.libraries.mapsplatform.secrets.gradle.plugin)
}

android {
    namespace = "snapbite.app"
    compileSdk = 34

    defaultConfig {
        applicationId = "snapbite.app"
        minSdk = 24
        targetSdk = 34
        versionCode = 2
        versionName = "1.0.1"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(files = arrayOf(
                getDefaultProguardFile(name = "proguard-android-optimize.txt"),
                "proguard-rules.pro"
            ))
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.kotlinCompilerVersion.get()
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }

    applicationVariants.all {
        addJavaSourceFoldersToModel(
            File(buildDir, "generated/ksp/$name/kotlin")
        )
    }

}

dependencies {

    // Commons Module...
    implementation(dependencyNotation = project(path = ":commons"))

    // Android...
    implementation(dependencyNotation = libs.androidx.activity.compose)

    // Compose Multiplatform...
    implementation(dependencyNotation = compose.material3)

    // Firebase...
    implementation(dependencyNotation = platform(libs.firebase.bom))
    implementation(dependencyNotation = libs.firebase.cloud.firestore)
    implementation(dependencyNotation = libs.firebase.authentication)
    implementation(dependencyNotation = libs.firebase.analytics)
    implementation(dependencyNotation = libs.firebase.cloud.messaging)
    implementation(dependencyNotation = libs.firebase.performance)
    implementation(dependencyNotation = libs.gms.play.services)

    // Splash Screen API...
    implementation(dependencyNotation = libs.androidx.core.splashscreen)

    // Voyager...
    implementation(dependencyNotation = libs.voyager.navigator)

    // Koin...
    implementation(dependencyNotation = libs.koin.android)
    implementation(dependencyNotation = libs.koin.compose)
    implementation(dependencyNotation = libs.koin.multiplatform)

    // Glide...
    implementation(dependencyNotation = libs.com.github.bumptech.glide.compose)

    // Timber...
    implementation(dependencyNotation = libs.timber)

    // Room...
    implementation(libs.room.runtime)
    implementation(libs.room.ktx)
    "ksp"(libs.room.compiler)

    // Kotlin Serialisation...
    implementation(dependencyNotation = libs.kotlin.serialization)

    // Emoji Picker...
    implementation(dependencyNotation = libs.emoji.picker)

    // CameraX...
    implementation(libs.androidx.camera.core)
    implementation(libs.androidx.camera.camera2)
    implementation(libs.androidx.camera.lifecycle)
    implementation(libs.androidx.camera.view)
    implementation(libs.androidx.camera.extensions)

    // Swipe To Refresh (Accompanist)...
    implementation(dependencyNotation = libs.accompanist.swiperefresh)

    // LeakCanary...
    debugImplementation(dependencyNotation = libs.leakCanary)

}