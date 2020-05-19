package com.dashingqi.base.widget.loading

import android.app.Dialog
import android.content.Context
import android.content.LocusId

/**
 * @author : zhangqi
 * @time : 2020/5/19
 * desc :
 */
class BaseLoadingDialog : Dialog {

    constructor(context: Context) : super(context)

    constructor(context: Context, resId: Int) : super(context, resId)
}