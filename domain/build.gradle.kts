@file:Suppress("UnstableApiUsage")

plugins {
    id("com.android.library")
    id("kotlin-kapt")
    id("kotlinx-serialization")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.davinhdev.eurosport.domain"
    compileSdk = 33

    defaultConfig {
        minSdk = 23
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    libraryVariants.all {
        // KSP - To use generated sources
        addJavaSourceFoldersToModel(file("build/generated/ksp/$name/kotlin"))
    }
}

dependencies {
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.5.1")

    api("com.jakewharton.timber:timber:5.0.1")
    api("io.insert-koin:koin-android:3.4.2")
    api("io.insert-koin:koin-core:3.4.2")
    api("io.insert-koin:koin-annotations:1.2.2")

    ksp("io.insert-koin:koin-ksp-compiler:1.2.2")

    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.7.1")
    testImplementation("io.mockk:mockk:1.13.5")
    testImplementation("com.google.truth:truth:1.1.5")
}
