package com.omegar.omegatracker.di.modules

import android.content.SharedPreferences
import com.omegar.data.storage.SharedPreferencesTokenStorage
import com.omegar.domain.storage.TokenStorage
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object StorageModule {

    @Singleton
    @Provides
    fun provideTokenStorage(sharedPreferences: SharedPreferences): TokenStorage {
        return SharedPreferencesTokenStorage(sharedPreferences)
    }

}