package com.omegar.omegatracker.ui.home

import com.omega_r.libs.omegatypes.toText
import com.omegar.data.entities.model.Task
import com.omegar.domain.entity.TaskInterface
import com.omegar.omegatracker.ui.base.BasePresenter

class HomePresenter : BasePresenter<HomeView>() {

    val taskName = mutableListOf<TaskInterface>()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        launchWithWaiting {
            val listIssues = apiRepository.getIssues(
                TOKEN,
                "for:me",
                "id,summary,resolved,customFields(name,value(minutes,name,presentation))"
            )
            taskName.clear()
            listIssues.forEach {
                it.summary
                taskName.add(Task(it.summary.toText()))
            }
            viewState.init(taskName)
        }
    }

    fun taskItemClicked(item: TaskInterface) {
        viewState.setSingleTaskFields(item)
        viewState.setSingleTaskVisibility(true)
    }
}