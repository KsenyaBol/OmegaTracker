package com.omegar.omegatracker.ui.base

import com.omega_r.base.mvp.presenters.OmegaPresenter
import com.omegar.omegatracker.application.TrackerApp

open class BasePresenter<View : BaseView> : OmegaPresenter<View>() {

    private val appComponent = TrackerApp.getAppComponent()
    protected val loginRepository = appComponent.getLoginRepository()
    protected val issueRepository = appComponent.getIssueRepository()
}