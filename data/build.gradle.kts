import java.util.Properties

android.buildFeatures.buildConfig = true

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.ksp)
}


android {
    namespace = "redon.tradox.data"
    compileSdk = 36

    defaultConfig {
        minSdk = 24


        val localProperties = Properties().apply {
            load(rootProject.file("local.properties").inputStream())
        }

        val allTickKey =
            localProperties.getProperty("ALLTICK_API_KEY") ?: ""

        buildConfigField(
            "String",
            "ALLTICK_API_KEY",
            "\"$allTickKey\""
        )
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}

dependencies {
    implementation(projects.core.network)
    implementation(libs.ktor.client.okhttp)
    implementation(libs.ktor.serialization.kotlinx.json)
    implementation(projects.domain)
    implementation(libs.kotlinx.datetime)
    implementation(libs.dagger)
    ksp(libs.dagger.compiler)
}