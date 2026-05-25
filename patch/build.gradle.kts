val androidSourceCompatibility: JavaVersion by rootProject.extra
val androidTargetCompatibility: JavaVersion by rootProject.extra

plugins {
    id("java-library")
}

java {
    sourceCompatibility = androidSourceCompatibility
    targetCompatibility = androidTargetCompatibility
    sourceSets {
        main {
            java.srcDirs("libs/manifest-editor/lib/src/main/java")
            resources.srcDirs("libs/manifest-editor/lib/src/main")
        }
    }
}

dependencies {
    implementation(projects.apkzlib)
    implementation(projects.share.java)
    implementation("vector:axml")

    implementation(spotmanager.commons.io)
    implementation(spotmanager.beust.jcommander)
    implementation(spotmanager.google.gson)
}
