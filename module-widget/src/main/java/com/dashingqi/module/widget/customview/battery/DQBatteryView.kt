package com.dashingqi.module.widget.customview.battery

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.Log
import android.view.View
import com.dashingqi.base.utils.DensityUtils
import com.orhanobut.logger.Logger
import java.util.jar.Attributes

/**
 *
 * @ProjectName: DQMVVMComponent
 * @Package: com.dashingqi.module.widget.customview
 * @ClassName: DQBatteryView
 * @Author: DashingQI
 * @CreateDate: 2020/7/27 10:13 PM
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/7/27 10:13 PM
 * @UpdateRemark:
 * @Version: 1.0
 */
class DQBatteryView : View {

    /**
     * 绘制电池边的画笔
     */
    lateinit var batteryBorderPaint: Paint
    lateinit var borderPath: Path

    lateinit var batteryHeaderPath: Path

    /**
     * 绘制电池能量的画笔
     */
    lateinit var batteryPowerPaint: Paint

    private val batteryBorderWidth = 12f

    var batteryPowerValue: Int = 0

    var headerHeight = DensityUtils.dip2px(context, 40f)
    var headerWidth = DensityUtils.dip2px(context, 70f)


    constructor(context: Context) : super(context)
    constructor(context: Context, attributes: AttributeSet) : super(
            context,
            attributes
    )

    init {
        initBorderPaint()
        initBatteryPowerPaint()
    }

    private fun initBorderPaint() {
        borderPath = Path()
        batteryHeaderPath = Path()
        //设置边框的画笔
        batteryBorderPaint = Paint()
        //设置画笔颜色
        batteryBorderPaint.color = Color.WHITE
        //设置画笔抗锯齿
        batteryBorderPaint.isAntiAlias = true
        //设置边框的粗细
        batteryBorderPaint.strokeWidth = batteryBorderWidth
        //描边
        batteryBorderPaint.style = Paint.Style.STROKE
    }

    private fun initBatteryPowerPaint() {
        batteryPowerPaint = Paint()
        batteryPowerPaint.color = Color.RED
        batteryPowerPaint.style = Paint.Style.FILL_AND_STROKE
        batteryPowerPaint.isAntiAlias = true
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }

    /**
     * 设置电池的能量
     */
    fun setBatteryPower(batteryPower: Int) {
        Log.d("setBatteryPower", "perform ---> $batteryPower")
        when {
            batteryPower < 20 -> {
                batteryPowerPaint.color = Color.RED
            }
            batteryPower < 60 -> {
                batteryPowerPaint.color = Color.YELLOW
            }
            else -> {
                batteryPowerPaint.color = Color.GREEN
            }
        }
        batteryPowerValue = batteryPower
        Log.d("setBatteryPower", "$batteryPowerValue")
        invalidate()
    }

    override fun draw(canvas: Canvas?) {
        super.draw(canvas)
        //画边
        drawBatteryBorder(canvas)
        //画电量
        drawBatteryPower(canvas)
    }

    /**
     * 画电池的边框
     */
    private fun drawBatteryBorder(canvas: Canvas?) {

        var shoulderWidth = (measuredWidth - headerWidth) / 2
        borderPath.moveTo(measuredWidth.toFloat(), measuredHeight.toFloat())
        borderPath.lineTo(0f, measuredHeight.toFloat())
        borderPath.lineTo(0f, headerHeight)
        borderPath.lineTo(shoulderWidth, headerHeight)
        borderPath.lineTo(shoulderWidth, 0f)
        borderPath.lineTo(shoulderWidth + headerWidth, 0f)
        borderPath.lineTo(shoulderWidth + headerWidth, headerHeight)
        borderPath.lineTo(measuredWidth.toFloat(), headerHeight)
        borderPath.lineTo(measuredWidth.toFloat(), measuredHeight.toFloat())
        canvas?.drawPath(borderPath, batteryBorderPaint)
    }

    /**
     * 画电池的电量
     */
    private fun drawBatteryPower(canvas: Canvas?) {
        var value1 = (1 - batteryPowerValue / 100F) * 0.9f
        Logger.d("value1 ----> $value1")


        var batteryRectF = Rect(batteryBorderWidth.toInt(), (measuredHeight * value1+headerHeight).toInt(), (measuredWidth - batteryBorderWidth).toInt(), (measuredHeight - batteryBorderWidth).toInt())
        var top = batteryRectF.top
        Log.d("top---> ", "$top")
        canvas?.drawRect(batteryRectF, batteryPowerPaint)
    }
}