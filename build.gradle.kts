// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    dependencies {
        classpath("com.android.tools.build:gradle:4.2.2")
        classpath("com.google.gms:google-services:4.3.14")

        val hilt = "2.39.1"
        classpath("com.google.dagger:hilt-android-gradle-plugin:$hilt")

        val safeArgs = "2.5.3"
        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:$safeArgs")
    }
}

plugins {
    id("com.android.application") version "7.3.0" apply false
    id("org.jetbrains.kotlin.android") version "1.7.10" apply false
    id("androidx.navigation.safeargs.kotlin") version "2.5.3" apply false
}

val clean by tasks.creating(Delete::class) {
    delete = setOf(rootProject.buildDir)
}