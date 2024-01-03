plugins {
    alias(libs.plugins.com.android.library)
    alias(libs.plugins.org.jetbrains.kotlin.android)
    alias(libs.plugins.com.google.devtools.ksp)
    alias(libs.plugins.com.google.dagger.hilt.android.plugin)
}

android {
    namespace = "emmanuelmuturia.navigation"
    compileSdk = 34

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
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
    }
    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.kotlinCompilerVersion.get()
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    // Home Module (UI Layer)...
    implementation(project(":home:uilayer"))

    // Search Module...
    implementation(project(":search:uilayer"))

    // Notifications Module...
    implementation(project(":notifications:uilayer"))

    // Settings Module...
    implementation(project(":settings:uilayer"))

    // Profile Module...
    implementation(project(":profile:uilayer"))

    // Food Module...
    implementation(project(":food:uilayer"))

    // Photography Module...
    implementation(project(":photography:uilayer"))

    // FAQ Module...
    implementation(project(":faq:uilayer"))

    // About Module...
    implementation(project(":about:uilayer"))

    // Commons Module...
    implementation(project(":commons:datalayer"))

    // Navigation...
    implementation(libs.androidx.navigation.compose)

    // Dagger-Hilt...
    implementation(dependencyNotation = libs.hilt.android)
    "ksp"(dependencyNotation = libs.hilt.android.compiler)
    implementation(dependencyNotation = libs.androidx.hilt.navigation.compose)

    // Lifecycle Runtime (Compose)...
    implementation(dependencyNotation = libs.lifecycle.runtime.compose)

    // Firebase...
    implementation(dependencyNotation = platform(libs.firebase.bom))
    implementation(dependencyNotation = libs.firebase.cloud.firestore)
    implementation(dependencyNotation = libs.firebase.authentication)
    implementation(dependencyNotation = libs.gms.play.services)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")
}