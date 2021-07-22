package com.omegar.omegatracker.ui.home

import android.view.View
import android.widget.TextView
import com.omega_r.bind.adapters.OmegaAutoAdapter
import com.omega_r.bind.model.binders.bindText
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

    private val singleTaskCard: View by bind(R.id.layout_activity_home_item_single_task)
    private val singleTaskName: View by bind(R.id.tv_item_single_task_name)
    lateinit var adapter: OmegaAutoAdapter<TaskInterface, OmegaAutoAdapter.ViewHolder<TaskInterface>>

    override fun init(list: List<TaskInterface>) {
        adapter = OmegaAutoAdapter.create(R.layout.item_task, { item ->
            presenter.taskItemClicked(item)
        }) {
            bindText(R.id.tv_item_task_name, Task::name)
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
        (singleTaskName as TextView).text = task.name.getString(applicationContext)
    }
}