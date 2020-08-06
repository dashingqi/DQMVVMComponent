package com.dashingqi.module.debug

import android.app.Application
import com.dashingqi.base.base.viewmodel.BasePageViewModel
import com.dashingqi.base.base.viewmodel.BaseViewModel
import com.dashingqi.base.route.RoutePath
import com.dashingqi.module.debug.data.DebugItemData

/**
 * @author : zhangqi
 * @time : 2020/7/27
 * desc :
 */
class DebugViewModel(application: Application) : BasePageViewModel<DebugItemData>(application) {

    init {
        items.add(DebugItemData("支付封装测试", RoutePath.Pay.PAY_TEST_ACTIVITY))
        items.add(DebugItemData("自定义电池", RoutePath.Widget.WIDGET_BATTERY))
        items.add(DebugItemData("自定义圆环进度", RoutePath.Widget.WIDGET_CIRCLE_PROGRESS))

    }

    override fun requestData(page: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemLayoutId(): Int {
        return R.layout.debug_item_view
    }
}