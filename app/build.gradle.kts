plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

//MÉTODO DE EXTENSIÓN AGREGADO A TRAVEZ DEL PLUGIN  id("com.android.application")
android {

    namespace = "com.example.jetpackcomposecatalogoelementosui"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.jetpackcomposecatalogoelementosui"
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
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        getByName("debug"){
            isMinifyEnabled = true
        }
    }

    compileOptions {
        isCoreLibraryDesugaringEnabled = true
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = "17"
    }

    buildFeatures {
        compose = true
        buildConfig = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.3"
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }

    //FLAVORS

    flavorDimensions.add("version")

    productFlavors{
        create("free"){
            dimension = "version"
            applicationIdSuffix = ".free"
            buildConfigField(type = "Boolean", name= "SHOW_POPUP", value= "true" )
        }
    }

}

dependencies {

    //COMPATIBILIDAD CON ALGUNAS API DE JAVA 8
    coreLibraryDesugaring("com.android.tools:desugar_jdk_libs:2.0.4")

    //KOTLIN
    implementation("androidx.core:core-ktx:1.12.0")

    //LIFECYCLE
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.7.0")

    //ACTIVIDAD
    implementation("androidx.activity:activity-compose:1.8.2")

    //COMPOSE
    implementation(platform("androidx.compose:compose-bom:2024.02.01"))

    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    //MATERIAL 3 COMPOSE
    implementation("androidx.compose.material3:material3-android:1.2.0")
    //implementation("androidx.compose.material3:material3")
    implementation ("androidx.compose.material:material-icons-extended")

    //WINDOWS SIZE CLASSES
    implementation("androidx.compose.material3:material3-window-size-class:1.2.0")

    //MATERIAL 2 COMPOSE
    //implementation ("androidx.compose.material:material:1.3.1")

    //CONSTRAINT LAYOUT
    implementation("androidx.constraintlayout:constraintlayout-compose:1.0.1")

    //NAVIGATION COMPOSE
    implementation(dependencyNotation= "androidx.navigation:navigation-compose:2.7.7")

    //TEST UNIT
    testImplementation("junit:junit:4.13.2")

    //TEST INSTRUMENTATION
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    //COMPOSE TEST UI
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.03.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")

    //BUILD TYPE DEBUG
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")

    //FLAVOR FREE
    "freeImplementation"("androidx.compose.ui:ui-tooling")

}