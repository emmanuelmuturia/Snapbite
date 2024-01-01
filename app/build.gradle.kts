plugins {
    alias(notation = libs.plugins.com.android.application)
    alias(notation = libs.plugins.org.jetbrains.kotlin.android)
    alias(notation = libs.plugins.com.google.devtools.ksp)
    alias(notation = libs.plugins.com.google.dagger.hilt.android.plugin)
    alias(notation = libs.plugins.com.google.gms.google.services)
    alias(notation = libs.plugins.com.google.firebase.crashlytics)
    alias(notation = libs.plugins.com.google.firebase.performance)
    alias(notation = libs.plugins.com.guardsquare.appsweep)
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

    // Module(s)...
    val moduleList = listOf(
        "navigation",
        "commons:uilayer",
        "notifications:datalayer",
        "notifications:domainlayer",
        "notifications:dependencyinjection"
    )

    moduleList.forEach { module ->
        implementation(project(path = ":$module"))
    }

    // Firebase...
    implementation(dependencyNotation = platform(libs.firebase.bom))
    implementation(dependencyNotation = libs.firebase.analytics)
    implementation(dependencyNotation = libs.firebase.cloud.messaging)
    implementation(dependencyNotation = libs.firebase.performance)

    // Splash Screen API...
    implementation(dependencyNotation = libs.androidx.core.splashscreen)

    // Dagger-Hilt...
    implementation(dependencyNotation = libs.hilt.android)
    "ksp"(dependencyNotation = libs.hilt.android.compiler)
    implementation(dependencyNotation = libs.androidx.hilt.navigation.compose)

    // Navigation...
    implementation(dependencyNotation = libs.androidx.navigation.compose)

    // App Compat...
    implementation(dependencyNotation = libs.appcompat)

    // Timber...
    implementation(dependencyNotation = libs.timber)

    // Android...
    implementation(dependencyNotation = libs.androidx.core.ktx)
    implementation(dependencyNotation = libs.androidx.lifecycle.runtime.ktx)
    implementation(dependencyNotation = libs.androidx.activity.compose)
    implementation(dependencyNotation = platform(libs.androidx.compose.bom))
    implementation(dependencyNotation = libs.compose.ui)
    implementation(dependencyNotation = libs.compose.ui.graphics)
    implementation(dependencyNotation = libs.compose.ui.tooling.preview)
    implementation(dependencyNotation = libs.material3)

    // Testing...
    testImplementation(dependencyNotation = libs.junit)
    androidTestImplementation(dependencyNotation = libs.androidx.junit)
    androidTestImplementation(dependencyNotation = libs.androidx.espresso.core)
    androidTestImplementation(dependencyNotation = platform(libs.androidx.compose.bom))
    androidTestImplementation(dependencyNotation = libs.compose.ui.test.junit4)
    debugImplementation(dependencyNotation = libs.compose.ui.tooling)
    debugImplementation(dependencyNotation = libs.compose.ui.test.manifest)
    debugImplementation(dependencyNotation = libs.leakCanary)

}