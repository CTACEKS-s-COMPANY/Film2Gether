plugins {
    id("android-setup")
    id("compose-setup")
}

android {
    namespace = ProjectConfig.namespace("auth.ui")
}

dependencies {
    implementation(project(":core:di"))
    implementation(project(":core:ui"))

    implementation(project(":auth:domain"))

    //Yandex
    implementation(Dependencies.Other.yandexAuthSdk)
    implementation(project(":auth:data"))
}