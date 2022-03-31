package com.omegar.omegatracker.ui.base

import com.omega_r.base.mvp.presenters.OmegaPresenter
import com.omegar.omegatracker.application.TrackerApp
import com.omegar.omegatracker.di.AppComponent

open class BasePresenter<View : BaseView> : OmegaPresenter<View>(), AppComponent by TrackerApp.getAppComponent() {

}