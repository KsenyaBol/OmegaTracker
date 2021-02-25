package com.omegar.omegatracker.ui.screens.base

import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.annotation.ContentView
import androidx.annotation.LayoutRes
import com.omega_r.base.annotations.OmegaWindowBackground
import com.omega_r.base.components.OmegaActivity
import com.omega_r.base.mvp.presenters.OmegaPresenter
import com.omega_r.libs.extensions.context.getColorByAttribute
import com.omegar.omegatracker.R

@OmegaWindowBackground(colorAttrRes = R.attr.activityBackground)
abstract class BaseActivity : OmegaActivity {

    constructor() : super()

    @ContentView
    constructor(@LayoutRes contentLayoutId: Int) : super(contentLayoutId)

    abstract override val presenter: OmegaPresenter<out BaseView>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setBackgroundDrawable(ColorDrawable(getColorByAttribute(R.attr.activityBackground)))
    }
}