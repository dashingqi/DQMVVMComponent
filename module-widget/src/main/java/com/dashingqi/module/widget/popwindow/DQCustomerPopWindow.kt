package com.dashingqi.module.widget.popwindow

import android.content.Context
import android.graphics.Rect
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupWindow
import com.dashingqi.module.widget.databinding.WidgetCustomerPopWindowLayoutBinding

/**
 * @author : zhangqi
 * @time : 2020/9/24
 * desc : 自定义PopWindow
 */

class DQCustomerPopWindow(context: Context) : PopupWindow(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT) {

    var viewBinding: WidgetCustomerPopWindowLayoutBinding = WidgetCustomerPopWindowLayoutBinding.inflate(LayoutInflater.from(context))

    init {

        isOutsideTouchable = true
        isFocusable = true
        contentView = viewBinding.root
    }

    override fun showAsDropDown(anchor: View?, xoff: Int, yoff: Int) {
        // 7.0以上的一个适配
        if (Build.VERSION.SDK_INT >= 24) {
            val visibleFrame = Rect()
            anchor!!.getGlobalVisibleRect(visibleFrame)
            val height: Int = anchor.resources.displayMetrics.heightPixels - visibleFrame.bottom
            setHeight(height)
        }
        super.showAsDropDown(anchor, xoff, yoff)
    }
}