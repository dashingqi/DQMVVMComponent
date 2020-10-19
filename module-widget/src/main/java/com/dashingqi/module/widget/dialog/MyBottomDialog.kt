package com.dashingqi.module.widget.dialog

import android.content.Context
import com.dashingqi.dqdialog.BottomDialog
import com.dashingqi.module.widget.R

/**
 * @author : zhangqi
 * @time : 2020/9/16
 * desc :
 */
class MyBottomDialog(context: Context) : BottomDialog(context) {

    init {
        setContentView(R.layout.widget_bottom_dialog_layout)
    }
}