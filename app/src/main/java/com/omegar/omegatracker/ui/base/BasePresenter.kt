package com.omegar.omegatracker.ui.base

import com.omega_r.base.mvp.presenters.OmegaPresenter
import com.omegar.omegatracker.application.TrackerApp

open class BasePresenter<View : BaseView> : OmegaPresenter<View>() {
    companion object {
        const val TOKEN = "Bearer perm:aXZhbi5idWx5Z2lu.NDktMTc=.G0lwJuz1DoBw0Pbq8rC6srEzY4sSKr"
    }
    private val appComponent = TrackerApp.getAppComponent()
    protected val apiRepository = appComponent.getApiRepository()
}