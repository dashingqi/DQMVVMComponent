package com.dashingqi.module.debug

import android.app.Application
import com.dashingqi.base.base.viewmodel.BasePageViewModel
import com.dashingqi.base.base.viewmodel.BaseViewModel
import com.dashingqi.base.route.RoutePath
import com.dashingqi.base.utils.toast
import com.dashingqi.module.debug.data.DebugItemData

/**
 * @author : zhangqi
 * @time : 2020/7/27
 * desc :
 */
class DebugViewModel(application: Application) : BasePageViewModel<DebugItemData>(application) {

    init {
        refresh()
    }

    override fun requestData(page: Int) {

        items.add(DebugItemData("支付封装测试", RoutePath.Pay.PAY_TEST_ACTIVITY))
        items.add(DebugItemData("自定义电池", RoutePath.Widget.WIDGET_BATTERY))
        items.add(DebugItemData("自定义圆环进度", RoutePath.Widget.WIDGET_CIRCLE_PROGRESS))
        items.add(DebugItemData("可折叠标题栏", RoutePath.Widget.WIDGET_MATERIAL))
        items.add(DebugItemData("BottomSheetDialog", RoutePath.Widget.WIDGET_BOTTOM_SHEET_DIALOG))
        items.add(DebugItemData("Letter View", RoutePath.Widget.WIDGET_LETTER_VIEW))
        items.add(DebugItemData("CoordinatorLayout Bottom", RoutePath.Widget.WIDGET_COOR_BOTTOM))
        items.add(DebugItemData("物流进度弹窗", RoutePath.Widget.WIDGET_LOGISTICS))
        items.add(DebugItemData("二维码扫描", RoutePath.QrCode.QRCODE_HW))
        items.add(DebugItemData("RV TabLayout 联动", RoutePath.Widget.WIDGET_RV_TAB_LAYOUT))
        items.add(DebugItemData("开眼首页", RoutePath.Widget.WIDGET_OPEN_EYE))
        items.add(DebugItemData("仿抖音页面切换效果", RoutePath.Widget.WIDGET_TIK_TOK_SNAP))
        items.add(DebugItemData("Rv的fling", RoutePath.Widget.WIDGET_RV_FLING))
        items.add(DebugItemData("叮咚买菜商品详情", RoutePath.Widget.WIDGET_DDMC_PRODUCT_DETAIL))
        items.add(DebugItemData("loading dialog 测试", RoutePath.Widget.WIDGET_LOADING_DIALOG))
        items.add(DebugItemData("bottom dialog 测试", RoutePath.Widget.WIDGET_BOTTOM_DIALOG))
        items.add(DebugItemData("box dialog 测试", RoutePath.Widget.WIDGET_BOX_DIALOG))
        items.add(DebugItemData("time picker 测试", RoutePath.Widget.WIDGET_TIME_PICKER))
        items.add(DebugItemData("pop window", RoutePath.Widget.WIDGET_POP_WINDOW))
        items.add(DebugItemData("测试回调", RoutePath.Widget.WIDGET_CALL_BACK))

    }

    override fun getItemLayoutId(): Int {
        return R.layout.debug_item_view
    }

    fun rightTextClickListener() {
        toast("点击了右标题")
    }

    fun leftTextClickListener() {
        toast("点击了左标题")
    }
}