package com.dashingqi.module.widget.rvtab

import android.app.Application
import com.dashingqi.base.base.viewmodel.BasePageViewModel
import com.dashingqi.module.widget.R

/**
 * @author : zhangqi
 * @time : 2020/8/25
 * desc :
 */

class WidgetRvTabViewModel(application: Application) : BasePageViewModel<String>(application) {
    init {
        refresh()
    }

    override fun requestData(page: Int) {
        for (index in 0..10) {
            items.add("data${index}")
        }
    }

    override fun getItemLayoutId(): Int = R.layout.widget_rv_tab_item_layout

    /**
     * 初始化tab的数据
     */
    fun setTabData(): ArrayList<String> {
        var tabsData = ArrayList<String>()
        for (index in 0..10) {
            tabsData.add("tab${index}")
        }
        return tabsData
    }
}