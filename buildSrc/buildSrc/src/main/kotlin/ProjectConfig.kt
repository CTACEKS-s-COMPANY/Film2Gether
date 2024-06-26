import org.gradle.api.JavaVersion

/**
 * Provides android project config
 */
object ProjectConfig {
    fun namespace(name: String) = "com.ctaceks.$name"

    const val compileSdk = 33
    const val applicationId = "com.ctaceks.todoapp"
    const val minSdk = 30
    const val targetSdk = 33
    const val versionCode = 1
    const val versionName = "1.0"

    const val kotlinCompilerExtensionVersion = "1.4.7"

    val javaVersion = JavaVersion.VERSION_17
    const val jvmTarget = "17"
}
