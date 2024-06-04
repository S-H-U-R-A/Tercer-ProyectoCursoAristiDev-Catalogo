plugins {
    this.id("com.android.application") // AGP ANDROID GRADLE PLUGIN
    this.id("org.jetbrains.kotlin.android")
}

kotlin{
    jvmToolchain(17)
}


//MÉTODO DE EXTENSIÓN AGREGADO A TRAVEZ DEL PLUGIN  id("com.android.application")
android {

    this.namespace = "com.example.jetpackcomposecatalogoelementosui"
    this.compileSdk = 34

    this.defaultConfig {
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

    this.compileOptions {
        isCoreLibraryDesugaringEnabled = true
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    this.kotlinOptions {
        jvmTarget = "17"
    }

    this.buildFeatures {
        compose = true
        buildConfig = true
    }

    this.composeOptions {
        kotlinCompilerExtensionVersion = "1.4.3"
    }

    this.packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }

    this.buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        getByName("debug"){
            isMinifyEnabled = false
        }
    }

    //FLAVORS
    this.flavorDimensions.add("version")
    /*this.flavorDimensions.add("AppInfo")*/

    this.productFlavors{
        this.create("free"){
            dimension = "version"
            applicationIdSuffix = ".free"
            versionNameSuffix = "-free"

            buildConfigField(type = "Boolean", name= "SHOW_POPUP", value= "true" ) //VARIABLES EN EL FLAVOR
        }
        this.create("paid"){
            dimension = "version"
            applicationIdSuffix = ".paid"
            versionNameSuffix = "-paid"

            buildConfigField(type = "Boolean", name= "SHOW_POPUP", value= "false" ) //VARIABLES EN EL FLAVOR
        }
/*        this.create("AppInfoFlavor"){
            dimension = "AppInfo"
            applicationIdSuffix = ".appInfo"
        }*/
    }

   //Esto se hace cuando además de configurar variables, necesitamos diferentes recursos o código fuente por cada Flavor
    sourceSets {
        getByName("free"){
            this.java {
                srcDirs("src/free/java")
            }
            this.res {
                srcDirs("src/free/res")
            }
            this.assets {
                this.srcDirs("src/free/assets")
            }
        }
        getByName("paid"){
            this.java {
                srcDirs("src/paid/java")
            }
            this.res {
                srcDirs("src/paid/res")
            }
        }
    }
}

dependencies {

    implementation("androidx.compose.animation:animation-graphics-android:1.6.7")
    //COMPATIBILIDAD CON ALGUNAS API DE JAVA 8
    coreLibraryDesugaring("com.android.tools:desugar_jdk_libs:2.0.4")

    //KOTLIN
    implementation("androidx.core:core-ktx:1.13.1")

    //LIFECYCLE
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.8.0")

    //ACTIVIDAD
    implementation("androidx.activity:activity-compose:1.9.0")

    //COMPOSE
    implementation(platform("androidx.compose:compose-bom:2024.05.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    //MATERIAL 3 COMPOSE
    implementation("androidx.compose.material3:material3")
    implementation ("androidx.compose.material:material-icons-extended")

    //WINDOWS SIZE CLASSES
    implementation("androidx.compose.material3:material3-window-size-class:1.2.1")

    //MATERIAL 2 COMPOSE
    //implementation ("androidx.compose.material:material:1.3.1")

    //LOTTIE
    implementation ("com.airbnb.android:lottie:6.4.0")

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
    androidTestImplementation(platform("androidx.compose:compose-bom:2024.05.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")

    //BUILD TYPE DEBUG
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")

    //FLAVOR FREE
    "freeImplementation"("androidx.compose.ui:ui-tooling")
}
