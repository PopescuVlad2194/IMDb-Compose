buildscript {
    dependencies {
        classpath(Build.androidBuildTools)
        classpath(Build.hiltAndroidGradlePlugin)
        classpath(Build.kotlinGradlePlugin)
        classpath(Build.secretsGradlePlugin)
    }
}
plugins {
    id("com.android.application") version Build.androidApplicationPluginVersion apply false
    id("com.android.library") version Build.androidLibraryPluginVersion apply false
    id("org.jetbrains.kotlin.android") version Kotlin.version apply false
    id("org.jetbrains.kotlin.jvm") version Kotlin.jvmVersion apply false
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}