package com.omegar.omegatracker.ui.report

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import com.omegar.omegatracker.R

class SelectorView : CardView, View.OnClickListener {

    companion object {
        const val NO_INDEX = -1
    }

    private var listener: OnSelectTitleListener? = null
    private var container: ViewGroup? = null
    private var selectedIndex = -1
    private var chekable = false

    constructor(context: Context) : super(context) {
        init(context, null)
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init(context, attrs)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        init(context, attrs)
    }

    private fun init(context: Context, attrs: AttributeSet?) {
        val inflater = LayoutInflater.from(context)
        val view: View = inflater.inflate(R.layout.view_selector, this)
        container = view.findViewById(R.id.layout_container)
        if (attrs != null) {
            val a = context.obtainStyledAttributes(attrs, R.styleable.SelectorView)
            val titles = a.getTextArray(R.styleable.SelectorView_titles)
            chekable = a.getBoolean(R.styleable.SelectorView_checkable, false)
            for ((index, title) in titles.withIndex()) {
                val textView =
                    inflater.inflate(R.layout.item_selector, container, false) as TextView
                textView.text = title
                textView.setOnClickListener(this)
                textView.tag = index
                container?.addView(textView)
                if (chekable) {
                    setCheckIcon(textView)
                }
            }
            a.recycle()
        }
    }

    fun setOnSelectTitleListener(listener: OnSelectTitleListener?) {
        this.listener = listener
    }

    fun selectTitle(index: Int) {
        if (selectedIndex != index) {
            selectedIndex = index
            for (i in 0 until container!!.childCount) {
                val child = container?.getChildAt(i)
                child?.isSelected = index == i
            }
        }
    }

    private fun setCheckIcon(view: TextView) {
        val config = resources.configuration
        if (config.layoutDirection == LAYOUT_DIRECTION_RTL) {
            view.setCompoundDrawablesWithIntrinsicBounds(
                0,
                0,
                R.drawable.ic_selector_check_selectable,
                0
            )
        } else {
            view.setCompoundDrawablesWithIntrinsicBounds(
                R.drawable.ic_selector_check_selectable,
                0,
                0,
                0
            )
        }
    }

    override fun onClick(v: View) {
        if (listener != null) {
            val index = v.tag as Int
            if (selectedIndex != index) {
                listener?.onSelectTitle(index)
            }
        }
    }

    interface OnSelectTitleListener {
        fun onSelectTitle(index: Int)
    }
}