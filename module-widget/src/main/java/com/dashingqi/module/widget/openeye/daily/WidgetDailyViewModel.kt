package com.dashingqi.module.widget.openeye.daily


import android.app.Application
import com.dashingqi.base.base.callback.LiveDataCallback
import com.dashingqi.base.base.viewmodel.BaseMultiplyPageViewModel
import com.dashingqi.module.widget.BR
import com.dashingqi.module.widget.R
import com.dashingqi.module.widget.net.response.OpenEyeResponse
import com.dashingqi.module.widget.net.WidgetService
import com.dashingqi.module.widget.openeye.constant.TypeConfigUtil
import com.dashingqi.module.widget.openeye.constant.getLayoutId
import me.tatarka.bindingcollectionadapter2.ItemBinding
import me.tatarka.bindingcollectionadapter2.OnItemBind


/**
 * @author : zhangqi
 * @time : 2020/8/26
 * desc :
 */
class WidgetDailyViewModel(application: Application) : BaseMultiplyPageViewModel<OpenEyeResponse.ItemListBean>(application) {

   private val onItemBind: OnItemBind<OpenEyeResponse.ItemListBean> = OnItemBind { itemBinding, position, item ->
        itemBinding.set(BR.item, getLayoutId(item))
    }
    val itemBinding = ItemBinding.of(onItemBind)

    init {
        refresh()
    }

    override fun requestData(page: Int) {
        WidgetService.openEyeInstance.getFeedData().enqueue(LiveDataCallback<OpenEyeResponse>()
                .doOnResponseSuccess { _, response ->
                    handleItemData(page, response.itemList)
                }
        )
    }

}