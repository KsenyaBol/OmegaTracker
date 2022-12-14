package com.omegar.omegatracker.ui.login

import com.omega_r.libs.omegatypes.Text
import com.omegar.data.TokenException
import com.omegar.omegatracker.R
import com.omegar.omegatracker.ui.base.BasePresenter
import com.omegar.omegatracker.ui.main.MainActivity
import retrofit2.HttpException

class LoginPresenter : BasePresenter<LoginView>() {
    companion object {

        private const val TOKEN_PREFIX = "perm:"
        private const val TOKEN_BEARER = "Bearer "
    }

    init {
        tokenStorage.token?.let { token ->
            MainActivity.createLauncher(token).launch()
            viewState.exit()
        }
    }

    fun requestLogin(token: String) {
        launchWithWaiting {
            try {
                val authToken = if (token.startsWith(TOKEN_PREFIX)) TOKEN_BEARER + token else TOKEN_BEARER + TOKEN_PREFIX + token
                val response = loginRepository.getUserInfo(authToken)

                tokenStorage.token = authToken

                viewState.showToast(Text.from(R.string.label_welcome) + (response.login ?: ""))

                MainActivity.createLauncher(authToken).launch()
                viewState.exit()
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