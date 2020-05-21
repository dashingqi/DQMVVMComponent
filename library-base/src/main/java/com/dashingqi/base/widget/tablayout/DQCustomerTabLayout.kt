package com.dashingqi.base.widget.tablayout

import android.content.Context
import android.graphics.Typeface
import android.graphics.drawable.Drawable
import android.provider.CalendarContract
import android.util.AttributeSet
import android.widget.TextView
import com.dashingqi.library_base.R
import com.google.android.material.tabs.TabLayout

/**
 * @author : zhangqi
 * @time : 2020/5/21
 * desc :自定义的TabLayout
 * 目前主要就是为了设置一个自定义的指示器
 */
class DQCustomerTabLayout : TabLayout {
    constructor(context: Context) : super(context)
    constructor(context: Context, attributeSet: AttributeSet) : super(context, attributeSet)
    constructor(context: Context, attributeSet: AttributeSet, defStyleAttr: Int) : super(context, attributeSet, defStyleAttr)

    init {
        addOnTabSelectedListener(object : OnTabSelectedListener {
            override fun onTabReselected(tab: Tab?) {
            }

            override fun onTabUnselected(tab: Tab?) {
                //当没有被选中，设置文字的样式
                tab?.customView?.findViewById<TextView>(R.id.customerTableLayoutTitle)?.let {
                    it.textSize = 14F
                    it.typeface = Typeface.DEFAULT
                }
            }

            override fun onTabSelected(tab: Tab?) {
                //当被选中，设置文字的样式
                tab?.customView?.findViewById<TextView>(R.id.customerTableLayoutTitle)?.let {
                    it.textSize = 18F
                    it.typeface = Typeface.DEFAULT_BOLD
                }
            }
        })
        //设置指示器的样式
        setSelectedTabIndicator(R.drawable.base_shape_customer_tl_indicator)
    }

    /**
     * 自定义Tab
     */
    override fun newTab(): Tab {
        val tab = super.newTab()
        tab.setCustomView(R.layout.base_tab_layout_item)
        return tab
    }


}