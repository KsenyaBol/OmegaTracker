package com.omegar.omegatracker.ui.login

import android.widget.Button
import android.widget.EditText
import com.omegar.libs.omegalaunchers.createActivityLauncher
import com.omegar.mvp.ktx.providePresenter
import com.omegar.omegatracker.R
import com.omegar.omegatracker.ui.base.BaseActivity

class LoginActivity : BaseActivity(R.layout.activity_login), LoginView {

    companion object {

        fun createLauncher() = createActivityLauncher()

    }

    override val presenter: LoginPresenter by providePresenter()

    private val inputToken: EditText by bind(R.id.input_login_token)
    private val buttonLogin: Button by bind(R.id.button_login_start) {
        setClickListener {
            presenter.requestLogin(inputToken.text.toString())
        }
    }
}