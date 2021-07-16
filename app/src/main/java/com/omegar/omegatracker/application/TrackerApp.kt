package com.omegar.omegatracker.application

import androidx.multidex.MultiDexApplication
import com.omegar.omegatracker.di.AppComponent
import com.omegar.omegatracker.di.DaggerAppComponent
import com.omegar.omegatracker.di.modules.ContextModule

class TrackerApp : MultiDexApplication() {
    companion object {
        private lateinit var appComponent: AppComponent
        fun getAppComponent() = appComponent
    }

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder()
            .contextModule(ContextModule(this))
            .build()
    }
}