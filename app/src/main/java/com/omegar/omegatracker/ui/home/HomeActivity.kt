package com.omegar.omegatracker.ui.home

import android.view.View
import android.widget.FrameLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import com.omega_r.bind.adapters.OmegaAutoAdapter
import com.omega_r.bind.model.binders.bindCustom
import com.omega_r.bind.model.binders.bindString
import com.omega_r.libs.omegarecyclerview.OmegaRecyclerView
import com.omegar.data.entities.model.Priority
import com.omegar.data.entities.model.State
import com.omegar.data.entities.model.Task
import com.omegar.domain.entity.TaskInterface
import com.omegar.libs.omegalaunchers.createActivityLauncher
import com.omegar.mvp.ktx.providePresenter
import com.omegar.omegatracker.R
import com.omegar.omegatracker.ui.base.BaseActivity

class HomeActivity : BaseActivity(R.layout.activity_home), HomeView {

    companion object {
        fun newInstance() = createActivityLauncher()
    }

    override val presenter: HomePresenter by providePresenter()

    private val singleTaskCard: FrameLayout by bind(R.id.layout_activity_home_item_single_task)
    private val singleTaskName: TextView by bind(R.id.tv_item_single_task_name)
    private val singleTaskTime: TextView by bind(R.id.tv_item_single_task_time)

    override fun init(list: List<TaskInterface>) {
        val adapter = OmegaAutoAdapter.create<TaskInterface>(R.layout.item_task, { item ->
            presenter.taskItemClicked(item)
        }) {
            bindCustom(R.id.cv_item_task_priority) { cv: CardView, item: TaskInterface ->
                when (item.priority) {
                    null -> cv.visibility = View.GONE
                }
            }
            bindString(R.id.tv_item_task_name, Task::name)
            bindString(R.id.tv_item_task_time, Task::spentTime)
            bindCustom(R.id.tv_item_task_priority) { tv: TextView, item: TaskInterface ->
                setPriorityViewParameters(tv, item)
            }
            bindCustom(R.id.tv_item_task_state) { tv: TextView, item: TaskInterface ->
                setStateViewParameters(tv, item)
            }
        }
        val taskList: OmegaRecyclerView by bind(R.id.rv_activity_home_task_list, adapter)
        adapter.list = list
        taskList.adapter = adapter
    }

    private fun setStateViewParameters(
        tv: TextView,
        item: TaskInterface
    ) {
        tv.text = item.state
        when (item.state) {
            State.IN_PROGRESS.searchName -> {
                tv.setTextColor(getColor(State.IN_PROGRESS.textColor))
                tv.setBackgroundColor(getColor(State.IN_PROGRESS.backgroundColor))
            }
            State.REOPENED.searchName -> {
                tv.setTextColor(getColor(State.REOPENED.textColor))
                tv.setBackgroundColor(getColor(State.REOPENED.backgroundColor))
            }
            State.IN_REVIEW.searchName -> {
                tv.setTextColor(getColor(State.IN_REVIEW.textColor))
                tv.setBackgroundColor(getColor(State.IN_REVIEW.backgroundColor))
            }
            State.IN_TESTING.searchName -> {
                tv.setTextColor(getColor(State.IN_TESTING.textColor))
                tv.setBackgroundColor(getColor(State.IN_TESTING.backgroundColor))
            }
            State.DONE.searchName -> {
                tv.setTextColor(getColor(State.DONE.textColor))
                tv.setBackgroundColor(getColor(State.DONE.backgroundColor))
            }
            State.FINISHED.searchName -> {
                tv.setTextColor(getColor(State.FINISHED.textColor))
                tv.setBackgroundColor(getColor(State.FINISHED.backgroundColor))
            }
            State.NOT_ClEARED.searchName -> {
                tv.setTextColor(getColor(State.NOT_ClEARED.textColor))
                tv.setBackgroundColor(getColor(State.NOT_ClEARED.backgroundColor))
            }
        }
    }

    private fun setPriorityViewParameters(
        tv: TextView,
        item: TaskInterface
    ) {
        tv.text = item.priority
        when (item.priority?.uppercase()) {
            Priority.BLOCKER.name -> {
                tv.setTextColor(getColor(Priority.BLOCKER.textColor))
                tv.setBackgroundColor(getColor(Priority.BLOCKER.backgroundColor))
            }
            Priority.CRITICAL.name -> {
                tv.setTextColor(getColor(Priority.CRITICAL.textColor))
                tv.setBackgroundColor(getColor(Priority.CRITICAL.backgroundColor))
            }
            Priority.MAJOR.name -> {
                tv.setTextColor(getColor(Priority.MAJOR.textColor))
                tv.setBackgroundColor(getColor(Priority.MAJOR.backgroundColor))
            }
            Priority.MINOR.name -> {
                tv.setTextColor(getColor(Priority.MINOR.textColor))
                tv.setBackgroundColor(getColor(Priority.MINOR.backgroundColor))
            }
            Priority.TRIVIAL.name -> {
                tv.setTextColor(getColor(Priority.TRIVIAL.textColor))
                tv.setBackgroundColor(getColor(Priority.TRIVIAL.backgroundColor))
            }
        }
    }

    override fun setSingleTaskVisibility(isVisible: Boolean) {
        with(singleTaskCard) {
            if (isVisible) visibility = View.VISIBLE
            else visibility = View.GONE
        }
    }

    override fun setSingleTaskFields(task: TaskInterface) {
        singleTaskName.text = task.name
        singleTaskTime.text = task.spentTime
    }
}