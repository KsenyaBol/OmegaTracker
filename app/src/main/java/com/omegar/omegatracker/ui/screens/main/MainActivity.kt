package com.omegar.omegatracker.ui.screens.main

import android.os.Bundle
import com.omegar.mvp.ktx.providePresenter
import com.omegar.omegatracker.R
import com.omegar.omegatracker.ui.screens.base.BaseActivity

class MainActivity : BaseActivity(R.layout.activity_main) {
    override val presenter: MainPresenter by providePresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}