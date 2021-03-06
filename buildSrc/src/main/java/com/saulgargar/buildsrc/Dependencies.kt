package com.saulgargar.buildsrc

object Versions{
    const val kotlin = "1.3.70"

    const val appcompat = "1.1.0"
    const val core = "1.2.0"
    const val lifecycle = "2.2.0"
    const val legacy = "1.0.0"
    const val constraint = "1.1.3"

    const val material = "1.1.0"

    const val junit = "4.12"
    const val runner = "1.2.0"
    const val expresso = "3.2.0"

    const val navFragment = "2.2.0"
    const val navUiKtx = "2.2.0"

    const val lifeCycleExtensions = "2.2.0"

    const val retrofit = "2.7.2"
    const val gson = "2.7.2"
    const val okhttp = "4.4.0"

    const val koin = "2.1.1"
    const val koinViewModel = "2.1.1"

    const val glide = "4.5.0"
}

object Kotlin {
    const val kotlinStandard = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin}"
}

object AndroidX {
    const val appCompat = "androidx.appcompat:appcompat:${Versions.appcompat}"
    const val core = "androidx.core:core-ktx:${Versions.core}"
    const val legacy = "androidx.legacy:legacy-support-v4:${Versions.legacy}"
    const val lifecycleExtensions = "androidx.lifecycle:lifecycle-extensions:${Versions.lifecycle}"
    const val lifecycleCompiler = "androidx.lifecycle:lifecycle-compiler:${Versions.lifecycle}"
    const val lifecycleRuntime = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycle}"
    const val lifecycle = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}"
    const val constraintlayout = "androidx.constraintlayout:constraintlayout:${Versions.constraint}"
}

object Google {
    const val material = "com.google.android.material:material:${Versions.material}"
}

object Testing {
    const val junit = "junit:junit:${Versions.junit}"
    const val runner = "androidx.test:runner:${Versions.runner}"
    const val expresso = "androidx.test.espresso:espresso-core:${Versions.expresso}"
}

object NavigationComponents {
    const val navFragment = "androidx.navigation:navigation-fragment-ktx:${Versions.navFragment}"
    const val navUiKtx = "androidx.core:core-ktx:${Versions.navUiKtx}"
}

object ViewModel {
    const val lifeCycleExtensions = "androidx.lifecycle:lifecycle-extensions:${Versions.lifeCycleExtensions}"
}

object Retrofit  {
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val gson = "com.squareup.retrofit2:converter-gson:${Versions.gson}"
    const val okhttp = "com.squareup.okhttp3:logging-interceptor:${Versions.okhttp}"
}

object Koin {
    const val koin = "org.koin:koin-android:${Versions.koin}"
    const val koinViewModel = "org.koin:koin-androidx-viewmodel:${Versions.koinViewModel}"
}

object Glide {
    const val glide = "com.github.bumptech.glide:glide:${Versions.glide}"
}

object ThirdPartyLibrary {

}