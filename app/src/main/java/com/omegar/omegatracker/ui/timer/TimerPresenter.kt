package com.omegar.omegatracker.ui.timer

import android.os.SystemClock
import android.widget.Chronometer
import com.omegar.omegatracker.ui.base.BasePresenter

class TimerPresenter(
    taskName: String,
    chronometer: Chronometer,
    circularProgress: RoundedCircularProgressView
): BasePresenter<TimerView>() {

    var timeWhenStopped: Long? = 0
    var isRunning: Boolean = false
    var timeProgress: Long? = 0

    init {
        viewState.setTaskName(taskName)
        circularProgress.progress = 0F
    }

    fun onPlay(chronometer: Chronometer , circularProgress: RoundedCircularProgressView) {
        chronometer.base = SystemClock.elapsedRealtime()
        chronometer.start()

        timeProgress = timeProgress?.minus(timeWhenStopped!!)
        circularProgress.indeterminate = true
        circularProgress.progress = timeProgress!!.toFloat()

        isRunning = true
        viewState.setVisible(isRunning)
    }

    fun onPause(chronometer: Chronometer, circularProgress: RoundedCircularProgressView) {
        timeWhenStopped = chronometer.base - SystemClock.elapsedRealtime()
        chronometer.base = SystemClock.elapsedRealtime() + timeWhenStopped!!
        chronometer.stop()
        timeProgress = timeProgress?.minus(timeWhenStopped!!)
        circularProgress.progress = timeProgress!!.toFloat()
        circularProgress.indeterminate = false

        isRunning = false
        viewState.setVisible(isRunning)
    }

    fun onDone(chronometer: Chronometer, circularProgress: RoundedCircularProgressView) {
        chronometer.base = SystemClock.elapsedRealtime()
        timeProgress = 0
        circularProgress.progress = timeProgress!!.toFloat()
        timeWhenStopped = 0
        chronometer.stop()
        circularProgress.indeterminate = false
        circularProgress.progress = timeProgress!!.toFloat()

        isRunning = false
        viewState.setVisible(isRunning)
    }

}