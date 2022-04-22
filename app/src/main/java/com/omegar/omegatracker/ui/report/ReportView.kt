package com.omegar.omegatracker.ui.report

import com.omegar.mvp.viewstate.strategy.StateStrategyType
import com.omegar.mvp.viewstate.strategy.StrategyType
import com.omegar.omegatracker.ui.base.BaseView

interface ReportView: BaseView {
    @StateStrategyType(StrategyType.ADD_TO_END_SINGLE)
    fun showDayGraph()

    @StateStrategyType(StrategyType.ADD_TO_END_SINGLE)
    fun showWeekGraph()
}