plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.daggerHilt)
    id("com.google.devtools.ksp")
    id("kotlin-kapt")
}

android {
    namespace = "com.example.composetemplate"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.composetemplate"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
    composeOptions {
        //Make sure KSP, Kotlin Version and this compiler version ALL Match up!
        kotlinCompilerExtensionVersion = "1.5.10"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
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
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    //KSP
    implementation(libs.kspProcessingApi)
    implementation(libs.kspProcessing)

    //Retrofit
    implementation(libs.retrofit)
    implementation(libs.converterMoshi)

    //Moshi
    implementation(libs.moshi)
    implementation(libs.moshiKotlin)

    //Dagger - Hilt
    implementation(libs.hiltAndroid)
    ksp(libs.hiltCompiler)

    //Coroutines Lifecycle Scopes
    implementation(libs.coroLifecycle)

    //Compose Navigation
    implementation(libs.composeNavi)
    implementation(libs.composeNaviHilt)

    //Room
    implementation(libs.roomDBRuntime)
    implementation(libs.roomDB)
    ksp(libs.roomCompiler)
}


