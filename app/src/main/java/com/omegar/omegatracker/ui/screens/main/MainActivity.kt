package com.omegar.omegatracker.ui.screens.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.omega_r.base.mvp.presenters.OmegaPresenter
import com.omega_r.base.mvp.presenters.providePresenter
import com.omegar.omegatracker.R
import com.omegar.omegatracker.ui.screens.base.BaseActivity
import com.omegar.omegatracker.ui.screens.base.BaseView

class MainActivity : BaseActivity(R.layout.activity_main) {
    override val presenter: MainPresenter by providePresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}