package com.dashingqi.module.widget.view.canvas

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.text.TextPaint
import android.util.AttributeSet
import android.view.View

/**
 * @author : zhangqi
 * @time : 7/29/21
 * desc :
 */
class DrawTextView : View {

    private val mTopLinePaint by lazy {
        Paint()
    }

    private val mCenterLinePaint by lazy {
        Paint()
    }

    private val mTextPaint by lazy {
        TextPaint()
    }

    private val mBaseLinePaint by lazy {
        Paint()
    }

    private val text = "harvic's blog"


    private val BASE_LINE_X = 0f

    private val TOP = 200f

    private val CENTER = 200f

    private val STOP_X = 3000f

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attributeSet: AttributeSet?) : super(context, attributeSet) {
        init()
    }

    /**
     * 初始化
     */
    private fun init() {
        mTopLinePaint.isAntiAlias = true
        mTopLinePaint.color = Color.YELLOW
        mCenterLinePaint.isAntiAlias = true
        mCenterLinePaint.color = Color.YELLOW
        mTextPaint.isAntiAlias = true
        mTextPaint.color = Color.GREEN
        mTextPaint.textSize = 120f
        mTextPaint.textAlign = Paint.Align.LEFT
        mBaseLinePaint.isAntiAlias = true
        mBaseLinePaint.color = Color.RED
    }

    /**
     * 基线的计算规则
     * var baseLineY = top - FontMetrics.top
     */
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        // (BASE_LINE_X , TOP) 作为Top线的起点
//        canvas?.let {
//            // 画Top线
//            it.drawLine(BASE_LINE_X, TOP, STOP_X, TOP, mTopLinePaint)
//            // 计算基线
//            var fontMetrics = mTextPaint.fontMetrics
//            var baseLineY = TOP - fontMetrics.top
//            // 画文字
//            canvas.drawText(text, BASE_LINE_X, baseLineY, mTextPaint)
//            // 画基线
//            canvas.drawLine(BASE_LINE_X, baseLineY, STOP_X, baseLineY, mBaseLinePaint)
//        }

        // (BASE_LINE_X , CENTER) 作为center线的起点

        /**
         * 画基准线的推算
         *
         * val centerLineY = (bottom-top)/2
         * val top = baseLineY+FontMetrics.top
         * val bottom = baseLineY+FontMetrics.bottom
         * val centerLineY = (FontMetrics.bottom - FontMetrics.top)/2
         * val baseLineY = center+(FontMetrics.bottom - FontMetrics.top )/2 - FontMetrics.bottom
         */

        canvas?.let {
            // 画center线
            it.drawLine(BASE_LINE_X, TOP, STOP_X, TOP, mCenterLinePaint)

            //画基准线
            var fontMetrics = mTextPaint.fontMetrics
            val baseLineY = CENTER + (fontMetrics.bottom - fontMetrics.top) / 2 - fontMetrics.bottom
            it.drawLine(BASE_LINE_X, baseLineY, STOP_X, baseLineY, mBaseLinePaint)

            // 画文字
            it.drawText(text, BASE_LINE_X, baseLineY, mTextPaint)
        }
    }
}