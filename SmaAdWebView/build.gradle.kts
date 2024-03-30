plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("maven-publish")
}

android {
//    namespace = "java.tech.gmo"
    namespace = "tech.gmo.tmpflutter_webview_android"
    compileSdk = 34

    defaultConfig {
//        applicationId = "tech.gmo.tmpflutter_webview_android"
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
    
}

afterEvaluate{
    publishing{
        publications {
            create("release", MavenPublication::class.java) {
                groupId = "com.github.ktoshiiio"
                artifactId = "jitpack"
                version = "0.1.33"
                artifact("$buildDir/outputs/aar/SmaAdWebView-release.aar")
            }
        }
    }
    // タスク間の依存関係を明示
    tasks.named("publishReleasePublicationToMavenLocal").configure {
        dependsOn("bundleReleaseAar")
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.webkit:webkit:1.8.0")
    implementation("androidx.browser:browser:1.6.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}