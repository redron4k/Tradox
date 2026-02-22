plugins {
    alias(libs.plugins.kotlin.jvm)
}


kotlin {
    jvmToolchain(11)
}

dependencies {
    implementation(projects.core.network)
    implementation(projects.domain)
    implementation(libs.kotlinx.datetime)
}