
plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.koin)
    alias(libs.plugins.kotlinSerialization)
}

group = "com.vi"
version = "unspecified"

dependencies {
}

kotlin {
    compilerOptions {
        freeCompilerArgs.add("-Xexpect-actual-classes")
    }
    jvm()

    js {
        useEsModules()
        browser()
        binaries.executable()
    }

    sourceSets {
        commonMain.dependencies {
            implementation(project.dependencies.platform(libs.koin.bom))

            implementation(libs.ktor.client.core)
            implementation(libs.ktor.client.content.negotiation)
            implementation(libs.ktor.serialization.kotlinx.json)
            implementation(libs.koin.core)
            api(libs.koin.annotations)
        }
        webMain.dependencies {
            implementation(libs.kotlinx.browser)
        }
    }

}