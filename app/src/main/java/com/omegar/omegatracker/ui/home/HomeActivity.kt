package com.omegar.omegatracker.ui.home

import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.view.isVisible
import com.omega_r.bind.adapters.OmegaAutoAdapter
import com.omega_r.bind.model.binders.bindCustom
import com.omega_r.bind.model.binders.bindString
import com.omega_r.libs.omegarecyclerview.OmegaRecyclerView
import com.omegar.data.entities.enumcollection.Priority
import com.omegar.data.entities.enumcollection.State
import com.omegar.data.entities.model.TaskImpl
import com.omegar.domain.entity.Task
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
    private val singleTaskName: TextView by bind(R.id.text_item_single_task_name)
    private val singleTaskTime: TextView by bind(R.id.text_item_single_task_time)
    private val singleTaskStartBtn: ImageView by bind(R.id.image_item_single_task_arrow)
    private val singleTaskProgress: ProgressBar by bind(R.id.progress_item_single_task_progress)

    override fun init(list: List<Task>) {
        initRecyclerView(list)
        initListeners()
    }

    private fun initListeners() {
        singleTaskStartBtn.setOnClickListener { presenter.activateTask(singleTaskProgress.isVisible) }
    }

    private fun initRecyclerView(list: List<Task>) {
        val adapter = OmegaAutoAdapter.create<Task>(R.layout.item_task, { item ->
            presenter.taskItemClicked(item)
            singleTaskProgress.visibility = View.INVISIBLE
        }) {
            bindCustom(R.id.card_view_item_task_priority) { cv: CardView, item: Task ->
                when (item.priority) {
                    null -> cv.visibility = View.GONE
                }
            }
            bindString(R.id.text_item_task_name, TaskImpl::name)
            bindString(R.id.text_item_task_time, TaskImpl::spentTime)
            bindCustom(R.id.text_item_task_priority) { tv: TextView, item: Task ->
                setPriorityViewParameters(tv, item)
            }
            bindCustom(R.id.text_item_task_state) { tv: TextView, item: Task ->
                setStateViewParameters(tv, item)
            }
        }
        val taskList: OmegaRecyclerView by bind(R.id.recycler_activity_home_task_list, adapter)
        adapter.list = list
        taskList.adapter = adapter
    }

    private fun setStateViewParameters(
        stateTextView: TextView,
        item: Task
    ) {
        stateTextView.text = item.state
        when (item.state) {
            State.IN_PROGRESS.searchName -> {
                stateTextView.setTextColor(getColor(State.IN_PROGRESS.textColor))
                stateTextView.setBackgroundColor(getColor(State.IN_PROGRESS.backgroundColor))
            }
            State.REOPENED.searchName -> {
                stateTextView.setTextColor(getColor(State.REOPENED.textColor))
                stateTextView.setBackgroundColor(getColor(State.REOPENED.backgroundColor))
            }
            State.IN_REVIEW.searchName -> {
                stateTextView.setTextColor(getColor(State.IN_REVIEW.textColor))
                stateTextView.setBackgroundColor(getColor(State.IN_REVIEW.backgroundColor))
            }
            State.IN_TESTING.searchName -> {
                stateTextView.setTextColor(getColor(State.IN_TESTING.textColor))
                stateTextView.setBackgroundColor(getColor(State.IN_TESTING.backgroundColor))
            }
            State.DONE.searchName -> {
                stateTextView.setTextColor(getColor(State.DONE.textColor))
                stateTextView.setBackgroundColor(getColor(State.DONE.backgroundColor))
            }
            State.FINISHED.searchName -> {
                stateTextView.setTextColor(getColor(State.FINISHED.textColor))
                stateTextView.setBackgroundColor(getColor(State.FINISHED.backgroundColor))
            }
            State.NOT_ClEARED.searchName -> {
                stateTextView.setTextColor(getColor(State.NOT_ClEARED.textColor))
                stateTextView.setBackgroundColor(getColor(State.NOT_ClEARED.backgroundColor))
            }
        }
    }

    private fun setPriorityViewParameters(
        priorityTextView: TextView,
        item: Task
    ) {
        priorityTextView.text = item.priority
        when (item.priority?.uppercase()) {
            Priority.BLOCKER.name -> {
                priorityTextView.setTextColor(getColor(Priority.BLOCKER.textColor))
                priorityTextView.setBackgroundColor(getColor(Priority.BLOCKER.backgroundColor))
            }
            Priority.CRITICAL.name -> {
                priorityTextView.setTextColor(getColor(Priority.CRITICAL.textColor))
                priorityTextView.setBackgroundColor(getColor(Priority.CRITICAL.backgroundColor))
            }
            Priority.MAJOR.name -> {
                priorityTextView.setTextColor(getColor(Priority.MAJOR.textColor))
                priorityTextView.setBackgroundColor(getColor(Priority.MAJOR.backgroundColor))
            }
            Priority.MINOR.name -> {
                priorityTextView.setTextColor(getColor(Priority.MINOR.textColor))
                priorityTextView.setBackgroundColor(getColor(Priority.MINOR.backgroundColor))
            }
            Priority.TRIVIAL.name -> {
                priorityTextView.setTextColor(getColor(Priority.TRIVIAL.textColor))
                priorityTextView.setBackgroundColor(getColor(Priority.TRIVIAL.backgroundColor))
            }
        }
    }

    override fun setSingleTaskVisibility(isVisible: Boolean) {
        with(singleTaskCard) {
            if (isVisible) visibility = View.VISIBLE
            else visibility = View.GONE
        }
    }

    override fun setSingleTaskFields(task: Task) {
        singleTaskName.text = task.name
        singleTaskTime.text = task.spentTime
    }

    override fun setTaskActive(isActive: Boolean) {
        if (isActive) singleTaskProgress.visibility = View.VISIBLE
        else singleTaskProgress.visibility = View.INVISIBLE
    }
}