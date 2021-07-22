package com.omegar.omegatracker.ui.login

import com.omega_r.libs.omegatypes.toText
import com.omegar.omegatracker.ui.base.BasePresenter
import com.omegar.omegatracker.ui.home.HomeActivity

class LoginPresenter : BasePresenter<LoginView>() {

    fun requestLogin() {
        launchWithWaiting {
            val response = apiRepository.getUserInfo(TOKEN, "login,id,email,name")
            response.login?.let { viewState.showToast(it.toText()) }
            HomeActivity.newInstance().launch()
        }
    }
}