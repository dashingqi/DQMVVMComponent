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

        items.add(DebugItemData("可折叠标题栏", RoutePath.Widget.WIDGET_MATERIAL))
        items.add(DebugItemData("BottomSheetDialog", RoutePath.Widget.WIDGET_BOTTOM_SHEET_DIALOG))
        items.add(DebugItemData("Letter View", RoutePath.Widget.WIDGET_LETTER_VIEW))
        items.add(DebugItemData("CoordinatorLayout Bottom", RoutePath.Widget.WIDGET_COOR_BOTTOM))
        items.add(DebugItemData("物流进度弹窗", RoutePath.Widget.WIDGET_LOGISTICS))
        items.add(DebugItemData("二维码扫描", RoutePath.QrCode.QRCODE_HW))

    }

    override fun requestData(page: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemLayoutId(): Int {
        return R.layout.debug_item_view
    }
}