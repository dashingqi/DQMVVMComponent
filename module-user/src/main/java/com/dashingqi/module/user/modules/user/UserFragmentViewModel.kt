package com.dashingqi.module.user.modules.user

import android.app.Application
import androidx.databinding.ObservableArrayList
import com.dashingqi.base.base.viewmodel.BaseViewModel
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

    init {
        initData()
    }

    private fun initData() {
        items.add(UserItem("可折叠标题栏", "/widget/material"))
        items.add(UserItem("BottomSheetDialog", "/widget/bottom_sheet_dialog"))
        items.add(UserItem("Material Design2", "/widget/material"))
        items.add(UserItem("Material Design3", "/widget/material"))
        items.add(UserItem("Material Design4", "/widget/material"))
        items.add(UserItem("Material Design5", "/widget/material"))
    }

}