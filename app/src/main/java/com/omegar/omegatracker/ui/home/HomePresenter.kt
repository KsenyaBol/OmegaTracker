package com.omegar.omegatracker.ui.home

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
                        issue.getPriority()?.value?.name,
                        issue.getState()?.value?.name,
                        (issue.getSpentTime()?.value?.minutes).toTimeFormat()
                    )
                )
            }
            viewState.init(taskName)
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