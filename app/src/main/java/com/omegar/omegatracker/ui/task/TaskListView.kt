package com.omegar.omegatracker.ui.task

import com.omegar.domain.entity.Task
import com.omegar.mvp.viewstate.strategy.StateStrategyType
import com.omegar.mvp.viewstate.strategy.StrategyType
import com.omegar.omegatracker.ui.base.BaseView

interface TaskListView : BaseView {

    @StateStrategyType(StrategyType.ADD_TO_END_SINGLE)
    fun setTasks(list: List<Task>)

    @StateStrategyType(StrategyType.ADD_TO_END_SINGLE)
    fun setSingleTaskVisibility(isVisible: Boolean)

    @StateStrategyType(StrategyType.ADD_TO_END_SINGLE)
    fun setSingleTaskFields(task: Task)

    @StateStrategyType(StrategyType.ADD_TO_END_SINGLE)
    fun setTaskActive(isActive: Boolean)
}