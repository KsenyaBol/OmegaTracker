package com.omegar.omegatracker.ui.login

import com.omega_r.libs.omegatypes.Text
import com.omega_r.libs.omegatypes.toText
import com.omegar.data.TokenException
import com.omegar.domain.entity.UserProfile
import com.omegar.libs.omegalaunchers.tools.put
import com.omegar.omegatracker.R
import com.omegar.omegatracker.ui.base.BasePresenter
import com.omegar.omegatracker.ui.home.HomeActivity
import retrofit2.HttpException

class LoginPresenter : BasePresenter<LoginView>() {
    companion object {
        private const val BASE_TOKEN = "Bearer perm:"
        private const val AUTHORIZATION_TOKEN = "token"
    }

    private lateinit var authToken: String
    private lateinit var response: UserProfile

    fun requestLogin(token: String) {
        launchWithWaiting {
            try {
                authToken = BASE_TOKEN + token
                response = loginRepository.getUserInfo(authToken)
                response.login?.let { viewState.showToast(Text.from(R.string.label_welcome) + it.toText()) }
                HomeActivity.newInstance(AUTHORIZATION_TOKEN put authToken).launch()
            } catch (e: HttpException) {
                throw TokenException()
            }
        }
    }

    override fun getErrorMessage(throwable: Throwable): Text {
        return when (throwable) {
            is TokenException -> Text.from(R.string.label_invalid_token)
            else -> super.getErrorMessage(throwable)
        }
    }
}