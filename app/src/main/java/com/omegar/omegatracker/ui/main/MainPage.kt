package com.omegar.omegatracker.ui.main

import com.omega_r.base.enitity.Identifiable
import com.omega_r.libs.omegatypes.image.Image
import com.omega_r.libs.omegatypes.image.from
import com.omegar.libs.omegalaunchers.FragmentLauncher
import com.omegar.omegatracker.R
import com.omegar.omegatracker.ui.report.ReportFragment
import com.omegar.omegatracker.ui.task.TaskListFragment

enum class MainPage(var icon: Image, val launcher: FragmentLauncher) : Identifiable<Int> {

    TASK_LIST(R.drawable.ic_time, TaskListFragment.createLauncher()),
    NEW_TASK(R.drawable.ic_add, ReportFragment.createLauncher()),
    REPORT(R.drawable.ic_pie_chart, ReportFragment.createLauncher());

    constructor(image: Int, launcher: FragmentLauncher) : this(Image.from(image), launcher)

    override val id: Int
        get() = ordinal

}