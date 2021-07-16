package com.omegar.omegatracker.di.modules

import android.content.Context
import com.omega_r.base.errors.ErrorHandler
import com.omega_r.base.remote.CoroutineCallAdapterFactory
import com.omega_r.base.remote.adapters.TextAdapter
import com.omegar.data.AppErrorHandler
import com.omegar.domain.entity.Issue
import com.omegar.omegatracker.BuildConfig
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module(includes = [ContextModule::class])
class RetrofitModule {
    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient, moshi: Moshi, errorHandler: ErrorHandler): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(client)
            .addCallAdapterFactory(CoroutineCallAdapterFactory(errorConverter = errorHandler))
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
    }

    @Provides
    @Singleton
    fun provideClient(): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = when {
            BuildConfig.DEBUG -> HttpLoggingInterceptor.Level.BODY
            else -> HttpLoggingInterceptor.Level.NONE
        }
        return OkHttpClient.Builder()
            .addNetworkInterceptor(loggingInterceptor)
            .build()
    }

    @Provides
    @Singleton
    fun provideMoshi(context: Context): Moshi {
        return Moshi.Builder()
            .add(Issue::class.java, TextAdapter(context).nullSafe())
            .add(KotlinJsonAdapterFactory())
            .build()
    }

    @Provides
    @Singleton
    fun provideErrorHandler(moshi: Moshi): ErrorHandler = AppErrorHandler(moshi)
}