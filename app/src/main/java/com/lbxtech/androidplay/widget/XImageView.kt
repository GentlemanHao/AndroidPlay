package com.lbxtech.androidplay.widget

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageView
import com.lbxtech.androidplay.R
import kotlin.math.min

class XImageView : AppCompatImageView {

    private var currentStyle = 0
    private var radius = 0f
    private val path = Path()
    private var rectF: RectF = RectF()
    private val paint by lazy {
        Paint().apply {
            isAntiAlias = true
            style = Paint.Style.FILL
            xfermode = PorterDuffXfermode(PorterDuff.Mode.DST_IN)
        }
    }

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        initAttr(context, attrs)
    }

    private fun initAttr(context: Context, attrs: AttributeSet?) {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.XImageView)
        radius = typedArray.getDimension(R.styleable.XImageView_radius, radius)
        currentStyle = typedArray.getInt(R.styleable.XImageView_showType, TYPE_ROUND)
        typedArray.recycle()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        if (currentStyle == TYPE_CIRCLE) {
            val min = min(MeasureSpec.getSize(widthMeasureSpec), MeasureSpec.getSize(heightMeasureSpec))
            setMeasuredDimension(min, min)
        }
        rectF.set(0f, 0f, width.toFloat(), height.toFloat())
    }

    override fun onDraw(canvas: Canvas) {
        path.reset()
        canvas.saveLayer(rectF, null, Canvas.ALL_SAVE_FLAG)
        super.onDraw(canvas)
        when (currentStyle) {
            TYPE_ROUND -> {
                if (radius > 0) {
                    path.addRoundRect(rectF, radius, radius, Path.Direction.CW)
                }
            }
            TYPE_CIRCLE -> {
                val circle = width / 2f
                path.addCircle(circle, circle, circle, Path.Direction.CW)
            }
        }
        canvas.drawPath(path, paint)
        canvas.restore()
    }

    companion object {
        private const val TYPE_ROUND = 1
        private const val TYPE_CIRCLE = 2
    }
}