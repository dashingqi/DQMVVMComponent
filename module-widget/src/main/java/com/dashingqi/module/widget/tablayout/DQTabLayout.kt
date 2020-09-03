package com.dashingqi.module.widget.tablayout

import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet
import android.util.TypedValue
import android.widget.TextView
import com.dashingqi.module.widget.R
import com.google.android.material.tabs.TabLayout

/**
 * @author : zhangqi
 * @time : 2020/9/3
 * desc :
 */
class DQTabLayout : TabLayout {
    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
            context,
            attrs,
            defStyleAttr
    )

    init {
        setSelectedTabIndicator(R.drawable.widget_base_tab_indicator)
    }

}