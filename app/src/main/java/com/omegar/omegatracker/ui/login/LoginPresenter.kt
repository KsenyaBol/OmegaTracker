package com.omegar.omegatracker.ui.login

import com.omega_r.libs.omegatypes.toText
import com.omegar.omegatracker.ui.base.BasePresenter
import com.omegar.omegatracker.ui.home.HomeActivity

class LoginPresenter : BasePresenter<LoginView>() {
    companion object {
        const val TOKEN = "Bearer perm:aXZhbi5idWx5Z2lu.NDktMTc=.G0lwJuz1DoBw0Pbq8rC6srEzY4sSKr"
    }

    fun requestLogin() {
        launchWithWaiting {
            val response = apiRepository.getUserInfo(TOKEN, "login,id,email,name")
            response.login?.let { viewState.showToast(it.toText()) }
            HomeActivity.newInstance().launch()
        }
    }
}