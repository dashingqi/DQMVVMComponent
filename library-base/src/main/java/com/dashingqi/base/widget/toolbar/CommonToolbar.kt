package com.dashingqi.base.widget.toolbar

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.children
import com.dashingqi.library_base.R
import kotlinx.android.synthetic.main.base_common_toolbar_layout.view.*

/**
 * @author : zhangqi
 * @time : 2020/5/27
 * desc : 通用的标题栏
 */
class CommonToolbar : ConstraintLayout {

    private var mToolbarTitle: TextView? = null
    private var mLeftContainer: LinearLayout? = null
    private var mRightContainer: LinearLayout? = null

    private var mLeftButtonLayout: CommonToolbarItem? = null
    private var mRightButtonLayout: CommonToolbarItem? = null

    init {
        var rootView = View.inflate(context, R.layout.base_common_toolbar_layout, this)
        mToolbarTitle = rootView.toolbarTitle
        mLeftContainer = rootView.leftContainer
        mRightContainer = rootView.rightContainer
        initDefaultLeftLayout()
        initDefaultRightLayout()
    }

    constructor(context: Context, attributeSet: AttributeSet) : super(context, attributeSet) {
        initAttribute(attributeSet)
    }

    private fun initDefaultLeftLayout() {
        if (mLeftButtonLayout == null) {
            mLeftButtonLayout = getDefaultToolbarItem()
            //添加到布局中
            mLeftContainer?.addView(mLeftButtonLayout, mLeftContainer!!.childCount)
        }

    }

    private fun initDefaultRightLayout() {

        if (mRightButtonLayout == null) {
            mRightButtonLayout = getDefaultToolbarItem()
            mRightContainer?.addView(mRightButtonLayout, mRightContainer!!.childCount)
        }

    }

    private fun getDefaultToolbarItem(): CommonToolbarItem {
        return CommonToolbarItem(context)
    }

    /**
     * 获取到自定义属性
     */
    private fun initAttribute(attributeSet: AttributeSet) {
        var obtainAttributeSet = context.obtainStyledAttributes(attributeSet, R.styleable.base_common_tool_bar)

        //设置标题
        obtainAttributeSet.getString(R.styleable.base_common_tool_bar_base_title)?.let { setTitleText(it) }

        //设置左边布局
        if (obtainAttributeSet.hasValue(R.styleable.base_common_tool_bar_base_left_text)) {
            obtainAttributeSet.getString(R.styleable.base_common_tool_bar_base_left_text)?.let { setLeftText(it) }
        } else {
            setLeftText(getDefaultLeftText()!!)
        }

        if (obtainAttributeSet.hasValue(R.styleable.base_common_tool_bar_base_left_icon)) {
            obtainAttributeSet.getDrawable(R.styleable.base_common_tool_bar_base_left_icon)?.let { setLeftIcon(it) }
        } else {
            setLeftIcon(getDefaultLeftIcon()!!)
        }

        //设置右边布局

        if (obtainAttributeSet.hasValue(R.styleable.base_common_tool_bar_base_right_text)) {
            obtainAttributeSet.getString(R.styleable.base_common_tool_bar_base_right_text)?.let { setRightText(it) }
        } else {
            setRightText(getDefaultRightText()!!)
        }

        if (obtainAttributeSet.hasValue(R.styleable.base_common_tool_bar_base_right_icon)) {
            obtainAttributeSet.getDrawable(R.styleable.base_common_tool_bar_base_right_icon)?.let { setRightIcon(it) }
        } else {
            getDefaultRightIcon()?.let {
                setRightIcon(it)
            }
        }

        //设置右边布局

        obtainAttributeSet.recycle()
    }

    /**
     * 设置标题
     */
    private fun setTitleText(charSequence: CharSequence): CommonToolbar {
        mToolbarTitle?.text = charSequence
        return this
    }

    private fun setLeftText(charSequence: CharSequence): CommonToolbar {
        mLeftButtonLayout?.setTitleText(charSequence)
        return this
    }

    private fun setLeftIcon(drawable: Drawable): CommonToolbar {
        mLeftButtonLayout?.setLeftIcon(drawable)
        return this
    }

    private fun setRightText(charSequence: CharSequence): CommonToolbar {
        mRightButtonLayout?.setTitleText(charSequence)
        return this
    }

    private fun setRightIcon(drawable: Drawable): CommonToolbar {
        mRightButtonLayout?.setRightIcon(drawable)
        return this
    }

    /**
     * default
     */

    private fun getDefaultLeftText(): CharSequence? {
        return ""
    }

    private fun getDefaultLeftIcon(): Drawable? {
        return resources.getDrawable(R.drawable.res_arrow_left_black)
    }


    private fun getDefaultRightText(): CharSequence? {
        return ""
    }

    private fun getDefaultRightIcon(): Drawable? {
        return null
    }
}