import com.google.protobuf.gradle.id

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.ksp)
    id("com.google.protobuf") version "0.9.4"
    id("kotlin-parcelize")
}

android {
    namespace = "com.example.profile"
    compileSdk = 36

    defaultConfig {
        minSdk = 24
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
}

protobuf {
    protoc {
        artifact = "com.google.protobuf:protoc:3.24.1"
    }
    generateProtoTasks {
        all().forEach { task ->
            task.builtins {
                id("java") {
                    option("lite")
                }
                id("kotlin") {
                    option("lite")
                }
            }
        }
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)

    //compose
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)
    implementation(libs.material)

    //navigation
    implementation(libs.androidx.navigation3.ui)

    //image loading
    implementation(libs.glide)
    implementation(libs.glide.compose)

    //DI
    implementation(libs.bundles.koin)

    //db
    implementation(libs.bundles.koin)
    implementation(libs.androidx.room.runtime)
    ksp(libs.androidx.room.compiler)
    annotationProcessor(libs.androidx.room.compiler)
    implementation(libs.room.ktx)
    implementation(libs.androidx.datastore.preferences)

    //proto
    implementation(libs.modo.compose)
    implementation(libs.protobuf.javalite)
    implementation(libs.protobuf.kotlin.lite)

    //modules
    implementation(project(":uikit"))
    implementation(project(":core"))
}