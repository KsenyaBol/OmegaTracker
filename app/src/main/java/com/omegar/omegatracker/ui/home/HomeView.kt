package com.omegar.omegatracker.ui.home

import com.omegar.domain.entity.TaskInterface
import com.omegar.mvp.viewstate.strategy.StateStrategyType
import com.omegar.mvp.viewstate.strategy.StrategyType
import com.omegar.omegatracker.ui.base.BaseView

interface HomeView : BaseView {
    @StateStrategyType(StrategyType.ADD_TO_END)
    fun init(list: List<TaskInterface>)

    @StateStrategyType(StrategyType.ADD_TO_END)
    fun setSingleTaskVisibility(isVisible: Boolean)

    @StateStrategyType(StrategyType.ADD_TO_END)
    fun setSingleTaskFields(task: TaskInterface)
}