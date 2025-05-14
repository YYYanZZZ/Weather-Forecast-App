plugins {
    alias(libs.plugins.androidApplication)
}

android {
    namespace = "com.example.forecast"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.forecast"
        minSdk = 30
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        val localProperties = java.util.Properties()
        val localPropertiesFile = rootProject.file("local.properties")
        if (localPropertiesFile.exists()) {
            localProperties.load(java.io.FileInputStream(localPropertiesFile))
        }
        
        buildConfigField("String", "WEATHER_API_KEY", "\"${localProperties.getProperty("weather.api.key", "YOUR_WEATHER_API_KEY")}\"")
        manifestPlaceholders["AMAP_API_KEY"] = localProperties.getProperty("amap.api.key", "YOUR_AMAP_API_KEY")
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
}

dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    implementation(files("C:\\Users\\asus\\Desktop\\AMap_Location_V6.4.5_20240508.jar"))
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
    implementation("com.squareup.picasso:picasso:2.5.2")
    implementation("com.google.code.gson:gson:2.2.4")
    implementation("com.android.support:cardview-v7:27.1.0")
    implementation("androidx.viewpager2:viewpager2:1.0.0")
    implementation("org.xutils:xutils:3.7.6")
}