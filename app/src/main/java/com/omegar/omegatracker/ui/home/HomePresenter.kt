package com.omegar.omegatracker.ui.home

import com.omegar.data.entities.api.ResponseValue
import com.omegar.data.entities.enumcollection.ValueType
import com.omegar.data.entities.model.TaskImpl
import com.omegar.domain.entity.Task
import com.omegar.omegatracker.ui.base.BasePresenter
import com.omegar.omegatracker.utils.toTimeFormat

class HomePresenter(private val authToken: String?) : BasePresenter<HomeView>() {

    private val taskName = mutableListOf<Task>()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        launchWithWaiting {
            val listIssues = authToken?.let { token ->
                issueRepository.getIssuesForMe(token)
            }
            taskName.clear()
            listIssues?.forEach { issue ->
                issue.summary
                taskName.add(
                    TaskImpl(
                        issue.summary,
                        priority = (issue.customFields.find {
                            (it as ResponseValue).valueType.searchName.contains(ValueType.PRIORITY.searchName)
                        } as ResponseValue.ResponsePriority?)?.let { it.value?.name },
                        state = (issue.customFields.find {
                            (it as ResponseValue).valueType.searchName.contains(ValueType.STATE.searchName)
                        } as ResponseValue.ResponseState?)?.let { it.value?.name },
                        spentTime = (issue.customFields.find {
                            (it as ResponseValue).valueType.searchName.contains(ValueType.SPENT_TIME.searchName)
                        } as ResponseValue.ResponseSpentTime?)?.let { it.value?.minutes.toTimeFormat() }
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