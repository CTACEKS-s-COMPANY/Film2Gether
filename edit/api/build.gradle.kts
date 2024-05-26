plugins {
    id("android-setup")
}

android {
    namespace = ProjectConfig.namespace("edit:api")
}

dependencies {
    implementation("org.java-websocket:Java-WebSocket:1.4.0")
}