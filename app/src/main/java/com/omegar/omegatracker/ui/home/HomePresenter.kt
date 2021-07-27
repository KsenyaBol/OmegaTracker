package com.omegar.omegatracker.ui.home

import com.omegar.data.entities.model.Task
import com.omegar.domain.entity.TaskInterface
import com.omegar.omegatracker.ui.base.BasePresenter

class HomePresenter : BasePresenter<HomeView>() {

    companion object {
        const val FORMAT = "%d:%02d:00"
        const val MINUTES_PER_HOUR = 60
        const val START_TIME = "00:00:00"
    }

    private val taskName = mutableListOf<TaskInterface>()

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
                taskName.add(
                    Task(
                        it.summary,
                        priority = it.customFields.find {
                            it.name.contains("Priority")
                        }?.value?.get(0)?.name,
                        state = it.customFields.find {
                            it.name.contains("State")
                        }?.value?.get(0)?.name,
                        spentTime = timeFormat(it.customFields.find {
                            it.name.contains("Spent time")
                        }?.value?.get(0)?.minutes)
                    )
                )
                viewState.init(taskName)
            }
        }
    }

    private fun timeFormat(time: Long?): String {
        return time?.let {
            val hours = time / MINUTES_PER_HOUR
            val minutes = time % MINUTES_PER_HOUR
            String.format(FORMAT, hours, minutes)
        } ?: START_TIME
    }

    fun taskItemClicked(item: TaskInterface) {
        viewState.setSingleTaskFields(item)
        viewState.setSingleTaskVisibility(true)
    }
}