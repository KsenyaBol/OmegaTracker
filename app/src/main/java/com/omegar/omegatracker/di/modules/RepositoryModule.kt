package com.omegar.omegatracker.di.modules

import com.omegar.data.api.TrackerApi
import com.omegar.data.repository.ApiRepository
import com.omegar.data.repository.ApiRepositoryImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {
    @Provides
    @Singleton
    fun providesApiRepository(trackerApi: TrackerApi): ApiRepository = ApiRepositoryImpl(trackerApi)
}