buildscript {
    dependencies {
        classpath("com.squareup.sqldelight:gradle-plugin:1.5.5")
    }
}

plugins {
    //trick: for the same plugin versions in all sub-modules
    alias(notation = libs.plugins.androidApplication).apply(apply = false)
    alias(notation = libs.plugins.androidLibrary).apply(apply = false)
    alias(notation = libs.plugins.kotlinAndroid).apply(apply = false)
    alias(notation = libs.plugins.kotlinMultiplatform).apply(apply = false)
    alias(notation = libs.plugins.jetBrainsCompose).apply(apply = false)
}
