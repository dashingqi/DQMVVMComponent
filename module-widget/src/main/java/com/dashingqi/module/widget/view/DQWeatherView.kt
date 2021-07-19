package com.dashingqi.module.widget.view

import android.content.Context
import android.graphics.Canvas
import android.text.TextPaint
import android.util.AttributeSet
import android.view.ViewGroup
import com.dashingqi.module.widget.R
import com.dashingqi.module.widget.util.TextPaintUtil

/**
 * @author : zhangqi
 * @time : 7/19/21
 * desc : 自定义天气View
 * 1. 画温度
 * 2. 画城市
 */
class DQWeatherView : ViewGroup {

    /**
     * 温度的画笔
     */
    private val mTemperaturePaint by lazy {
        TextPaint()
    }

    /**
     * 城市的画笔
     */
    private val mCityPaint by lazy {
        TextPaint()
    }

    /**  天气温度*/
    private var mWeatherTemp = "30"

    /** 所在的城市*/
    private var mCity = "大连"

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
        initPaint()
    }

    /**
     * 初始化画笔
     * 1。温度的画笔
     * 2。城市的画笔
     */
    private fun initPaint() {
        //抗锯齿
        mTemperaturePaint.isAntiAlias = true
        mTemperaturePaint.textSize =
                resources.getDimension(R.dimen.widget_temperature_size)
        mTemperaturePaint.color = resources.getColor(R.color.widget_temperature_color)

        mCityPaint.isAntiAlias = true
        mCityPaint.textSize =
                resources.getDimension(R.dimen.widget_city_size)
        mCityPaint.color = resources.getColor(R.color.widget_temperature_color)
    }

    /**
     * 测量
     */
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val measureWidthMode = MeasureSpec.getMode(widthMeasureSpec)
        val measureWidthSize = MeasureSpec.getSize(widthMeasureSpec)

        val measureHeightMode = MeasureSpec.getMode(heightMeasureSpec)
        val measureHeightSize = MeasureSpec.getSize(heightMeasureSpec)


        val height = when (measureHeightMode) {
            MeasureSpec.AT_MOST -> {
                (TextPaintUtil.getTextHeight(mTemperaturePaint, false).toInt()
                        + paddingTop + paddingBottom).coerceAtMost(measureHeightSize)
            }

            MeasureSpec.EXACTLY -> {
                measureHeightSize
            }
            else -> {
                TextPaintUtil.getTextHeight(mTemperaturePaint, false)
                +paddingTop + paddingBottom
            }
        }

        val width = when (measureWidthMode) {
            MeasureSpec.AT_MOST -> {
                (getWeatherMaxWidth() + paddingLeft + paddingRight).coerceAtMost(measureWidthSize)
            }
            MeasureSpec.EXACTLY -> {
                measureWidthSize
            }
            else -> {
                getWeatherMaxWidth() + paddingLeft + paddingRight
            }
        }
        measureChildren(widthMeasureSpec, heightMeasureSpec)
        setMeasuredDimension(width, height)
    }


    /**
     * 获取到天气显示的最大宽度
     */
    private fun getWeatherMaxWidth(): Int {
        val maxWidth =
                TextPaintUtil.getTextWidth(mWeatherTemp, mTemperaturePaint) +
                        TextPaintUtil.getTextWidth(mCity, mCityPaint)
        return maxWidth.toInt()
    }

    /**
     * 布局
     */
    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
    }

    /**
     * 绘制
     */
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

    }

}