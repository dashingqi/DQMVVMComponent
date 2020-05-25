package com.dashingqi.base.widget.smart

import android.content.Context
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import com.scwang.smartrefresh.header.WaterDropHeader
import com.scwang.smartrefresh.header.waterdrop.WaterDropView
import java.lang.reflect.Field


/**
 * @author : zhangqi
 * @time : 2020/5/25
 * desc :
 */
class PremixHeader(context: Context?, attrs: AttributeSet?) : WaterDropHeader(context, attrs) {
    constructor(context: Context?) : this(context, null) {}

    fun getWaterDropViewPaint(view: WaterDropView?): Paint? {
        return try {
            val mPaintField: Field = WaterDropView::class.java.getDeclaredField("mPaint")
            mPaintField.setAccessible(true)
            mPaintField.get(view) as Paint
        } catch (e: NoSuchFieldException) {
            e.printStackTrace()
            null
        } catch (e: IllegalAccessException) {
            e.printStackTrace()
            null
        }
    }

    override fun setPrimaryColors(vararg colors: Int) {
        //super.setPrimaryColors(colors);
    }

    init {
        mProgress.alpha = 255
        val paint: Paint? = getWaterDropViewPaint(mWaterDropView)
        if (paint != null) {
            paint.setColor(Color.parseColor("#33000000"))
            paint.setShadowLayer(0F, 0F, 0F, 0x00000000)
        }
    }
}