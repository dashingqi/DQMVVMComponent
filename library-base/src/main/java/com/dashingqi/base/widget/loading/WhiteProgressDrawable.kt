package com.dashingqi.base.widget.loading

import android.graphics.Color
import com.scwang.smart.drawable.ProgressDrawable

/**
 * @author : zhangqi
 * @time : 2020/5/19
 * desc :
 */
class WhiteProgressDrawable : ProgressDrawable() {
    init {
        mPaint.color = Color.parseColor("#ffffffff")
    }
}