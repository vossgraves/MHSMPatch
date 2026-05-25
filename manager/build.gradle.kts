import java.util.Locale

val defaultManagerPackageName: String by rootProject.extra
val apiCode: Int by rootProject.extra
val verCode: Int by rootProject.extra
val verName: String by rootProject.extra
val coreVerCode: Int by rootProject.extra
val coreVerName: String by rootProject.extra
val miuixVersion = spotmanager.versions.miuix.get()

plugins {
    alias(libs.plugins.agp.app)
    alias(spotmanager.plugins.compose.compiler)
    alias(spotmanager.plugins.google.devtools.ksp)
    alias(spotmanager.plugins.rikka.tools.refine)
    alias(spotmanager.plugins.kotlin.android)
    id("kotlin-parcelize")
}

android {
    defaultConfig {
        applicationId = defaultManagerPackageName
    }

    packaging {
        jniLibs {
            excludes += "lib/*/libandroidx.graphics.path.so"
            excludes += "lib/*/libdatastore_shared_counter.so"
        }
        resources {
            excludes += "kotlin/**"
            excludes += "META-INF/androidx*"
            excludes += "META-INF/androidx/**"
            excludes += "DebugProbesKt.bin"
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = true      // 启用 R8/ProGuard 进行代码压缩、优化和混淆。
            isShrinkResources = true    // 启用资源缩减，移除未被引用的资源文件。
            isDebuggable = false        // 发布版本禁止调试。
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        all {
            sourceSets[name].assets.srcDirs(rootProject.projectDir.resolve("out/assets/$name"))
        }
    }

    buildFeatures {
        compose = true
        buildConfig = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.15"
    }

    namespace = "top.winner02.spotmanager"

    applicationVariants.all {
        kotlin.sourceSets {
            getByName(name) {
                kotlin.srcDir("build/generated/ksp/$name/kotlin")
            }
        }
    }
}

afterEvaluate {
    android.applicationVariants.forEach { variant ->
        val variantLowered = variant.name.lowercase()
        val variantCapped = variant.name.replaceFirstChar { it.uppercase() }

        val copyAssetsTaskProvider = tasks.register<Copy>("copy${variantCapped}Assets") {
            dependsOn(":meta-loader:copy$variantCapped")
            dependsOn(":patch-loader:copy$variantCapped")

            val targetDir = layout.buildDirectory.dir("intermediates/assets/$variantLowered/merge${variantCapped}Assets")
            doFirst {
                delete(targetDir.map { it.file("spotmanager/loader.dex") })
            }
            into(targetDir)

            from("${rootProject.projectDir}/out/assets/${variant.name}")
        }

        tasks.named("merge${variantCapped}Assets").configure {
            dependsOn(copyAssetsTaskProvider)
        }

        tasks.register<Copy>("build$variantCapped") {
            dependsOn("assemble$variantCapped")
            from(variant.outputs.map { it.outputFile })
            into("${rootProject.projectDir}/out/$variantLowered")
            rename(".*.apk", "SpotManager-v$verName-$verCode-$variantLowered.apk")
        }
    }
}

dependencies {
    implementation(projects.patch)
    implementation(projects.share.android)
    implementation(projects.share.java)
    implementation("vector:daemon-service")

    implementation(platform(spotmanager.androidx.compose.bom))
    implementation(spotmanager.androidx.activity.compose)
    implementation(spotmanager.androidx.compose.material.icons.extended)
    implementation(spotmanager.androidx.compose.material3)
    implementation(spotmanager.androidx.compose.ui)
    implementation(spotmanager.androidx.compose.ui.tooling.preview)
    implementation(spotmanager.androidx.core.ktx)
    implementation(libs.material)
    implementation(spotmanager.androidx.datastore.preferences)
    implementation(spotmanager.coil.compose)
    implementation(libs.gson)
    implementation(spotmanager.androidx.lifecycle.viewmodel.compose)
    implementation(spotmanager.androidx.navigation3.runtime)
    implementation(spotmanager.androidx.navigation3.ui)
    implementation(libs.androidx.preference)
    implementation(spotmanager.androidx.room.ktx)
    implementation(spotmanager.androidx.room.runtime)
    implementation("com.squareup.okhttp3:okhttp:5.3.2")

    implementation(libs.material)
    implementation(libs.gson)
    implementation(spotmanager.rikka.shizuku.api)
    implementation(spotmanager.rikka.shizuku.provider)
    implementation(spotmanager.rikka.refine)
    //implementation(spotmanager.raamcosta.compose.destinations)
    implementation(libs.appiconloader)
    implementation(libs.hiddenapibypass)

    // MiuiX & Haze
    implementation(spotmanager.haze)
    implementation(spotmanager.hazeBlur)
    implementation(spotmanager.backdrop)
    implementation("top.yukonga.miuix.kmp:miuix-ui:$miuixVersion")
    implementation("top.yukonga.miuix.kmp:miuix-preference:$miuixVersion")
    implementation("top.yukonga.miuix.kmp:miuix-icons:$miuixVersion")
    implementation(spotmanager.androidx.webkit)


    annotationProcessor(spotmanager.androidx.room.compiler)
    compileOnly(spotmanager.rikka.hidden.stub)
    ksp(spotmanager.androidx.room.compiler)
    //ksp(spotmanager.raamcosta.compose.destinations.ksp)

    debugImplementation(spotmanager.androidx.compose.ui.tooling)
    debugImplementation(spotmanager.androidx.customview)
    debugImplementation(spotmanager.androidx.customview.poolingcontainer)
}
