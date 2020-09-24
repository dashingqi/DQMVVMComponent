package com.dashingqi.module.widget.popwindow

import android.content.Context
import android.view.LayoutInflater
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

        contentView = viewBinding.root
    }
}