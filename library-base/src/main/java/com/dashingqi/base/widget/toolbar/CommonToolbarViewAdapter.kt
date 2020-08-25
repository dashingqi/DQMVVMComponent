package com.dashingqi.base.widget.toolbar

import android.graphics.Color
import android.view.View
import androidx.databinding.BindingAdapter

/**
 * @author : zhangqi
 * @time : 2020/8/25
 * desc :
 */
object CommonToolbarViewAdapter {

    @JvmStatic
    @BindingAdapter(value = ["titleText"], requireAll = false)
    fun setTitleText(commonToolbar: CommonToolbar, text: String) {
        commonToolbar.setTitleText(text)
    }

    @JvmStatic
    @BindingAdapter(value = ["titleTextSize"], requireAll = false)
    fun setTitleTextSize(commonToolbar: CommonToolbar, textSize: Float) {
        commonToolbar.setTitleTextSize(textSize)
    }

    @JvmStatic
    @BindingAdapter(value = ["titleTextColor"], requireAll = false)
    fun setTitleTextColor(commonToolbar: CommonToolbar, titleTextColor: String) {
        commonToolbar.setTitleTextColor(Color.parseColor(titleTextColor))
    }

    @JvmStatic
    @BindingAdapter(value = ["titleTextColorInt"],requireAll = false)
    fun setTitleTextColor(commonToolbar: CommonToolbar,titleTextColor: Int){
        commonToolbar.setTitleTextColor(titleTextColor)
    }

    @JvmStatic
    @BindingAdapter(value = ["leftText"], requireAll = false)
    fun setLeftText(commonToolbar: CommonToolbar, text: String) {
        commonToolbar.setLeftText(text)
    }

    @JvmStatic
    @BindingAdapter(value = ["leftTextColor"], requireAll = false)
    fun setLeftTextColor(commonToolbar: CommonToolbar, color: String) {
        commonToolbar.setLeftTextColor(color)
    }

    @JvmStatic
    @BindingAdapter(value = ["rightText"], requireAll = false)
    fun setRightText(commonToolbar: CommonToolbar, text: String) {
        commonToolbar.setRightText(text)
    }

    @JvmStatic
    @BindingAdapter(value = ["rightTextColor"], requireAll = false)
    fun setRightTextColor(commonToolbar: CommonToolbar, rightTextColor: String) {
        commonToolbar.setRightTextColor(rightTextColor)
    }

    @JvmStatic
    @BindingAdapter(value = ["leftTextClickListener"], requireAll = false)
    fun setLeftTextClickListener(commonToolbar: CommonToolbar, leftTextClickListener: View.OnClickListener) {
        commonToolbar.setLeftTextClickListener(leftTextClickListener)
    }

    @JvmStatic
    @BindingAdapter(value = ["rightTextClickListener"], requireAll = false)
    fun setRightTextClickListener(commonToolbar: CommonToolbar, rightTextClickListener: View.OnClickListener) {
        commonToolbar.setRightTextClickListener(rightTextClickListener)
    }
}