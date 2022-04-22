package com.omegar.omegatracker.ui.timer

import com.omegar.mvp.viewstate.strategy.StateStrategyType
import com.omegar.mvp.viewstate.strategy.StrategyType
import com.omegar.omegatracker.ui.base.BaseView

interface TimerView : BaseView {

    @StateStrategyType(StrategyType.ADD_TO_END_SINGLE)
    fun setVisible(isRun: Boolean)

    @StateStrategyType(StrategyType.ADD_TO_END_SINGLE)
    fun setTaskName(name: String)

}