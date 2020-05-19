package com.dashingqi.base.widget.loading

import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.graphics.drawable.Drawable

/**
 * @author : zhangqi
 * @time : 2020/5/19
 * desc : base的对话框
 * 1. 能设置宽和高
 * 2. 能设置位置
 * 3. 能设置进入退出的动画
 * 4. 设置背景
 */
open class BaseDialog : Dialog {

    constructor(context: Context) : super(context)

    constructor(context: Context, resId: Int) : super(context, resId)

    constructor(context: Context, cancel: Boolean, onCancelListener: DialogInterface.OnCancelListener?) : super(context, cancel, onCancelListener)

    /**
     * 设置Dialog的宽度
     */
    fun setDialogWidth(width: Int) {
        window?.let {
            var widthAttributes = it.attributes
            widthAttributes.width = width
            it.attributes = widthAttributes
        }

    }


    /**
     * 设置Dialog的高度
     */
    fun setDialogHeight(height: Int) {
        window?.let {
            var heightAttributes = it.attributes
            heightAttributes.height = height
            it.attributes = heightAttributes
        }
    }

    /**
     * 设置当前dialog所处的位置
     */
    fun setDialogGravity(dialogGravity: Int) {
        window?.setGravity(dialogGravity)
    }

    /**
     * 设置
     */
    fun setDialogInAndOutAnimation(animationRes: Int) {
        window?.setWindowAnimations(animationRes)
    }

    /**
     * 设置背景
     */
    fun setDialogBackground(bgDraw: Drawable) {
        window?.decorView?.background = bgDraw
    }

    fun setDialogBackground(bgRes: Int) {
        window?.decorView?.setBackgroundResource(bgRes)
    }

    fun setDialogBackgroundColor(bgColor: Int) {
        window?.decorView?.setBackgroundColor(bgColor)
    }
}