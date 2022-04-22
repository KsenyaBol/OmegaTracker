package com.omegar.omegatracker.ui.timer

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import android.view.animation.Animation
import androidx.annotation.ColorInt
import androidx.core.content.withStyledAttributes
import com.omegar.omegatracker.R
import kotlin.math.cos
import kotlin.math.min
import kotlin.math.sin


/**
 * Created by Anton Knyazev on 08.04.2020.
 */
class RoundedCircularProgressView @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    companion object {
        private const val ANGLE_MIN = 0f
        private const val ANGLE_MAX = 360f
        private const val PROGRESS_MIN = 0f
        private const val PROGRESS_MAX = 1f
        private const val ANGLE_ARC_FIX_START = 90f
        private const val ANGLE_ARC_FIX_START_SWEEP = 182f

    }

    var startAngle: Float = -90f
        set(value) {
            field = value
            if (!indeterminate) {
                internalStartAngle = value
            }
        }

    private var internalStartAngle: Float = startAngle
        set(value) {
            field = value
            updateHeadRectF(angle, getSize(width, height), centerPointF, strokeWidth, value)
            updateGradientMatrix(progress, rectF, value)
        }

    @ColorInt
    var startColor: Int = Color.WHITE
        set(value) {
            field = value
            circlePaint.color = startColor
            updateGradient(gradientMatrix, centerPointF, value, endColor)
        }

    @ColorInt
    var endColor: Int = Color.BLACK
        set(value) {
            field = value
            updateGradient(gradientMatrix, centerPointF, startColor, value)
        }

    @ColorInt
    var backColor: Int = Color.LTGRAY
        set(value) {
            field = value
            backPaint.color = value
        }

    var strokeWidth = 100f
        set(value) {
            field = value
            foregroundPaint.strokeWidth = value
            backPaint.strokeWidth = value
            updateRectF(width, height, value)
            updateHeadRectF(angle, getSize(width, height), centerPointF, value, internalStartAngle)
            invalidate()
        }

    private var headRectF = RectF()

    var indeterminate: Boolean = false
        set(value) {
            if (field != value) {
                field = value
                if (field) {
                    indeterminateAnimator.start()
                } else {
                    indeterminateAnimator.end()
                    internalStartAngle = startAngle
                }
            }
        }


    private val indeterminateAnimator = ValueAnimator.ofFloat(ANGLE_MIN, ANGLE_MAX).apply {
        addUpdateListener {
            val value = it.animatedValue as Float
            if (progress < PROGRESS_MAX) {
                val newProgress = value / ANGLE_MAX
                progress = if (newProgress < progress) {
                    internalStartAngle = value + startAngle
                    PROGRESS_MAX
                } else newProgress
            } else {
                internalStartAngle = value + startAngle
            }
            invalidate()
        }
        interpolator = null
        repeatCount = Animation.INFINITE
    }

    private var rectF = RectF()
        set(value) {
            field = value
            updateCenterPointF(value)
            updateGradientMatrix(progress, value, internalStartAngle)
            updateHeadRectF(
                    angle,
                    getSize(width, height),
                    centerPointF,
                    strokeWidth,
                    internalStartAngle
            )
            invalidate()
        }

    private var centerPointF = PointF()
        set(value) {
            field = value
            updateGradient(gradientMatrix, value, startColor, endColor)
            updateHeadRectF(angle, getSize(width, height), value, strokeWidth, internalStartAngle)
        }

    private var gradientMatrix = Matrix()
        set(value) {
            field = value
            foregroundPaint.shader?.setLocalMatrix(value)
        }

    private var foregroundPaint: Paint = Paint().apply {
        isAntiAlias = true
        style = Paint.Style.STROKE
        strokeWidth = this@RoundedCircularProgressView.strokeWidth
        strokeCap = Paint.Cap.ROUND
    }

    private var circlePaint: Paint = Paint().apply {
        isAntiAlias = true
        color = startColor
    }

    private var backPaint: Paint = Paint().apply {
        color = backColor
        style = Paint.Style.STROKE
        strokeWidth = this@RoundedCircularProgressView.strokeWidth
        invalidate()
    }

    var progress = 0.5f
        set(value) {
            field = value
            updateGradientMatrix(value, rectF, internalStartAngle)
            updateAngle(value)
            invalidate()
        }

    private var angle = 0f
        set(value) {
            field = value
            updateHeadRectF(
                    value,
                    getSize(width, height),
                    centerPointF,
                    strokeWidth,
                    internalStartAngle
            )
            invalidate()
        }

    init {
        setWillNotDraw(false)
        context.withStyledAttributes(attrs, R.styleable.RoundedCircularProgressView) {
            startColor = getColor(R.styleable.RoundedCircularProgressView_startColor, startColor)
            endColor = getColor(R.styleable.RoundedCircularProgressView_endColor, endColor)
            backColor = getColor(R.styleable.RoundedCircularProgressView_backColor, backColor)
            strokeWidth = getDimension(R.styleable.RoundedCircularProgressView_strokeWidth, strokeWidth)
            indeterminate = getBoolean(R.styleable.RoundedCircularProgressView_indeterminate, indeterminate)
            indeterminateAnimator.duration = getInteger(
                    R.styleable.RoundedCircularProgressView_indeterminateDuration,
                    indeterminateAnimator.duration.toInt()
            ).toLong()
            progress = getFloat(R.styleable.RoundedCircularProgressView_progress, progress)
        }
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        updateRectF(w, h, strokeWidth)
    }

    private fun updateRectF(w: Int, h: Int, strokeWidth: Float) {
        val rectF = rectF
        val size = getSize(w, h)
        val halfStrokeWidth = strokeWidth / 2f
        rectF.set(
                halfStrokeWidth,
                halfStrokeWidth,
                size - halfStrokeWidth,
                size - halfStrokeWidth
        )
        this.rectF = rectF
    }

    private fun getSize(w: Int, h: Int): Int = min(w, h)

    private fun updateCenterPointF(rectF: RectF) {
        val centerPointF = centerPointF
        centerPointF.set(rectF.centerX(), rectF.centerY())
        this.centerPointF = centerPointF
    }

    private fun updateGradientMatrix(progress: Float, rectF: RectF, startAngle: Float) {
        val rotateAngle = startAngle + ANGLE_MAX * (progress)
        val gradientMatrix = gradientMatrix
        gradientMatrix.reset()

        if (!isInEditMode)
            gradientMatrix.postRotate(
                    rotateAngle,
                    rectF.centerX(),
                    rectF.centerY()
            )
        else
            gradientMatrix.postRotate(rotateAngle)

        this.gradientMatrix = gradientMatrix
    }

    private fun updateGradient(
            gradientMatrix: Matrix,
            centerPointF: PointF,
            startColor: Int,
            endColor: Int
    ) {

        foregroundPaint.shader = SweepGradient(
                centerPointF.x,
                centerPointF.y,
                endColor, startColor
        ).also {
            it.setLocalMatrix(gradientMatrix)
        }

    }

    private fun updateAngle(progress: Float) {
        angle = ANGLE_MAX * progress
    }

    private fun updateHeadRectF(
            angle: Float,
            size: Int,
            centerPointF: PointF,
            strokeWidth: Float,
            startAngle: Float
    ) {
        val headRectF = headRectF
        val halfStrokeWidth = strokeWidth / 2f
        val angleRad =
                Math.toRadians((angle + ANGLE_ARC_FIX_START + startAngle).toDouble()) // Need to convert to radians first
        val radius = (size / 2 - halfStrokeWidth)
        val pointX = (centerPointF.x + radius * sin(angleRad))
        val pointY = (centerPointF.y - radius * cos(angleRad))

        headRectF.set(
                (pointX - halfStrokeWidth).toFloat(),
                (pointY - halfStrokeWidth).toFloat(),
                (pointX + halfStrokeWidth).toFloat(),
                (pointY + halfStrokeWidth).toFloat()
        )

        this.headRectF = headRectF
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        canvas.drawArc(rectF, ANGLE_MIN, ANGLE_MAX, false, backPaint)
        if (progress != PROGRESS_MIN) {
            canvas.drawArc(rectF, internalStartAngle, angle, false, foregroundPaint)
            canvas.drawArc(
                    headRectF,
                    angle + internalStartAngle - 1,
                    ANGLE_ARC_FIX_START_SWEEP,
                    false,
                    circlePaint
            )
        }
    }

    override fun onVisibilityAggregated(isVisible: Boolean) {
        super.onVisibilityAggregated(isVisible)
        if (indeterminate) {
            if (isVisible) {
                indeterminateAnimator.resume()
            } else {
                indeterminateAnimator.pause()
            }
        }
    }

    override fun onWindowVisibilityChanged(visibility: Int) {
        super.onWindowVisibilityChanged(visibility)
        if (indeterminate) {
            if (visibility == VISIBLE) {
                indeterminateAnimator.resume()
            } else {
                indeterminateAnimator.pause()
            }
        }
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        indeterminateAnimator.cancel()
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        if (indeterminate && !indeterminateAnimator.isRunning) {
            indeterminateAnimator.start()
        }
    }
}