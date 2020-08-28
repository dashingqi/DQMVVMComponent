package com.dashingqi.module.widget.rvtab

import android.app.Application
import com.dashingqi.base.base.viewmodel.BasePageViewModel
import com.dashingqi.module.widget.R

/**
 * @author : zhangqi
 * @time : 2020/8/28
 * desc :
 */
class WidgetRvFlingViewModel(application: Application) : BasePageViewModel<String>(application) {
    init {
        refresh()
    }

    override fun requestData(page: Int) {
        for (index in 0..50) {
            items.add("")
        }
    }

    override fun getItemLayoutId(): Int {
        return R.layout.widget_item_rv_fling
    }
}