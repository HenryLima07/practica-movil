plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "com.example.sistema"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.sistema"
        minSdk = 24
        targetSdk = 35
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
}

val camerax_version = "1.3.3" // Revisa la última versión estable

dependencies {
    // Para solicitar permisos en tiempo de ejecución con Accompanist (útil para versiones anteriores a Android 13)
    // o si prefieres un API más declarativa.
    implementation("com.google.accompanist:accompanist-permissions:0.34.0") // Verifica la última versión

    // Para acceder a la ubicación (si tu ejercicio la usa)
    implementation("com.google.android.gms:play-services-location:21.2.0") // Verifica la última versión

    // Para la cámara (si tu ejercicio la usa) - Puedes usar la ActivityResultContracts API nativa
    // o librerías como CameraX que también manejan permisos.
    implementation("androidx.camera:camera-camera2:1.3.3") // Verifica la última versión
    implementation("androidx.camera:camera-lifecycle:1.3.3")
    implementation("androidx.camera:camera-view:1.3.3")

    // Para los contactos (si tu ejercicio los usa) - Se accede mediante ContentResolver, no requiere librería específica de Compose.
    implementation("androidx.camera:camera-core:${camerax_version}")
    implementation("androidx.camera:camera-camera2:${camerax_version}")
    implementation("androidx.camera:camera-lifecycle:${camerax_version}")
    implementation("androidx.camera:camera-view:${camerax_version}")
    implementation("androidx.camera:camera-extensions:${camerax_version}")


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
}