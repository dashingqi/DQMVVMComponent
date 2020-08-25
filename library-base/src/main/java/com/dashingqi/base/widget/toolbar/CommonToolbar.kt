package com.dashingqi.base.widget.toolbar

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.BindingAdapter
import com.dashingqi.base.utils.DensityUtils
import com.dashingqi.library_base.R
import kotlinx.android.synthetic.main.base_common_toolbar_layout.view.*

/**
 * @author : zhangqi
 * @time : 2020/5/27
 * desc : 通用的标题栏
 */
class CommonToolbar : ConstraintLayout {

    var paddingHorizontal = DensityUtils.dip2pxInt(context, 16f)
    var paddingVertical = DensityUtils.dip2pxInt(context, 16f)

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
            mLeftButtonLayout?.setPadding(paddingHorizontal, paddingVertical, 0, paddingVertical)
            //添加到布局中
            mLeftContainer?.addView(mLeftButtonLayout, mLeftContainer!!.childCount)
        }

    }

    private fun initDefaultRightLayout() {
        if (mRightButtonLayout == null) {
            mRightButtonLayout = getDefaultToolbarItem()
            mRightButtonLayout?.setPadding(0, paddingVertical, paddingHorizontal, paddingVertical)
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
        obtainAttributeSet.getFloat(R.styleable.base_common_tool_bar_base_title_size, 16f)?.let { setTitleTextSize(it) }
        obtainAttributeSet.getColor(R.styleable.base_common_tool_bar_base_title_color, Color.BLACK)?.let { setTitleTextColor(it) }


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
    fun setTitleText(charSequence: CharSequence): CommonToolbar {
        mToolbarTitle?.text = charSequence
        return this
    }

    fun setTitleTextSize(size: Float): CommonToolbar {
        mToolbarTitle?.textSize = size
        return this
    }

    fun setTitleTextColor(color: Int): CommonToolbar {
        mToolbarTitle?.setTextColor(color)
        return this
    }

    fun setTitleTextColor(colorStr: String): CommonToolbar {
        mToolbarTitle?.setTextColor(Color.parseColor(colorStr))
        return this
    }

    /**
     * 设置左布局
     */
    fun setLeftText(charSequence: CharSequence): CommonToolbar {
        mLeftButtonLayout?.setTitleText(charSequence)
        return this
    }

    fun setLeftTextColor(colorStr: String): CommonToolbar {
        mLeftButtonLayout?.setTitleTextColor(colorStr)
        return this
    }

    fun setLeftIcon(drawable: Drawable): CommonToolbar {
        mLeftButtonLayout?.setLeftIcon(drawable)
        return this
    }

    fun setLeftTextClickListener(listener: View.OnClickListener): CommonToolbar {
        mLeftButtonLayout?.setLeftTextClickListener(listener)
        return this
    }


    /**
     * 设置右布局
     */
    fun setRightText(charSequence: CharSequence): CommonToolbar {
        mRightButtonLayout?.setTitleText(charSequence)
        return this
    }

    fun setRightTextColor(colorStr: String): CommonToolbar {
        mRightButtonLayout?.setTitleTextColor(colorStr)
        return this
    }

    fun setRightIcon(drawable: Drawable): CommonToolbar {
        mRightButtonLayout?.setRightIcon(drawable)
        return this
    }

    fun setRightTextClickListener(listener: View.OnClickListener): CommonToolbar {
        mRightButtonLayout?.setRightTextClickListener(listener)
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