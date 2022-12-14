package com.omegar.omegatracker.ui.task

import com.omegar.data.entities.model.TaskImpl
import com.omegar.domain.entity.Task
import com.omegar.omegatracker.ui.base.BasePresenter
import com.omegar.omegatracker.ui.login.LoginActivity
import com.omegar.omegatracker.ui.timer.TimerActivity

class TaskListPresenter : BasePresenter<TaskListView>() {
    private val tasks = mutableListOf<Task>()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        launchWithWaiting {
            val listIssues = tokenStorage.token?.let { token ->
                issueRepository.getIssuesForMe(token)
            }
            tasks.clear()
            listIssues?.map { issue ->
                tasks.add(
                    TaskImpl(
                        issue.summary,
                        issue.priority,
                        issue.state,
                        issue.spentTime
                    )
                )
            }
            viewState.setTasks(tasks)
        }
    }

    fun onTaskItemClicked(item: Task) {
        viewState.setSingleTaskFields(item)
        viewState.setSingleTaskVisibility(true)
    }

    fun onTaskActiveRequest(isActive: Boolean) {
        viewState.setTaskActive(!isActive)
    }

    fun onLogoutClicked() {
        tokenStorage.token = null
        LoginActivity.createLauncher().launch()
        viewState.exit()
    }

    fun onSelectItem(taskName: String) {
        TimerActivity.createLauncher(taskName).launch()
    }
}