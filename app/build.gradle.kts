plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.ksp)
    alias(libs.plugins.daggerHiltAndroid)
//    id("com.google.gms.google-services")
}

android {
    namespace = "com.service.servicejunction"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.service.servicejunction"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
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
    implementation(libs.play.services.location)
//    implementation(libs.androidx.room.common)
//    implementation(libs.androidx.room.ktx)
    implementation(libs.androidx.espresso.core)
    implementation(libs.androidx.appcompat.resources)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)


    implementation("androidx.navigation:navigation-compose:2.8.5")
    implementation("androidx.compose.material3:material3:1.3.1")
    implementation ("androidx.compose.material:material:1.7.6")
    implementation ("io.coil-kt:coil-compose:2.7.0")


    // Jetpack Compose UI
    implementation ("androidx.compose.ui:ui:1.7.6")
    implementation ("androidx.compose.material:material:1.7.6")
    implementation ("androidx.compose.ui:ui-tooling-preview:1.7.6")

    // Compose Runtime for State management
    implementation( "androidx.compose.runtime:runtime:1.7.6")

    // LiveData integration with Compose (optional, for apps using LiveData)
    implementation ("androidx.compose.runtime:runtime-livedata:1.7.6")

    // Compose Activity for Jetpack Compose support in activities
    implementation ("androidx.activity:activity-compose:1.9.3")

    // Optional - Compose UI Testing
    androidTestImplementation ("androidx.compose.ui:ui-test-junit4:1.7.6")
    debugImplementation( "androidx.compose.ui:ui-tooling:1.7.6")

    implementation(libs.androidx.core.splashscreen)

    implementation ("androidx.compose.foundation:foundation:1.7.6")



    implementation ("com.google.accompanist:accompanist-pager:0.30.0")
    implementation ("com.google.accompanist:accompanist-pager-indicators:0.31.2-alpha")
    implementation ("androidx.compose.material3:material3:1.3.1")

    //intro screen

    implementation ("androidx.compose.material3:material3:1.3.1")
    implementation ("com.google.accompanist:accompanist-pager:0.30.0")
    implementation ("com.google.accompanist:accompanist-pager-indicators:0.30.0")

    implementation("androidx.compose.foundation:foundation:1.7.6")

    implementation("com.google.maps.android:maps-compose:4.0.0")
    implementation("com.google.android.gms:play-services-maps:18.1.0")

    //Dagger-Hilt
    implementation(libs.hilt.android)
    ksp(libs.hilt.android.compiler)
    implementation(libs.androidx.hilt.navigation.compose)
    ksp(libs.androidx.hilt.compiler)

    //Room
    implementation(libs.androidx.room.runtime)
    ksp(libs.androidx.room.compiler)
    // Kotlin Extension and Coroutines support for Room
    implementation(libs.androidx.room.ktx)

//    //For Camera
//    val cameraxVersion = "1.4.1"
//    implementation("androidx.camera:camera-core:$cameraxVersion")
//    implementation("androidx.camera:camera-camera2:$cameraxVersion")
//    implementation("androidx.camera:camera-lifecycle:$cameraxVersion")
//    implementation("androidx.camera:camera-video:$cameraxVersion")
//
//    implementation("androidx.camera:camera-view:$cameraxVersion")
//    implementation("androidx.camera:camera-extensions:$cameraxVersion")


}
java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(17)
    }
}
