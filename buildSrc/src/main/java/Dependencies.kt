object Versions {
    const val gradle_plugin = "3.0.1"
    const val kotlin = "1.2.30"
    const val dagger = "2.12"
    const val mockito_core = "2.15.0"
    const val android_arch = "1.0.0"
    const val support = "26.1.0"
    const val retrofit = "2.3.0"
    const val espresso = "3.0.1"
}

object Libs {
    const val kotlin_stdlib = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlin}"
    const val dagger = "com.google.dagger:dagger:${Versions.dagger}"
    const val dagger_compiler = "com.google.dagger:dagger-compiler:${Versions.dagger}"
    const val mockito_core = "org.mockito:mockito-core:${Versions.mockito_core}"
    const val mockito_android = "org.mockito:mockito-android:${Versions.mockito_core}"
    const val arch_testing = "android.arch.core:core-testing:${Versions.android_arch}"
    const val arch_lifecycle = "android.arch.lifecycle:extensions:${Versions.android_arch}"
    const val arch_compiler = "android.arch.lifecycle:compiler:${Versions.android_arch}"
    const val kotlinx_coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-core:0.21.2"
    const val anko_coroutines = "org.jetbrains.anko:anko-coroutines:0.10.4"
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val retrofit_gson_adapter = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"
    const val retrofit_sync_adapter = "com.jaredsburrows.retrofit:retrofit2-synchronous-adapter:0.4.0"


    const val support_appcompat_v7 = "com.android.support:appcompat-v7:${Versions.support}"
    const val recycler_view = "com.android.support:recyclerview-v7:${Versions.support}"
    const val constraint_layout = "com.android.support.constraint:constraint-layout:1.0.2"

    const val junit = "junit:junit:4.12"
    const val test_runner = "com.android.support.test:runner:1.0.1"
    const val espresso_core = "com.android.support.test.espresso:espresso-core:${Versions.espresso}"
    const val espresso_idling_res = "com.android.support.test.espresso:espresso-idling-resource:${Versions.espresso}"
    const val espresso_contrib = "com.android.support.test.espresso:espresso-contrib:${Versions.espresso}"
}

object Plugins {
    val android_gradle = "com.android.tools.build:gradle:${Versions.gradle_plugin}"
    val kotlin_gradle = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
}