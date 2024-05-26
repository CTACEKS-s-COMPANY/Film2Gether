import org.gradle.api.artifacts.dsl.Dependencies

plugins {
    id("android-setup")
    id("org.jetbrains.kotlin.plugin.serialization")
}

android {
    namespace = ProjectConfig.namespace("auth.domain")
}

dependencies {
}
