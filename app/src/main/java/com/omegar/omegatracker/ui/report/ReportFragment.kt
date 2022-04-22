package com.omegar.omegatracker.ui.report

import android.graphics.LinearGradient
import android.graphics.Shader
import android.os.Bundle
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.content.ContextCompat
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.omegar.libs.omegalaunchers.createFragmentLauncher
import com.omegar.mvp.ktx.providePresenter
import com.omegar.omegatracker.R
import com.omegar.omegatracker.ui.base.BaseFragment

class ReportFragment: BaseFragment(R.layout.fragment_report), ReportView, SelectorView.OnSelectTitleListener {

    companion object {

        const val TAB_INDEX_DAY = 0
        const val TAB_INDEX_WEEK = 1

        fun createLauncher() = createFragmentLauncher()
    }

    override val presenter: ReportPresenter by providePresenter()

    private val selectorView: SelectorView by bind(R.id.selector_view) {
        setOnSelectTitleListener(this@ReportFragment)
    }
    private val chart: LineChart by bind(R.id.chart) {
        val entries = arrayListOf(
            Entry(6.0f, 4.0f),
            Entry(9.0f, 7.0f),
            Entry(13.2f, 8.0f),
            Entry(14.2f, 5.0f),
            Entry(24.0f, 11.0f),
        )


        val dataSet = LineDataSet(entries, "Label"); // add entries to dataset
        dataSet.lineWidth = 10f
        dataSet.circleRadius = 12f
        dataSet.circleHoleRadius = 6f
        setVisibleXRangeMaximum(24f)

        moveViewToX(24f);
        setVisibleYRangeMaximum(24f, YAxis.AxisDependency.RIGHT)

        val gradient = LinearGradient(
            0f, 0f, 2000f, 0f,
            ContextCompat.getColor(requireContext(), R.color.purple),
            ContextCompat.getColor(requireContext(), R.color.purple_light),
            Shader.TileMode.CLAMP)

        val paint = renderer.paintRender
        paint.shader = gradient

        val lineData = LineData(dataSet)
        dataSet.mode = LineDataSet.Mode.CUBIC_BEZIER
        data = lineData
        invalidate()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter.onDayTabClicked()
    }

    override fun onSelectTitle(index: Int) {
        when (index) {
            TAB_INDEX_DAY -> presenter.onDayTabClicked()
            TAB_INDEX_WEEK -> presenter.onWeekTabClicked()
        }
    }

    override fun showDayGraph() {
        selectorView.selectTitle(TAB_INDEX_DAY)
    }

    override fun showWeekGraph() {
        selectorView.selectTitle(TAB_INDEX_WEEK)
    }

}