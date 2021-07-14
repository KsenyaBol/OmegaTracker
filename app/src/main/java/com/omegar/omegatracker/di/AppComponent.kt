package com.omegar.omegatracker.di

import com.omegar.omegatracker.di.modules.ContextModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        ContextModule::class,
    ]
)

interface AppComponent {

}