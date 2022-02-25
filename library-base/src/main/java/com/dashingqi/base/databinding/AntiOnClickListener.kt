package com.dashingqi.base.databinding

import android.view.View

/**
 * @author : zhangqi
 * @time : 2020/5/17
 * desc : 防止重复点击
 */
open class AntiOnClickListener(var clickListener: View.OnClickListener) : View.OnClickListener {
    var last = 0L
    override fun onClick(v: View?) {
        val currentTimeMillis = System.currentTimeMillis()
        if (currentTimeMillis - last > getAntiMillis()) {
            clickListener.onClick(v)
            last = currentTimeMillis
        }
    }

    open fun getAntiMillis(): Long = 1000
}