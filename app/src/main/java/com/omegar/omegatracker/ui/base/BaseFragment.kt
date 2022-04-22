package com.omegar.omegatracker.ui.base

import com.omega_r.base.components.OmegaFragment

abstract class BaseFragment(contentLayoutId: Int) : OmegaFragment(contentLayoutId), BaseView {

    abstract override val presenter: BasePresenter<out BaseView>

}