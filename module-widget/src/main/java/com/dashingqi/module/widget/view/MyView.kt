package com.dashingqi.module.widget.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.text.TextPaint
import android.util.AttributeSet
import android.view.View

/**
 * @author zhangqi61
 * @since 7/28/21
 */
class MyView : View {

    /**
     * 画文字
     */
    private val mTextPaint by lazy {
        TextPaint()
    }

    /**
     * 画线
     */
    private val mLinePaint by lazy {
        Paint()
    }

    private val mBaseLineX = 0f

    private val mBaseLineY = 200f

    private val text = "harvic's blog"

    constructor(context: Context) : super(context) {
        init()

    }

    constructor(context: Context, attributeSet: AttributeSet) : super(context, attributeSet) {
        init()

    }

    constructor(context: Context, attributeSet: AttributeSet, defaultValue: Int) :
            super(context, attributeSet, defaultValue) {
        init()

    }

    /**
     * 初始化
     */
    private fun init() {
        mTextPaint.isAntiAlias = true
        // 以px为单位
        mTextPaint.textSize = 120f
        mLinePaint.isAntiAlias = true
        mLinePaint.color = Color.RED
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.let {
            it.drawLine(mBaseLineX, mBaseLineY, 3000f, mBaseLineY, mLinePaint)
            it.drawText(text, mBaseLineX, mBaseLineY, mTextPaint)
        }
    }
}