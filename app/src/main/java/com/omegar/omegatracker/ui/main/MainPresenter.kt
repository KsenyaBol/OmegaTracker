package com.omegar.omegatracker.ui.main

import com.omegar.omegatracker.ui.base.BasePresenter
import com.omegar.omegatracker.ui.login.LoginActivity

class MainPresenter(private val authToken: String?) : BasePresenter<MainView>() {

    init {
        viewState.mainPages = MainPage.values().toList()
        viewState.currentMainPage = MainPage.values().first()
    }

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        tokenStorage.token = authToken
    }

    fun requestChangeMainPage(newPage: MainPage): Boolean {
        viewState.currentMainPage = newPage
        return true
    }

    fun onLogoutClicked() {
        tokenStorage.token = null
        LoginActivity.createLauncher().launch()
        viewState.exit()
    }
}