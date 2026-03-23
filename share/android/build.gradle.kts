plugins {
    alias(libs.plugins.agp.lib)
}

android {
    namespace = "org.lsposed.oqpatch.share"

    buildFeatures {
        androidResources = false
        buildConfig = false
    }
}

dependencies {
    implementation("vector:daemon-service")
}
