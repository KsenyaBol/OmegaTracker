package com.omegar.omegatracker.ui.base

import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.annotation.ContentView
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatDelegate
import com.omega_r.base.components.OmegaActivity
import com.omega_r.base.mvp.presenters.OmegaPresenter
import com.omega_r.libs.extensions.context.getColorByAttribute
import com.omegar.omegatracker.R

abstract class BaseActivity : OmegaActivity, BaseView {

    constructor() : super()

    @ContentView
    constructor(@LayoutRes contentLayoutId: Int) : super(contentLayoutId)

    abstract override val presenter: OmegaPresenter<out BaseView>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setBackgroundDrawable(ColorDrawable(getColorByAttribute(R.attr.activityBackground)))
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
    }
}