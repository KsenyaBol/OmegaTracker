package com.omegar.omegatracker.ui.home

import android.view.View
import android.widget.FrameLayout
import android.widget.TextView
import com.omega_r.bind.adapters.OmegaAutoAdapter
import com.omega_r.bind.model.binders.bindString
import com.omega_r.libs.omegarecyclerview.OmegaRecyclerView
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
            bindString(R.id.tv_item_task_name, Task::name)
            bindString(R.id.tv_item_task_time, Task::spentTime)
            bindString(R.id.tv_item_task_priority, Task::priority)
            bindString(R.id.tv_item_task_state, Task::state)
        }
        val taskList: OmegaRecyclerView by bind(R.id.rv_activity_home_task_list, adapter)
        adapter.list = list
        taskList.adapter = adapter
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