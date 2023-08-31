package com.dashingqi.module.widget.view

import android.content.Context
import android.graphics.Canvas
import android.text.TextPaint
import android.util.AttributeSet
import android.view.ViewGroup
import com.dashingqi.module.widget.R
import com.dashingqi.module.widget.util.TextPaintUtil
import com.dashingqi.module.widget.view.data.WeatherData

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

    /**
     * 画天气质量的的画笔
     */
    private val mWeatherQualityPaint by lazy {
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

    /** 天气质量*/
    private var mWeatherQuality = "优"

    /** 天气描述*/
    private var mWeatherDesc = "多云"

    /** 是否包含文字的空白区域*/
    private var mIsIncludePadding = false

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

        // 画温度符号的画笔
        mTemperatureSymbolPaint.isAntiAlias = true
        mTemperatureSymbolPaint.textSize =
                resources.getDimension(R.dimen.widget_weather_temperature_symbol)
        mTemperatureSymbolPaint.color = resources.getColor(R.color.widget_temperature_color)
        // 画城市的画笔
        mCityPaint.isAntiAlias = true
        mCityPaint.textSize =
                resources.getDimension(R.dimen.widget_city_size)
        mCityPaint.color = resources.getColor(R.color.widget_temperature_color)

        // 画天气描述
        mWeatherDescPaint.isAntiAlias = true
        mWeatherDescPaint.textSize = resources.getDimension(R.dimen.widget_weather_desc_size)
        mWeatherDescPaint.color = resources.getColor(R.color.widget_temperature_color)

        // 画pm2.5
        mPMPaint.isAntiAlias = true
        mPMPaint.textSize = resources.getDimension(R.dimen.widget_pm_size)
        mPMPaint.color = resources.getColor(R.color.widget_temperature_color)

        // 画天气质量
        mWeatherQualityPaint.isAntiAlias = true
        mWeatherQualityPaint.textSize = resources.getDimension(R.dimen.widget_weather_quality_size)
        mWeatherQualityPaint.color = resources.getColor(R.color.widget_temperature_color)


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
                (TextPaintUtil.getTextHeight(mTemperaturePaint, mIsIncludePadding).toInt()
                        + paddingTop + paddingBottom).coerceAtMost(measureHeightSize)
            }

            MeasureSpec.EXACTLY -> {
                measureHeightSize
            }
            else -> {
                TextPaintUtil.getTextHeight(mTemperaturePaint, mIsIncludePadding)
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
        var maxWidth =
                TextPaintUtil.getTextWidth(mWeatherTemp, mTemperaturePaint) +
                        TextPaintUtil.getTextWidth(mTemperatureSymbolText, mTemperatureSymbolPaint) +
                        resources.getDimension(R.dimen.widget_weather_right_padding)

        maxWidth += Math.max((TextPaintUtil.getTextWidth(mCity, mCityPaint) +
                TextPaintUtil.getTextWidth(mWeatherDesc, mWeatherDescPaint) +
                resources.getDimension(R.dimen.widget_weather_symbol_margin_right)),
                (TextPaintUtil.getTextWidth(mPM, mPMPaint) +
                        TextPaintUtil.getTextWidth(mWeatherQuality, mWeatherQualityPaint) +
                        resources.getDimension(R.dimen.widget_weather_symbol_margin_right)
                        )
        )
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
                                mIsIncludePadding),
                        mTemperaturePaint)
            }
            // 画温度的符号
            leftPadding += TextPaintUtil.getTextWidth(mWeatherTemp, mTemperaturePaint)

            var symbolBaselineY = (height - TextPaintUtil.getTextHeight(mTemperaturePaint, mIsIncludePadding)) / 2 +
                    resources.getDimension(R.dimen.widget_weather_symbol_margin_top)

            if (mIsIncludePadding) {
                symbolBaselineY -= mTemperatureSymbolPaint.fontMetrics.top
            } else {
                symbolBaselineY -= mTemperatureSymbolPaint.fontMetrics.ascent
            }

            if (!mTemperatureSymbolText.isNullOrEmpty()) {
                it.drawText(mTemperatureSymbolText, leftPadding, symbolBaselineY, mTemperatureSymbolPaint)
            }
            // 画城市
            leftPadding += TextPaintUtil.getTextWidth(mTemperatureSymbolText, mTemperatureSymbolPaint) +
                    resources.getDimension(R.dimen.widget_weather_symbol_margin_right)
            var lintBaseLineYCity = TextPaintUtil.getNewLineBaseLineY(mCityPaint, mPMPaint, height, mIsIncludePadding)
            +resources.getDimension(R.dimen.widget_weather_city_margin_top)
            if (!mCity.isNullOrEmpty()) {
                it.drawText(mCity, leftPadding, lintBaseLineYCity, mCityPaint)
            }

            // 画天气的描述
            leftPadding += TextPaintUtil.getTextWidth(mWeatherDesc, mWeatherDescPaint) +
                    resources.getDimension(R.dimen.widget_weather_desc_margin_left)
            if (!mWeatherDesc.isNullOrEmpty()) {
                it.drawText(mWeatherDesc, leftPadding, lintBaseLineYCity, mWeatherDescPaint)
            }

            var bottomLeft = TextPaintUtil.getTextWidth(mWeatherTemp, mTemperaturePaint) +
                    TextPaintUtil.getTextWidth(mTemperatureSymbolText, mTemperatureSymbolPaint) +
                    resources.getDimension(R.dimen.widget_weather_symbol_margin_right)
            // 画pm
            if (!mPM.isNullOrEmpty()) {
                it.drawText(mPM, bottomLeft, TextPaintUtil.getTextBaseLineY(mTemperaturePaint, height,
                        mIsIncludePadding), mPMPaint)
            }

            bottomLeft += TextPaintUtil.getTextWidth(mPM, mPMPaint) +
                    resources.getDimension(R.dimen.widget_weather_quality_margin_left)

            // 画天气的质量
            if (!mWeatherQuality.isNullOrEmpty()) {
                it.drawText(mWeatherQuality, bottomLeft, TextPaintUtil.getTextBaseLineY(mTemperaturePaint, height,
                        mIsIncludePadding), mWeatherQualityPaint)
            }

        }
    }

    fun setData(weatherData: WeatherData?) {
        weatherData?.let {
            mWeatherTemp = weatherData.weatherTemperature
            mCity = weatherData.city
            mWeatherDesc = weatherData.weatherDesc
            mPM = weatherData.pm
            mWeatherQuality = weatherData.weatherQuality
            requestLayout()
            invalidate()
        }
    }
}