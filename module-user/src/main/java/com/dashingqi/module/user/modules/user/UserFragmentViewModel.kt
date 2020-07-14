package com.dashingqi.module.user.modules.user

import android.app.Application
import androidx.databinding.ObservableArrayList
import com.dashingqi.base.base.viewmodel.BaseViewModel
import com.dashingqi.base.route.RoutePath
import com.dashingqi.module.user.BR
import com.dashingqi.module.user.R
import com.dashingqi.module.user.bean.UserItem
import me.tatarka.bindingcollectionadapter2.ItemBinding

/**
 * @author : zhangqi
 * @time : 2020/5/21
 * desc :
 */
class UserFragmentViewModel(application: Application) : BaseViewModel(application) {

    /**
     * 列表的数据源
     */
    var items = ObservableArrayList<UserItem>()

    var itemBinding = ItemBinding.of<UserItem>(BR.item, R.layout.user_list_item)

    var labelItems = ObservableArrayList<String>()
    var labelBinding = ItemBinding.of<String>(BR.labelItem, R.layout.user_label_item)

    init {
        initData()
    }

    private fun initData() {
        items.add(UserItem("可折叠标题栏", RoutePath.Widget.WIDGET_MATERIAL))
        items.add(UserItem("BottomSheetDialog", RoutePath.Widget.WIDGET_BOTTOM_SHEET_DIALOG))
        items.add(UserItem("Ex Fresh", RoutePath.Widget.WIDGET_EX_FRESH))
        items.add(UserItem("Letter View", RoutePath.Widget.WIDGET_LETTER_VIEW))
        items.add(UserItem("CoordinatorLayout Bottom", RoutePath.Widget.WIDGET_COOR_BOTTOM))
        items.add(UserItem("物流进度弹窗", RoutePath.Widget.WIDGET_LOGISTICS))
        items.add(UserItem("二维码扫描",RoutePath.QrCode.QRCODE_HW))
        items.add(UserItem("Material Design3", "/widget/material"))
        items.add(UserItem("Material Design4", "/widget/material"))
        items.add(UserItem("Material Design5", "/widget/material"))
        items.add(UserItem("Material Design3", "/widget/material"))
        items.add(UserItem("Material Design4", "/widget/material"))
        items.add(UserItem("Material Design5", "/widget/material"))
        items.add(UserItem("Material Design3", "/widget/material"))
        items.add(UserItem("Material Design4", "/widget/material"))
        items.add(UserItem("Material Design5", "/widget/material"))
        items.add(UserItem("Material Design3", "/widget/material"))
        items.add(UserItem("Material Design4", "/widget/material"))
        items.add(UserItem("Material Design5", "/widget/material"))

        labelItems.add("自由可撩")
        labelItems.add("北京")
        labelItems.add("互联网技术")
        labelItems.add("大连海事大学")
        labelItems.add("正大集团")
    }

}