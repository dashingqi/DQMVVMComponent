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

    /**
     * 画城市温度的画笔
     */
    private val mTemperatureSymbolPaint by lazy {
        TextPaint()
    }

    /**
     * 画pm2.5的画笔
     */
    private val mPMPaint by lazy {
        TextPaint()
    }

    /**
     * 画天气描述的画笔
     */
    private val mWeatherDescPaint by lazy {
        TextPaint()
    }

    /**  天气温度*/
    private var mWeatherTemp = "30"

    /** 所在的城市*/
    private var mCity = "大连"

    /** 温度符号*/
    val mTemperatureSymbolText = resources.getString(R.string.widget_weather_temperature_symbol)

    /** pm值*/
    private var mPM = "34"

    /** 天气描述*/
    private var mWeatherDesc = "优"

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
        //画温度的画笔
        mTemperaturePaint.isAntiAlias = true
        mTemperaturePaint.textSize =
                resources.getDimension(R.dimen.widget_temperature_size)
        mTemperaturePaint.color = resources.getColor(R.color.widget_temperature_color)

        // 画城市的画笔
        mCityPaint.isAntiAlias = true
        mCityPaint.textSize =
                resources.getDimension(R.dimen.widget_city_size)
        mCityPaint.color = resources.getColor(R.color.widget_temperature_color)

        // 画温度符号的画笔
        mTemperatureSymbolPaint.isAntiAlias = true
        mTemperatureSymbolPaint.textSize =
                resources.getDimension(R.dimen.widget_weather_temperature_symbol)
        mTemperatureSymbolPaint.color = resources.getColor(R.color.widget_temperature_color)

        // 画pm2.5
        mPMPaint.isAntiAlias = true
        mPMPaint.textSize = resources.getDimension(R.dimen.widget_pm_size)
        mPMPaint.color = resources.getColor(R.color.widget_temperature_color)

        // 画天气描述
        mWeatherDescPaint.isAntiAlias = true
        mWeatherDescPaint.textSize = resources.getDimension(R.dimen.widget_weather_desc_size)
        mWeatherDescPaint.color = resources.getColor(R.color.widget_temperature_color)


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
                        TextPaintUtil.getTextWidth(mCity, mCityPaint) +
                        TextPaintUtil.getTextWidth(mTemperatureSymbolText, mTemperatureSymbolPaint)
        return maxWidth.toInt()
    }

    /**
     * 布局
     */
    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
    }

    /**
     * 绘制45
     */
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        var leftPadding = resources.getDimension(R.dimen.widget_weather_default_padding)
        canvas?.let {
            //画温度
            if (!mWeatherTemp.isNullOrEmpty()) {
                it.drawText(mWeatherTemp, 0.0f,
                        TextPaintUtil.getTextBaseLineY(mTemperaturePaint, height,
                                false),
                        mTemperaturePaint)
            }
            // 画温度的符号
            leftPadding += TextPaintUtil.getTextWidth(mWeatherTemp, mTemperaturePaint)


            if (!mTemperatureSymbolText.isNullOrEmpty()) {
                it.drawText(mTemperatureSymbolText, leftPadding, TextPaintUtil.getTextBaseLineY(mTemperatureSymbolPaint, height,
                        false), mTemperatureSymbolPaint)
            }
            // 画城市
            leftPadding += TextPaintUtil.getTextWidth(mTemperatureSymbolText, mTemperatureSymbolPaint)
            if (!mCity.isNullOrEmpty()) {
                it.drawText(mCity, leftPadding, TextPaintUtil.getTextBaseLineY(mCityPaint, height,
                        false), mCityPaint)
            }

            var bottomLeft = TextPaintUtil.getTextWidth(mWeatherTemp, mTemperaturePaint) +
                    TextPaintUtil.getTextWidth(mTemperatureSymbolText, mTemperatureSymbolPaint)
            // 画pm
            if (!mPM.isNullOrEmpty()) {
                it.drawText(mPM, bottomLeft, TextPaintUtil.getTextBaseLineY(mTemperaturePaint, height,
                        false), mPMPaint)
            }

            bottomLeft += TextPaintUtil.getTextWidth(mPM, mPMPaint)

            // 画天气的描述
            if (!mWeatherDesc.isNullOrEmpty()) {
                it.drawText(mWeatherDesc, bottomLeft, TextPaintUtil.getTextBaseLineY(mTemperaturePaint, height,
                        false), mWeatherDescPaint)
            }

        }
    }

}