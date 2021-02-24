package com.omegar.omegatracker.ui.screens.base

import androidx.annotation.ContentView
import androidx.annotation.LayoutRes
import com.omega_r.base.components.OmegaActivity
import com.omega_r.base.mvp.presenters.OmegaPresenter

abstract class BaseActivity : OmegaActivity {

    constructor() : super()

    @ContentView
    constructor(@LayoutRes contentLayoutId: Int) : super(contentLayoutId)

    abstract override val presenter: OmegaPresenter<out BaseView>
}