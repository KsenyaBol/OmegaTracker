package com.omegar.omegatracker.di.modules

import android.content.Context
import com.omegar.omegatracker.BuildConfig
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ContextModule(private val context: Context) {
    @Provides
    @Singleton
    fun provideContext() = context

    @Provides
    @Singleton
    fun provideSharePrefs() = context.getSharedPreferences(BuildConfig.APPLICATION_ID, Context.MODE_PRIVATE)


}