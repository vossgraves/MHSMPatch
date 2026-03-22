import java.util.Locale

val defaultManagerPackageName: String by rootProject.extra
val apiCode: Int by rootProject.extra
val verCode: Int by rootProject.extra
val verName: String by rootProject.extra
val coreVerCode: Int by rootProject.extra
val coreVerName: String by rootProject.extra

plugins {
    alias(libs.plugins.agp.app)
    alias(mhsmpatch.plugins.compose.compiler)
    alias(mhsmpatch.plugins.google.devtools.ksp)
    alias(mhsmpatch.plugins.rikka.tools.refine)
    alias(mhsmpatch.plugins.kotlin.android)
    id("kotlin-parcelize")
}

android {
    defaultConfig {
        applicationId = defaultManagerPackageName
    }

    androidResources {
        noCompress.add(".so")
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

    namespace = "org.lsposed.mhsmpatch"

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
            rename(".*.apk", "MHSMPatch-v$verName-$verCode-$variantLowered.apk")
        }
    }
}

dependencies {
    implementation(projects.patch)
    implementation(projects.services.daemonService)
    implementation(projects.share.android)
    implementation(projects.share.java)

    implementation(platform(mhsmpatch.androidx.compose.bom))
    implementation(mhsmpatch.androidx.activity.compose)
    implementation(mhsmpatch.androidx.compose.material.icons.extended)
    implementation(mhsmpatch.androidx.compose.material3)
    implementation(mhsmpatch.androidx.compose.ui)
    implementation(mhsmpatch.androidx.compose.ui.tooling.preview)
    implementation(mhsmpatch.androidx.core.ktx)
    implementation(mhsmpatch.androidx.lifecycle.viewmodel.compose)
    implementation(mhsmpatch.androidx.navigation.compose)
    implementation(libs.androidx.preference)
    implementation(mhsmpatch.androidx.room.ktx)
    implementation(mhsmpatch.androidx.room.runtime)

    implementation(mhsmpatch.google.accompanist.navigation.animation)
    implementation(mhsmpatch.google.accompanist.pager)
    implementation(mhsmpatch.google.accompanist.swiperefresh)
    implementation(libs.material)
    implementation(libs.gson)
    implementation(mhsmpatch.rikka.shizuku.api)
    implementation(mhsmpatch.rikka.shizuku.provider)
    implementation(mhsmpatch.rikka.refine)
    implementation(mhsmpatch.raamcosta.compose.destinations)
    implementation(libs.appiconloader)
    implementation(libs.hiddenapibypass)

    annotationProcessor(mhsmpatch.androidx.room.compiler)
    compileOnly(mhsmpatch.rikka.hidden.stub)
    ksp(mhsmpatch.androidx.room.compiler)
    ksp(mhsmpatch.raamcosta.compose.destinations.ksp)

    debugImplementation(mhsmpatch.androidx.compose.ui.tooling)
    debugImplementation(mhsmpatch.androidx.customview)
    debugImplementation(mhsmpatch.androidx.customview.poolingcontainer)
}