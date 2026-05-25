plugins {
    alias(libs.plugins.agp.lib)
}

android {
    namespace = "top.winner02.spotmanager.share"

    buildFeatures {
        androidResources = false
        buildConfig = false
    }
}

dependencies {
    implementation("vector:daemon-service")
}
