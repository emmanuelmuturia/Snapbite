plugins {
    alias(notation = libs.plugins.com.android.library)
    alias(notation = libs.plugins.org.jetbrains.kotlin.android)
    alias(notation = libs.plugins.com.google.devtools.ksp)
    alias(notation = libs.plugins.com.google.dagger.hilt.android.plugin)
}

android {
    namespace = "emmanuelmuturia.about.uilayer"
    compileSdk = 34

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "emmanuelmuturia.about.TestRunner"
        consumerProguardFiles(proguardFiles = arrayOf("consumer-rules.pro"))
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                files = arrayOf(
                    getDefaultProguardFile(name = "proguard-android-optimize.txt"),
                    "proguard-rules.pro"
                )
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
        buildConfig = true
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

    libraryVariants.all {
        addJavaSourceFoldersToModel(
            File(buildDir, "generated/ksp/$name/kotlin")
        )
    }

}

dependencies {

    // Module(s)...
    val moduleList = listOf(
        "about:datalayer",
        "about:domainlayer",
        "about:dependencyinjection",
        "commons:uilayer",
        "commons:domainlayer"
    )

    moduleList.forEach { module ->
        implementation(project(path = ":$module"))
    }

    // Dagger-Hilt...
    implementation(dependencyNotation = libs.hilt.android)
    "ksp"(dependencyNotation = libs.hilt.android.compiler)
    implementation(dependencyNotation = libs.androidx.hilt.navigation.compose)

    // Timber...
    implementation(dependencyNotation = libs.timber)

    // Android...
    implementation(dependencyNotation = libs.lifecycle.runtime.compose)
    implementation(dependencyNotation = libs.androidx.core.ktx)
    implementation(dependencyNotation = libs.androidx.lifecycle.runtime.ktx)
    implementation(dependencyNotation = libs.androidx.activity.compose)
    implementation(dependencyNotation = platform(libs.androidx.compose.bom))
    implementation(dependencyNotation = libs.compose.ui)
    implementation(dependencyNotation = libs.compose.ui.graphics)
    implementation(dependencyNotation = libs.compose.ui.tooling.preview)
    implementation(dependencyNotation = libs.material3)

    // Testing...
    testImplementation(dependencyNotation = libs.robolectric)
    testImplementation(dependencyNotation = libs.mockK)
    testImplementation(dependencyNotation = libs.junit)
    androidTestImplementation(dependencyNotation = libs.androidx.junit)
    androidTestImplementation(dependencyNotation = libs.androidx.espresso.core)
    androidTestImplementation(dependencyNotation = platform(libs.androidx.compose.bom))
    androidTestImplementation(dependencyNotation = libs.compose.ui.test.junit4)
    debugImplementation(dependencyNotation = libs.compose.ui.tooling)
    debugImplementation(dependencyNotation = libs.compose.ui.test.manifest)
}