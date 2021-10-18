import org.gradle.api.JavaVersion

object Config {
    const val application_id = "com.omegar.omegatracker"
    const val compile_sdk = 30
    const val build_tools = "30.0.3"
    const val min_sdk = 24
    const val target_sdk = 30
    val java_version = JavaVersion.VERSION_1_8
}

object KotlinOptions {
    const val jvm_target = "1.8"
}

object Releases {
    const val version_code = 1
    const val version_name = "1.0"
    const val base_url = "https://ytr.omega-r.club/"
    const val consumer_proguard_files = "consumer-rules.pro"
}

object Tests {
    const val runner = "androidx.test.runner.AndroidJUnitRunner"
}

object Modules {
    const val app = ":app"
    const val data = ":data"
    const val domain = ":domain"
}

object Versions {
    //Tools
    const val multidex = "1.0.3"
    const val constraintLayout = "2.0.4"
    const val recyclerView = "1.2.1"
    const val gridLayout = "1.0.0"
    const val annotation = "1.2.0"

    //Design
    const val appcompat = "1.3.0"
    const val material = "1.4.0"

    //Dagger
    const val dagger = "2.28.3"
    const val daggerCompiler = "2.28.3"

    //Kotlin
    const val core = "1.6.0"
    const val stdlib = "1.5.20"
    const val reflect = "1.5.20"
    const val coroutinesCore = "1.4.1"
    const val coroutinesAndroid = "1.4.1"

    //Retrofit
    const val retrofit = "2.9.0"
    const val converterMoshi = "2.9.0"
    const val interceptor = "4.9.0"

    //Moshi
    const val moshi = "1.12.0"

    //Room
    const val room = "2.4.0-alpha03"

    //Omega
    const val omegaBase = "1.4.1"
    const val omegaMoxy = "2.2.0"
    const val omegaTypes = "2.0.3"
    const val omegaGlide = "2.0.0"
    const val omegaBind = "1.0.2"
    const val omegaLaunchers = "1.0.3"
    const val omegaExtensions = "1.0.5"
    const val omegaIntentCore = "1.2.0"
    const val omegaViews = "1.1.0"
    const val omegaLayouts = "0.0.1"
    const val omegaRecyclerView = "1.9.8"
    const val omegaCenterIcon = "0.0.5"
    const val omegaClicks = "1.0.0"
    const val omegaAdapters = "1.0.1"
    const val omegaLint = "0.2.3"

    //MPChart
    const val androidChart = "v3.1.0"

    //Test
    const val jUnit = "4.13.2"
    const val androidxJUnit = "1.1.3"
    const val espressoCore = "3.4.0"

}

object Tools {
    const val multidex = "com.android.support:multidex:${Versions.multidex}"
    const val constraint_layout =
        "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
    const val recycler_view = "androidx.recyclerview:recyclerview:${Versions.recyclerView}"
    const val grid_layout = "androidx.gridlayout:gridlayout:${Versions.gridLayout}"
    const val androidx_annotation = "androidx.annotation:annotation:${Versions.annotation}"
}

object Design {
    const val appcompat = "androidx.appcompat:appcompat:${Versions.appcompat}"
    const val material = "com.google.android.material:material:${Versions.material}"
}

object Dagger {
    const val dagger = "com.google.dagger:dagger:${Versions.dagger}"
    const val dagger_compiler = "com.google.dagger:dagger-compiler:${Versions.dagger}"
}

object Kotlin {
    const val core = "androidx.core:core-ktx:${Versions.core}"
    const val stdlib = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.stdlib}"
    const val coroutines_core =
        "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutinesCore}"
    const val coroutines_android =
        "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutinesAndroid}"
    const val reflect = "org.jetbrains.kotlin:kotlin-reflect:${Versions.reflect}"
}

object Omega {
    //OmegaBase
    const val base_core = "com.github.Omega-R.OmegaBase:core:${Versions.omegaBase}"
    const val base_annotation = "com.github.Omega-R.OmegaBase:annotations:${Versions.omegaBase}"
    const val base_processor = "com.github.Omega-R.OmegaBase:processor:${Versions.omegaBase}"

    //OmegaMoxy
    const val moxy = "com.github.Omega-R.OmegaMoxy:moxy:${Versions.omegaMoxy}"
    const val moxy_androidx = "com.github.Omega-R.OmegaMoxy:moxy-androidx:${Versions.omegaMoxy}"
    const val moxy_ktx = "com.github.Omega-R.OmegaMoxy:moxy-ktx:${Versions.omegaMoxy}"
    const val moxy_compiler = "com.github.Omega-R.OmegaMoxy:moxy-compiler:${Versions.omegaMoxy}"

    //OmegaFeatures
    const val intent_builder_core =
        "com.github.Omega-R.OmegaIntentBuilder:core:${Versions.omegaIntentCore}"
    const val launchers = "com.github.Omega-R:OmegaLaunchers:${Versions.omegaLaunchers}"
    const val views = "com.github.Omega-R:OmegaViews:${Versions.omegaViews}"
    const val extensions = "com.github.Omega-R:OmegaExtensions:${Versions.omegaExtensions}"
    const val layouts = "com.github.Omega-R:OmegaLayouts:${Versions.omegaLayouts}"
    const val types = "com.github.Omega-R.OmegaTypes:omegatypes:${Versions.omegaTypes}"
    const val glide = "com.github.Omega-R.OmegaTypes:glide:${Versions.omegaGlide}"
    const val center_icon_btn =
        "com.github.Omega-R:OmegaCenterIconButton:${Versions.omegaCenterIcon}@aar"
    const val bind = "com.github.Omega-R:OmegaBind:${Versions.omegaBind}@aar"
    const val clicks = "com.github.Omega-R:OmegaClicks:${Versions.omegaClicks}"
    const val adapters = "com.github.Omega-R:OmegaAdapters:${Versions.omegaAdapters}@aar"
    const val recycler_view =
        "com.github.Omega-R:OmegaRecyclerView:${Versions.omegaRecyclerView}@aar"

    //OmegaLint
    const val lint = "com.github.omega-r:omegalint:${Versions.omegaLint}"
}

object Moshi {
    const val moshi_kotlin = "com.squareup.moshi:moshi-kotlin:${Versions.moshi}"
    const val moshi_codegen = "com.squareup.moshi:moshi-kotlin-codegen:${Versions.moshi}"
    const val moshi_adapter = "com.squareup.moshi:moshi-adapters:${Versions.moshi}"
}

object Retrofit {
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val retrofit_moshi_converter =
        "com.squareup.retrofit2:converter-moshi:${Versions.converterMoshi}"
    const val okhttp3_logging_interceptor =
        "com.squareup.okhttp3:logging-interceptor:${Versions.interceptor}"
}

object Room {
    const val room_runtime = "androidx.room:room-runtime:${Versions.room}"
    const val room_ktx = "androidx.room:room-ktx:${Versions.room}"
    const val room_compiler = "androidx.room:room-compiler:${Versions.room}"
}

object MPChart {
    const val chart = "com.github.PhilJay:MPAndroidChart:${Versions.androidChart}"
}

object TestImpl {
    const val room_testing = "androidx.room:room-testing:${Versions.room}"
    const val junit = "junit:junit:${Versions.jUnit}"
    const val androidx_junit = "androidx.test.ext:junit:${Versions.androidxJUnit}"
    const val espresso = "androidx.test.espresso:espresso-core:${Versions.espressoCore}"
}