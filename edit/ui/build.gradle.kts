plugins {
    id("android-setup")
    id("compose-setup")
}

android {
    namespace = ProjectConfig.namespace("edit.ui")
}

dependencies {
    implementation(project(":core:di"))
    implementation(project(":core:ui"))

    implementation(project(":core:data"))

    implementation(project(":auth:domain"))
    implementation(project(":settings:domain"))

    implementation(project(":tasks:domain"))
    implementation("io.coil-kt:coil-compose:2.0.0")
}