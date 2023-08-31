package com.dashingqi.module.widget.customview.circleprogress

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.dashingqi.base.utils.DensityUtils
import com.orhanobut.logger.Logger
import kotlin.math.cos
import kotlin.math.sin

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

    lateinit var mArcPaint: Paint

    lateinit var mEndCirclePaint: Paint

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
        mCirclePaint.strokeWidth = 12f

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

        /**
         * 画圆弧的
         */
        mArcPaint = Paint()
        mArcPaint.isAntiAlias = true
        mArcPaint.color = Color.YELLOW
        mArcPaint.style = Paint.Style.STROKE
        mArcPaint.strokeWidth = 15f

        mEndCirclePaint = Paint()
        mEndCirclePaint.color = Color.BLACK
        mEndCirclePaint.strokeWidth = 5f
        mEndCirclePaint.isAntiAlias = true
        mEndCirclePaint.style = Paint.Style.STROKE

    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        drawCircle(canvas)
        drawText1(canvas)
        drawText2(canvas)
        drawProgressArc(canvas)
        drawEndCircle(canvas)
    }

    /**
     * 画圆
     */
    private fun drawCircle(canvas: Canvas?) {
        canvas?.drawCircle(measuredWidth / 2f, measuredHeight / 2f, DensityUtils.dip2px(context, 60f), mCirclePaint)
    }

    /**
     * 画流量值
     */
    private fun drawText1(canvas: Canvas?) {
        var rect = Rect()
        var text = "${mFlowValue * 2} M"
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


    /**
     * 画终点上的圆
     */
    private fun drawEndCircle(canvas: Canvas?) {
        //将角度转化成弧度
        var angle = (mFlowValue * 360 / 100f * Math.PI / 180).toFloat()
        var radius = DensityUtils.dip2px(context, 60f)
        var x = measuredWidth / 2 + radius * sin(angle)
        var y = measuredHeight / 2 - radius * cos(angle)
        canvas?.drawCircle(x, y, 15f, mEndCirclePaint)


    }

    /**
     * 画进度圆弧
     */
    private fun drawProgressArc(canvas: Canvas?) {
        //圆的直径
        var circle2Radius = DensityUtils.dip2px(context, 120f)
        var padding = (measuredWidth - circle2Radius) / 2
        //圆弧的范围
        var arcRectF = RectF(padding, padding, padding + circle2Radius, padding + circle2Radius)
        //画圆弧
        canvas?.drawArc(arcRectF, -90F, mFlowValue * 360 / 100F, false, mArcPaint)
    }

    fun setFlowValue(flowValue: Int) {
        mFlowValue = flowValue
        invalidate()
    }
}