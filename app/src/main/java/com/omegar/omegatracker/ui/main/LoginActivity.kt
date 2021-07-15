package com.omegar.omegatracker.ui.main

import android.os.Bundle
import com.omegar.mvp.ktx.providePresenter
import com.omegar.omegatracker.R
import com.omegar.omegatracker.ui.base.BaseActivity

class LoginActivity : BaseActivity(R.layout.activity_login) {
    override val presenter: LoginPresenter by providePresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}