import java.io.FileInputStream
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Properties

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
//    alias(libs.plugins.google.daggerhilt.android)
//    alias(libs.plugins.google.ksp)
    kotlin("plugin.serialization") version "2.0.10"
    id("com.google.gms.google-services")
    id("com.google.firebase.crashlytics")
    id("kotlin-parcelize")
    id("com.google.devtools.ksp")
    id("com.google.dagger.hilt.android")

}
val keystoreProperties = Properties().apply {
    load(FileInputStream(rootProject.file("keystore.properties")))
}
val localProperties = Properties().apply {
    load(FileInputStream(rootProject.file("local.properties")))
}
android {
    namespace = "com.xgame.base"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.xgame.base"
        minSdk = 24
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"
        multiDexEnabled = true
//        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
//
//        val buildDate = SimpleDateFormat("ddMMyy_HHmm").format(Date())
//        setProperty("archivesBaseName", "Base_Android-Xgame-HCM${versionName}_${buildDate}")
    }
    android.applicationVariants.configureEach {
        outputs.all {
            val buildDate = SimpleDateFormat("ddMMyy_HHmm").format(Date())
            val versionName = this@configureEach.versionName
            val newName = "Base_Android-Xgame-HCM${versionName}_${buildDate}.apk"
            (this as com.android.build.gradle.internal.api.ApkVariantOutputImpl).outputFileName = newName
        }
    }

    signingConfigs {
        create("release") {
            keyAlias = keystoreProperties["keyAlias"] as String
            keyPassword = keystoreProperties["keyPassword"] as String
            storeFile = file(keystoreProperties["storeFile"] as String)
            storePassword = keystoreProperties["storePassword"] as String
        }
    }
    buildTypes {
        debug {
        }
        release {
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        buildConfig = true
        compose = true
        viewBinding = true
    }

    flavorDimensions += "default"
    productFlavors {
        create("dev") {
            manifestPlaceholders["appName"] = "@string/app_name_Dev"
            applicationId = "com.xgame.base.dev"
            buildConfigField("String", "API_DOMAIN", "\"https://api.pexels.com\"")
//            buildConfigField ("String", "API_KEY", "\"${localProperties.getProperty("API_KEY")}\"")
        }
        create("product") {
            manifestPlaceholders["appName"] = "@string/app_name"
            applicationId = "com.xgame.base"
            buildConfigField("String", "API_DOMAIN", "\"https://api.pexels.com\"")
//            buildConfigField ("String", "API_KEY", "\"${localProperties.getProperty("API_KEY")}\"")
        }
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.material)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)
    implementation(libs.androidx.tools.core)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
    // Dagger - Hilt
//    implementation("com.google.dagger:hilt-android:2.56.2")
//    ksp("com.google.dagger:hilt-android-compiler:2.56.2")

    implementation(libs.hilt.android)
    ksp(libs.hilt.android.compiler)
//    implementation("com.squareup:javapoet:1.13.0")

//    Compose
    val composeBom = platform("androidx.compose:compose-bom:2024.12.01")
    implementation(composeBom)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.ui)
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.lifecycle.viewmodel.compose)
    implementation(libs.androidx.lifecycle.runtime.compose)
    implementation(libs.androidx.hilt.navigation.compose)
    implementation(libs.material3)



    //Image - Video
    implementation(libs.coil.compose)
    implementation(libs.coil.network.okhttp)

    // Retrofit // Chú ý chỗ mấy thằng này rất hay xung đột version khi nâng ver mới, =>> nên dùng ver cũ
    implementation(libs.retrofit)
    implementation(libs.converter.scalars)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.timber)
    implementation(libs.logging.interceptor)
    implementation(libs.converter.gson)
//    chuckerteam

    debugImplementation (libs.library.v352)
    releaseImplementation (libs.library.no.op.v352)

    // Room
    implementation(libs.androidx.room.runtime)
    implementation(libs.androidx.room.ktx)
    ksp(libs.androidx.room.compiler)
    //Gson
    implementation(libs.gson)

    // media3 exoplayer
    implementation(libs.androidx.media3.exoplayer)
    implementation(libs.media3.exoplayer.dash)
    implementation(libs.androidx.media3.ui)
    implementation(libs.androidx.media3.exoplayer.hls)
    // Lottie
    implementation(libs.lottie)



    //Firebase, google
    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.analytics)
    implementation(libs.firebase.crashlytics)
    // Firebase Cloud Messaging
    implementation(libs.firebase.messaging.ktx)
    implementation(libs.androidx.multidex)
    // Appsflyer
//    implementation(libs.adrevenue)
//    implementation(libs.af.android.sdk)
//    implementation(libs.installreferrer)
    // Admob
//    implementation(libs.play.services.ads)
//    implementation(libs.user.messaging.platform)
    // AdmobAdapter
//    implementation(libs.applovin)
////    implementation(libs.chartboost)
//    implementation("com.chartboost:chartboost-sdk:9.6.0")
//    implementation(libs.fyber)
//    implementation(libs.inmobi)
//    implementation(libs.ironsource)
//    implementation(libs.vungle)
//    implementation(libs.facebook)
//    implementation(libs.mintegral)
//    implementation(libs.mytarget)
//    implementation(libs.pangle)
//    implementation(libs.unity.ads)
//    implementation(libs.unity)
//    //Billing
//    implementation(libs.billing)
//    implementation(libs.guava)

}