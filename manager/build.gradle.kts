import java.util.Locale

val defaultManagerPackageName: String by rootProject.extra
val apiCode: Int by rootProject.extra
val verCode: Int by rootProject.extra
val verName: String by rootProject.extra
val coreVerCode: Int by rootProject.extra
val coreVerName: String by rootProject.extra

plugins {
    alias(libs.plugins.agp.app)
    alias(oqpatch.plugins.compose.compiler)
    alias(oqpatch.plugins.google.devtools.ksp)
    alias(oqpatch.plugins.rikka.tools.refine)
    alias(oqpatch.plugins.kotlin.android)
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

    namespace = "org.lsposed.oqpatch"

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
            rename(".*.apk", "OQPatch-v$verName-$verCode-$variantLowered.apk")
        }
    }
}

dependencies {
    implementation(projects.patch)
    implementation(projects.services.daemonService)
    implementation(projects.share.android)
    implementation(projects.share.java)

    implementation(platform(oqpatch.androidx.compose.bom))
    implementation(oqpatch.androidx.activity.compose)
    implementation(oqpatch.androidx.compose.material.icons.extended)
    implementation(oqpatch.androidx.compose.material3)
    implementation(oqpatch.androidx.compose.ui)
    implementation(oqpatch.androidx.compose.ui.tooling.preview)
    implementation(oqpatch.androidx.core.ktx)
    implementation(oqpatch.androidx.lifecycle.viewmodel.compose)
    implementation(oqpatch.androidx.navigation.compose)
    implementation(libs.androidx.preference)
    implementation(oqpatch.androidx.room.ktx)
    implementation(oqpatch.androidx.room.runtime)

    implementation(oqpatch.google.accompanist.navigation.animation)
    implementation(oqpatch.google.accompanist.pager)
    implementation(oqpatch.google.accompanist.swiperefresh)
    implementation(libs.material)
    implementation(libs.gson)
    implementation(oqpatch.rikka.shizuku.api)
    implementation(oqpatch.rikka.shizuku.provider)
    implementation(oqpatch.rikka.refine)
    implementation(oqpatch.raamcosta.compose.destinations)
    implementation(libs.appiconloader)
    implementation(libs.hiddenapibypass)

    annotationProcessor(oqpatch.androidx.room.compiler)
    compileOnly(oqpatch.rikka.hidden.stub)
    ksp(oqpatch.androidx.room.compiler)
    ksp(oqpatch.raamcosta.compose.destinations.ksp)

    debugImplementation(oqpatch.androidx.compose.ui.tooling)
    debugImplementation(oqpatch.androidx.customview)
    debugImplementation(oqpatch.androidx.customview.poolingcontainer)
}