package com.dashingqi.module.widget.rvtab

import android.content.Context
import android.hardware.SensorManager
import android.view.ViewConfiguration
import androidx.recyclerview.widget.RecyclerView


/**
 * @author : zhangqi
 * @time : 2020/8/28
 * desc :
 */
class WidgetFlingRecyclerView : RecyclerView {

    constructor(context: Context) : super(context)


    override fun fling(velocityX: Int, velocityY: Int): Boolean {
//        var flingX = (velocityX * 0.40f).toInt()
//        val manger = layoutManager
//        val distance = getSplineFlingDistance(flingX)
//        val newDistance: Double = manger.calculateDistance(velocityX, distance)
//        val fixVelocityX = getVelocity(newDistance)
//        flingX = if (velocityX > 0) {
//            fixVelocityX
//        } else {
//            -fixVelocityX
//        }
        return super.fling(velocityX, velocityY)
    }


    /**
     * 根据松手后的滑动速度计算出fling的距离
     *
     * @param velocity
     * @return
     */
    private fun getSplineFlingDistance(velocity: Int): Double {
        val l: Double = getSplineDeceleration(velocity)
        val decelMinusOne: Double = DECELERATION_RATE - 1.0
        return mFlingFriction * getPhysicalCoeff() * Math.exp(DECELERATION_RATE / decelMinusOne * l)
    }

    /**
     * 根据距离计算出速度
     *
     * @param distance
     * @return
     */
    private fun getVelocity(distance: Double): Int {
        val decelMinusOne: Double = DECELERATION_RATE - 1.0
        val aecel: Double = Math.log(distance / (mFlingFriction * mPhysicalCoeff)) * decelMinusOne / DECELERATION_RATE
        return Math.abs((Math.exp(aecel) * (mFlingFriction * mPhysicalCoeff) / INFLEXION) as Int)
    }

    /**
     * --------------fling辅助类---------------
     */
    private val INFLEXION = 0.35f // Tension lines cross at (INFLEXION, 1)

    private val mFlingFriction = ViewConfiguration.getScrollFriction()
    private val DECELERATION_RATE = (Math.log(0.78) / Math.log(0.9)).toFloat()
    private var mPhysicalCoeff = 0f

    private fun getSplineDeceleration(velocity: Int): Double {
        val ppi = this.resources.displayMetrics.density * 160.0f
        val mPhysicalCoeff = (SensorManager.GRAVITY_EARTH // g (m/s^2)
                * 39.37f // inch/meter
                * ppi
                * 0.84f) // look and feel tuning
        return Math.log(INFLEXION * Math.abs(velocity) / (mFlingFriction * mPhysicalCoeff).toDouble())
    }

    private fun getPhysicalCoeff(): Float {
        if (mPhysicalCoeff == 0f) {
            val ppi = this.resources.displayMetrics.density * 160.0f
            mPhysicalCoeff = (SensorManager.GRAVITY_EARTH // g (m/s^2)
                    * 39.37f // inch/meter
                    * ppi
                    * 0.84f) // look and feel tuning
        }
        return mPhysicalCoeff
    }

}