package com.omegar.omegatracker.ui.report

import android.util.Log
import com.omegar.omegatracker.ui.base.BasePresenter

class ReportPresenter: BasePresenter<ReportView>() {
    fun onDayTabClicked() {
        Log.d("ReportPresenter", "day")
        viewState.showDayGraph()
    }

    fun onWeekTabClicked() {
        Log.d("ReportPresenter", "week")
        viewState.showWeekGraph()
    }

}