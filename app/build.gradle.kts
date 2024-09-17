plugins {
    this.alias(notation = libs.plugins.android.application) //AGP ANDROID GRADLE PLUGIN
    this.alias(notation = libs.plugins.kotlin.android)
    alias(libs.plugins.compose.compiler)
}

kotlin{
    jvmToolchain(17)
}

//MÉTODO DE EXTENSIÓN AGREGADO A TRAVEZ DEL PLUGIN  id("com.android.application")
android {

    this.namespace = "com.example.jetpackcomposecatalogoelementosui"
    this.compileSdk = 35

    this.defaultConfig {
        applicationId = "com.example.jetpackcomposecatalogoelementosui"
        minSdk = 24
        targetSdk = 35
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
    //COMPATIBILIDAD CON ALGUNAS API DE JAVA 8
    coreLibraryDesugaring(dependencyNotation = libs.desugar.jdk.libs)
    //KOTLIN
    implementation(dependencyNotation =  libs.androidx.core.ktx)
    //LIFECYCLE
    implementation(dependencyNotation = libs.androidx.lifecycle.runtime.ktx)
    //ACTIVIDAD
    implementation(libs.androidx.activity.compose)
    //ANIMATION COMPOSE
    implementation(libs.androidx.animation.graphics)
    //COMPOSE
    implementation(dependencyNotation = platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    //MATERIAL3 COMPOSE
    implementation(libs.androidx.material3)
    implementation (libs.androidx.material.icon.extended)
    //WINDOWS SIZE CLASSES
    implementation(libs.androidx.material3.window.size)
    //CONSTRAINT LAYOUT
    implementation(dependencyNotation = libs.androidx.constraintlayout.compose)
    //NAVIGATION COMPOSE
    implementation(dependencyNotation= libs.androidx.navigation.compose)
    //LOTTIE
    implementation (dependencyNotation = libs.lottie)

    //TEST UNIT
    testImplementation(dependencyNotation = libs.junit)
    //TEST INSTRUMENTATION
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    //COMPOSE TEST UI
    androidTestImplementation(libs.androidx.ui.test.junit4)

    //BUILD TYPE DEBUG
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    //FLAVOR FREE
    "freeImplementation"(dependencyNotation =  libs.androidx.ui.tooling)
}
