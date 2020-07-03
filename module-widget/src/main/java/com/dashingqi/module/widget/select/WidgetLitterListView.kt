package com.dashingqi.module.widget.select

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import com.dashingqi.base.utils.DensityUtils

/**
 * @author : zhangqi
 * @time : 2020/7/3
 * desc :
 */

class WidgetLitterListView : View {

    lateinit var mPaintDefault: Paint
    lateinit var mPaintSelect: Paint
    var mOnTouchListener: OnTouchListener? = null
    var viewHeight: Int = 0
    var currentLetter = "A"

    /**
     * 26个英文字母
     */
    var letters = arrayListOf("A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z")

    constructor(context: Context) : this(context, null!!) {

    }

    constructor(context: Context, attributes: AttributeSet) : this(context, attributes, 0) {

    }

    constructor(context: Context, attributes: AttributeSet, defStyleAttr: Int) : super(context, attributes, defStyleAttr) {
        init()
    }

    /**
     * 初始化工作，用来初始化画文字的画笔
     */
    private fun init() {

        mPaintDefault = Paint()
        //设置为抗锯齿
        mPaintDefault.isAntiAlias = true
        mPaintDefault.color = Color.BLACK
        mPaintDefault.textSize = DensityUtils.dip2px(context, 10F)

        mPaintSelect = Paint()
        mPaintSelect.isAntiAlias = true
        mPaintSelect.color = Color.RED
        mPaintSelect.textSize = DensityUtils.dip2px(context, 10F)
    }

    /**
     * 画文字
     */
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        //每个字母的高度
        var letterHeight = height.div(letters.size)

        for (index in 0 until letters.size) {
            //每个字母的宽度
            var measureWidth = mPaintDefault.measureText(letters[index])
            var dy = letterHeight * index + letterHeight - measureWidth / 2
            var dx = width / 2 - measureWidth / 2

            canvas?.drawText(letters[index], dx, dy, mPaintDefault)
            if (currentLetter == letters[index]) {
                canvas?.drawText(letters[index], dx, dy, mPaintSelect)
            }
        }
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        viewHeight = h

    }

    /**
     * 触摸事件
     */
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        when (event?.action) {
            MotionEvent.ACTION_MOVE,
            MotionEvent.ACTION_DOWN
            -> {

                currentLetter = (letters[(event.y / viewHeight * letters.size).toInt()])
                /**
                 * 针对 点击和滑动的可以认定为事件开端
                 * 想要在触发事件的时候拿到字母，需要计算角标·
                 */
                if (mOnTouchListener != null) {
                    mOnTouchListener?.onLetterTouch(currentLetter, true)
                }
                //重新调用下draw
                invalidate()
            }

            MotionEvent.ACTION_UP,
            MotionEvent.ACTION_CANCEL -> {

                if (mOnTouchListener != null) {
                    mOnTouchListener?.onLetterTouch(currentLetter, false)
                }
                //重新调用下draw
                invalidate()
            }
        }
        return true
    }

    /**
     * 文字动作的回调事件
     */
    interface OnTouchListener {
        fun onLetterTouch(letterStr: String, isTouch: Boolean)
    }

    /**
     * 设置回调事件
     */
    fun setOnTouchListener(onTouchListener: OnTouchListener) {
        mOnTouchListener = onTouchListener
    }
}