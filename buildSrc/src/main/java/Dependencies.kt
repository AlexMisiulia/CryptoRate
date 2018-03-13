object Versions {
    val gradle_plugin = "3.0.1"
    const val kotlin = "1.2.30"
}

object Libs {
    const val kotlin_stdlib = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlin}"
}

object Plugins {
    val android_gradle = "com.android.tools.build:gradle:${Versions.gradle_plugin}"
    val kotlin_gradle = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
}