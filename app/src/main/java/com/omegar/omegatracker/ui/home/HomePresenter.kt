package com.omegar.omegatracker.ui.home

import com.omegar.data.entities.api.ResponseValue
import com.omegar.data.entities.enumcollection.ValueType
import com.omegar.data.entities.model.TaskImpl
import com.omegar.domain.entity.Task
import com.omegar.omegatracker.ui.base.BasePresenter
import com.omegar.omegatracker.utils.timeFormat

class HomePresenter : BasePresenter<HomeView>() {

    private val taskName = mutableListOf<Task>()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        launchWithWaiting {
            val listIssues = apiRepository.getIssues(
                TOKEN,
                "for:me",
                "id,summary,resolved,customFields(name,value(minutes,name,presentation))"
            )
            taskName.clear()
            listIssues.forEach { it ->
                it.summary
                taskName.add(
                    TaskImpl(
                        it.summary,
                        priority = (it.customFields.find {
                            it.valueType.searchName.contains(ValueType.PRIORITY.searchName)
                        } as ResponseValue.ResponsePriority).value?.name,
                        state = (it.customFields.find {
                            it.valueType.searchName.contains(ValueType.STATE.searchName)
                        } as ResponseValue.ResponseState).value?.name,
                        spentTime = (it.customFields.find {
                            it.valueType.searchName.contains(ValueType.SPENT_TIME.searchName)
                        } as ResponseValue.ResponseSpentTime).value?.minutes.timeFormat()
                    )
                )
                viewState.init(taskName)
            }
        }
    }

    fun taskItemClicked(item: Task) {
        viewState.setSingleTaskFields(item)
        viewState.setSingleTaskVisibility(true)
    }

    fun activateTask(isActive: Boolean) {
        viewState.setTaskActive(!isActive)
    }
}