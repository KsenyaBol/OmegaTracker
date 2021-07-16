package com.omegar.omegatracker.di

import com.omegar.data.repository.ApiRepository
import com.omegar.omegatracker.di.modules.ContextModule
import com.omegar.omegatracker.di.modules.RepositoryModule
import com.omegar.omegatracker.di.modules.RetrofitModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        ContextModule::class,
        RetrofitModule::class,
        RepositoryModule::class
    ]
)

interface AppComponent {
    fun getApiRepository(): ApiRepository
}