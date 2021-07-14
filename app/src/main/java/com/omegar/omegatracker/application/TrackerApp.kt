package com.omegar.omegatracker.application

import androidx.multidex.MultiDexApplication
import com.omegar.omegatracker.di.AppComponent
import com.omegar.omegatracker.di.DaggerAppComponent

class TrackerApp : MultiDexApplication() {
    companion object {
        private lateinit var appComponent: AppComponent
    }

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder()
            .build()
    }
}