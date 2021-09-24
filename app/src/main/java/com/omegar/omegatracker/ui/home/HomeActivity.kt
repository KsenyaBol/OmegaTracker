package com.omegar.omegatracker.ui.home

import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import com.omega_r.bind.adapters.OmegaAutoAdapter
import com.omega_r.bind.model.binders.bindCustom
import com.omega_r.bind.model.binders.bindNonNullVisible
import com.omega_r.bind.model.binders.bindString
import com.omega_r.libs.omegarecyclerview.OmegaRecyclerView
import com.omegar.data.entities.enumcollection.PriorityEnum
import com.omegar.data.entities.enumcollection.StateEnum
import com.omegar.data.entities.model.TaskImpl
import com.omegar.domain.entity.Task
import com.omegar.domain.entity.api.SpentTime
import com.omegar.libs.omegalaunchers.createActivityLauncher
import com.omegar.libs.omegalaunchers.tools.BundlePair
import com.omegar.mvp.ktx.providePresenter
import com.omegar.omegatracker.R
import com.omegar.omegatracker.ui.base.BaseActivity
import com.omegar.omegatracker.utils.toTimeFormat

class HomeActivity : BaseActivity(R.layout.activity_home), HomeView {

    companion object {
        private const val AUTHORIZATION_TOKEN = "token"
        fun newInstance(authToken: BundlePair) = createActivityLauncher(authToken)
    }

    override val presenter: HomePresenter by providePresenter {
        HomePresenter(intent.extras?.getString(AUTHORIZATION_TOKEN))
    }

    private val singleTaskCard: FrameLayout by bind(R.id.layout_home_item_single_task)
    private val singleTaskName: TextView by bind(R.id.text_item_single_task_name)
    private val singleTaskTime: TextView by bind(R.id.text_item_single_task_time)
    private val singleTaskStartBtn: ImageView by bind(R.id.image_item_single_task_arrow)
    private val singleTaskProgress: ProgressBar by bind(R.id.progress_item_single_task_progress)
    private val adapter = OmegaAutoAdapter.create<Task>(R.layout.item_task, { item ->
        presenter.onTaskItemClicked(item)
        singleTaskProgress.visibility = View.INVISIBLE
    }) {
        bindNonNullVisible(R.id.card_view_item_task_state, property = Task::state)
        bindNonNullVisible(R.id.card_view_item_task_priority, property = Task::priority)
        bindString(R.id.text_item_task_name, TaskImpl::name)
        bindString(
            R.id.text_item_task_time,
            TaskImpl::spentTime,
            formatter = { (it as SpentTime).value?.minutes.toTimeFormat() })
        bindCustom(R.id.text_item_task_priority) { tv: TextView, item: Task ->
            setPriorityViewParameters(tv, item)
        }
        bindCustom(R.id.text_item_task_state) { tv: TextView, item: Task ->
            setStateViewParameters(tv, item)
        }
    }
    private val taskList: OmegaRecyclerView by bind(R.id.recyclerview_home_tasks, adapter)

    override fun setTasks(list: List<Task>) {
        initRecyclerView(list)
        initListeners()
    }

    private fun initListeners() {
        setOnClickListener(R.id.image_item_single_task_arrow) {
            presenter.onTaskActiveRequest(singleTaskProgress.isVisible)
        }
    }

    private fun initRecyclerView(list: List<Task>) {
        adapter.list = list
        taskList.adapter = adapter
    }

    private fun setStateViewParameters(
        stateTextView: TextView,
        item: Task
    ) {
        stateTextView.text = item.state?.value?.name
        when (item.state?.value?.name) {
            StateEnum.IN_PROGRESS.searchName -> {
                setTagSettings(
                    stateTextView,
                    StateEnum.IN_PROGRESS.textColor,
                    StateEnum.IN_PROGRESS.backgroundColor
                )
            }
            StateEnum.REOPENED.searchName -> {
                setTagSettings(
                    stateTextView,
                    StateEnum.REOPENED.textColor,
                    StateEnum.REOPENED.backgroundColor
                )
            }
            StateEnum.IN_REVIEW.searchName -> {
                setTagSettings(
                    stateTextView,
                    StateEnum.IN_REVIEW.textColor,
                    StateEnum.IN_REVIEW.backgroundColor
                )
            }
            StateEnum.IN_TESTING.searchName -> {
                setTagSettings(
                    stateTextView,
                    StateEnum.IN_TESTING.textColor,
                    StateEnum.IN_TESTING.backgroundColor
                )
            }
            StateEnum.DONE.searchName -> {
                setTagSettings(
                    stateTextView,
                    StateEnum.DONE.textColor,
                    StateEnum.DONE.backgroundColor
                )
            }
            StateEnum.FINISHED.searchName -> {
                setTagSettings(
                    stateTextView,
                    StateEnum.FINISHED.textColor,
                    StateEnum.FINISHED.backgroundColor
                )
            }
            StateEnum.NOT_ClEARED.searchName -> {
                setTagSettings(
                    stateTextView,
                    StateEnum.NOT_ClEARED.textColor,
                    StateEnum.NOT_ClEARED.backgroundColor
                )
            }
            StateEnum.BACKLOG.searchName -> {
                setTagSettings(
                    stateTextView,
                    StateEnum.BACKLOG.textColor,
                    StateEnum.BACKLOG.backgroundColor
                )
            }
        }
    }

    private fun setPriorityViewParameters(
        priorityTextView: TextView,
        item: Task
    ) {
        priorityTextView.text = item.priority?.value?.name
        when (item.priority?.value?.name?.uppercase()) {
            PriorityEnum.BLOCKER.name -> {
                setTagSettings(
                    priorityTextView,
                    PriorityEnum.BLOCKER.textColor,
                    PriorityEnum.BLOCKER.backgroundColor
                )
            }
            PriorityEnum.CRITICAL.name -> {
                setTagSettings(
                    priorityTextView,
                    PriorityEnum.CRITICAL.textColor,
                    PriorityEnum.CRITICAL.backgroundColor
                )
            }
            PriorityEnum.MAJOR.name -> {
                setTagSettings(
                    priorityTextView,
                    PriorityEnum.MAJOR.textColor,
                    PriorityEnum.MAJOR.backgroundColor
                )
            }
            PriorityEnum.MINOR.name -> {
                setTagSettings(
                    priorityTextView,
                    PriorityEnum.MINOR.textColor,
                    PriorityEnum.MINOR.backgroundColor
                )
            }
            PriorityEnum.TRIVIAL.name -> {
                setTagSettings(
                    priorityTextView,
                    PriorityEnum.TRIVIAL.textColor,
                    PriorityEnum.TRIVIAL.backgroundColor
                )
            }
        }
    }

    private fun setTagSettings(view: TextView, textColor: Int, backgroundColor: Int) {
        view.setTextColor(getColor(textColor))
        view.setBackgroundColor(getColor(backgroundColor))
    }

    override fun setSingleTaskVisibility(isVisible: Boolean) {
        singleTaskCard.isVisible = isVisible
    }

    override fun setSingleTaskFields(task: Task) {
        singleTaskName.text = task.name
        singleTaskTime.text = (task.spentTime?.value?.minutes).toTimeFormat()
    }

    override fun setTaskActive(isActive: Boolean) {
        singleTaskProgress.isInvisible = !isActive
    }
}