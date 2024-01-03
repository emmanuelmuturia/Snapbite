// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(notation = libs.plugins.com.android.application) apply false
    alias(notation = libs.plugins.org.jetbrains.kotlin.android) apply false
    alias(notation = libs.plugins.com.google.dagger.hilt.android.plugin) apply false
    alias(notation = libs.plugins.com.google.devtools.ksp) apply false
    alias(notation = libs.plugins.com.android.library) apply false
    alias(notation = libs.plugins.com.google.gms.google.services) apply false
    alias(notation = libs.plugins.com.google.android.libraries.mapsplatform.secrets.gradle.plugin) apply false
}