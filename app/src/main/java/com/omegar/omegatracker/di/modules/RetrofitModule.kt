package com.omegar.omegatracker.di.modules

import android.content.Context
import com.omega_r.base.errors.ErrorHandler
import com.omega_r.base.remote.CoroutineCallAdapterFactory
import com.omegar.data.AppErrorHandler
import com.omegar.data.api.TrackerApi
import com.omegar.data.entities.api.ResponsePriority
import com.omegar.data.entities.api.ResponseSpentTime
import com.omegar.data.entities.api.ResponseState
import com.omegar.data.entities.api.ResponseUnknownEntity
import com.omegar.data.entities.enumcollection.ValueType
import com.omegar.domain.entity.api.CustomFields
import com.omegar.omegatracker.BuildConfig
import com.squareup.moshi.Moshi
import com.squareup.moshi.adapters.PolymorphicJsonAdapterFactory
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
            .add(
                PolymorphicJsonAdapterFactory.of(CustomFields::class.java, "name")
                    .withSubtype(ResponsePriority::class.java, ValueType.PRIORITY.searchName)
                    .withSubtype(ResponseState::class.java, ValueType.STATE.searchName)
                    .withSubtype(ResponseSpentTime::class.java, ValueType.SPENT_TIME.searchName)
                    .withDefaultValue(ResponseUnknownEntity())
            )
            .add(KotlinJsonAdapterFactory())
            .build()
    }

    @Provides
    @Singleton
    fun provideErrorHandler(moshi: Moshi): ErrorHandler = AppErrorHandler(moshi)

    @Provides
    @Singleton
    fun provideTrackerApi(retrofit: Retrofit): TrackerApi = retrofit.create(TrackerApi::class.java)
}