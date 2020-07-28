package com.dashingqi.module.widget.customview.circleprogress

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.util.AttributeSet
import android.view.View
import com.dashingqi.base.utils.DensityUtils
import com.orhanobut.logger.Logger

/**
 * @author : zhangqi
 * @time : 2020/7/28
 * desc :
 */
class DQCircleProgress : View {

    /**
     * 画圆的画笔
     */
    lateinit var mCirclePaint: Paint

    lateinit var mTextPaint1: Paint

    lateinit var mTextPaint2: Paint

    private var mFlowValue: Int = 0

    constructor(context: Context) : super(context)
    constructor(context: Context, attributeSet: AttributeSet) : super(context, attributeSet)

    init {
        initPaint()
    }

    private fun initPaint() {
        mCirclePaint = Paint()
        mCirclePaint.color = Color.WHITE
        mCirclePaint.isAntiAlias = true
        mCirclePaint.style = Paint.Style.STROKE
        mCirclePaint.strokeWidth = 15f

        mTextPaint1 = Paint()
        mTextPaint1.isAntiAlias = true
        mTextPaint1.textSize = DensityUtils.dip2px(context, 14f)
        mTextPaint1.color = Color.YELLOW
        mTextPaint1.style = Paint.Style.FILL

        mTextPaint2 = Paint()
        mTextPaint2.isAntiAlias = true
        mTextPaint2.textSize = DensityUtils.dip2px(context, 12f)
        mTextPaint2.color = Color.BLACK
        mTextPaint2.style = Paint.Style.FILL


    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        drawCircle(canvas)
        drawText1(canvas)
        drawText2(canvas)
    }

    /**
     * 画圆
     */
    private fun drawCircle(canvas: Canvas?) {
        canvas?.drawCircle(measuredWidth / 2f, measuredHeight / 2f, 200f, mCirclePaint)
    }

    private fun drawText1(canvas: Canvas?) {
        var rect = Rect()
        var text = "$mFlowValue M"
        mTextPaint1.getTextBounds(text, 0, text.length, rect)
        canvas?.drawText(text, (measuredWidth - rect.width()) / 2f, measuredHeight / 2f - rect.height() * 2, mTextPaint1)
    }

    /**
     * 画文字（当前流量剩余）
     */
    private fun drawText2(canvas: Canvas?) {
        var react = Rect()
        var text = "流量剩余"
        //测量出当前的文字的大小，以Rect来存储
        mTextPaint2.getTextBounds(text, 0, text.length, react)
        canvas?.drawText(text, (measuredWidth - react.width()) / 2f, measuredHeight / 2f + react.height(), mTextPaint2)
    }

    fun setFlowValue(flowValue: Int) {
        mFlowValue = flowValue
        invalidate()
    }
}