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
    implementation("vector:axml")
    implementation(projects.share.java)

    implementation(oqpatch.commons.io)
    implementation(oqpatch.beust.jcommander)
    implementation(oqpatch.google.gson)
}
