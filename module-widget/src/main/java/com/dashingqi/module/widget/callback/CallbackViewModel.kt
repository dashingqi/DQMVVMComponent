package com.dashingqi.module.widget.callback

import android.app.Application
import com.dashingqi.base.base.viewmodel.BaseViewModel

/**
 * @author : zhangqi
 * @time : 2020/9/25
 * desc :
 */
class CallbackViewModel(application: Application) : BaseViewModel(application) {

    init {
        CallBackHelper.doAnything()
    }
}