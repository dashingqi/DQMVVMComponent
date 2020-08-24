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
        items.add(UserItem("我的收藏",RoutePath.Collect.COLLECT_LIST))
        items.add(UserItem("我的文章",""))
        items.add(UserItem("TODO",""))
        items.add(UserItem("系统设置",""))
        items.add(UserItem("Debug View", RoutePath.Debug.DEBUG_VIEW))

        labelItems.add("自由可撩")
        labelItems.add("北京")
        labelItems.add("互联网技术")
        labelItems.add("大连海事大学")
        labelItems.add("正大集团")
    }

}