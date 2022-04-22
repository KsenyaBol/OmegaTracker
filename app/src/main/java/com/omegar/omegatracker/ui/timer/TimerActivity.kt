package com.omegar.omegatracker.ui.timer

import android.content.Context
import android.content.res.Configuration.UI_MODE_NIGHT_MASK
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.graphics.Color
import android.os.Build
import android.view.View
import android.widget.Chronometer
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.app.AppCompatDelegate.*
import androidx.core.view.isVisible
import com.omegar.libs.omegalaunchers.createActivityLauncher
import com.omegar.libs.omegalaunchers.tools.put
import com.omegar.mvp.ktx.providePresenter
import com.omegar.omegatracker.R
import com.omegar.omegatracker.ui.base.BaseActivity

@RequiresApi(Build.VERSION_CODES.R)
class TimerActivity : BaseActivity(R.layout.activity_timer), TimerView {

    companion object {

        private const val EXTRA_TASK_NAME = "task_name"

        fun createLauncher(taskName: String) = createActivityLauncher(
            EXTRA_TASK_NAME put taskName
        )
    }

    override val presenter: TimerPresenter by providePresenter {
        TimerPresenter(this[EXTRA_TASK_NAME]!!, chronometer, circularProgress)
    }

    private var mContext: Context? = null

    var isRunning: Boolean = false

    private val chronometer: Chronometer by bind(R.id.text_timer) {
        if (getDefaultNightMode() == MODE_NIGHT_UNSPECIFIED)
            setTextColor(Color.WHITE)
    }
    private val circularProgress: RoundedCircularProgressView by bind(R.id.circular_progres)

    private val nameTask: TextView by bind(R.id.text_name)

    private val buttonPause: View by bind(R.id.button_pause) {

        setOnClickListener {
            presenter.onPause(chronometer, circularProgress)
            visibleButton(isRunning)
        }
    }
    private val textPlay: TextView by bind(R.id.text_play)
    private val textPause: View by bind(R.id.text_pause)

    private val buttonPlay: View by bind(R.id.button_play) {
        setOnClickListener {

            if (!isRunning) {
                presenter.onPlay(chronometer, circularProgress)
                visibleButton(isRunning)
            } else {
                presenter.onPause(chronometer, circularProgress)
                visibleButton(isRunning)
            }
        }
    }
    private val buttonDone: View by bind(R.id.button_done) {
        setClickListener {
            presenter.onDone(chronometer, circularProgress)
            visibleButton(isRunning)

        }
    }

    private fun visibleButton(isRunning: Boolean) {
        if (!isRunning) {
            buttonPlay.isVisible = true
            textPlay.isVisible = true
            buttonPause.isVisible = false
            textPause.isVisible = false
        } else {
            buttonPlay.isVisible = false
            textPlay.isVisible = false
            buttonPause.isVisible = true
            textPause.isVisible = true

        }
    }

    override fun setVisible(isRun: Boolean) {
        isRunning = isRun
    }

    override fun setTaskName(name: String) {
        nameTask.text = name
    }

}
