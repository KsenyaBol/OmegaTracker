package com.omegar.omegatracker.ui.home

import com.omegar.data.entities.model.Task
import com.omegar.domain.entity.TaskInterface
import com.omegar.omegatracker.ui.base.BasePresenter
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

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

    fun timeFormat(time: Long?): String {
        return time?.let {
            SimpleDateFormat("HH:mm:ss", Locale.ROOT).format(Date(TimeUnit.MINUTES.toMillis(it)))
        } ?: "00:00:00"
    }

    fun taskItemClicked(item: TaskInterface) {
        viewState.setSingleTaskFields(item)
        viewState.setSingleTaskVisibility(true)
    }
}