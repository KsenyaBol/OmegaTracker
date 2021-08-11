package com.omegar.omegatracker.ui.login

import android.view.View
import com.omega_r.libs.omegatypes.Text
import com.omega_r.libs.omegatypes.toast
import com.omegar.mvp.ktx.providePresenter
import com.omegar.omegatracker.R
import com.omegar.omegatracker.ui.base.BaseActivity

class LoginActivity : BaseActivity(R.layout.activity_login), LoginView {
    override val presenter: LoginPresenter by providePresenter()

    private val buttonLogin: View by bind(R.id.button_activity_login_start) {
        setClickListener {
            presenter.requestLogin()
        }
    }

    override fun showToast(message: Text) {
        toast(message)
    }
}