package com.omegar.omegatracker.ui.home

import com.omegar.libs.omegalaunchers.createActivityLauncher
import com.omegar.mvp.ktx.providePresenter
import com.omegar.omegatracker.R
import com.omegar.omegatracker.ui.base.BaseActivity

class HomeActivity : BaseActivity(R.layout.activity_home) {

    companion object {
        fun newInstance() = createActivityLauncher()
    }

    override val presenter: HomePresenter by providePresenter()

}