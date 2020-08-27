package com.dashingqi.module.widget.openeye.discover

import android.app.Application
import com.dashingqi.base.base.viewmodel.BaseMultiplyPageViewModel
import com.dashingqi.base.utils.JsonUtils
import com.dashingqi.module.widget.BR
import com.dashingqi.module.widget.R
import com.dashingqi.module.widget.net.response.OpenEyeResponse
import com.dashingqi.module.widget.openeye.constant.TypeConfigUtil
import com.dashingqi.module.widget.openeye.constant.getLayoutId
import me.tatarka.bindingcollectionadapter2.ItemBinding
import me.tatarka.bindingcollectionadapter2.OnItemBind

/**
 * @author : zhangqi
 * @time : 2020/8/26
 * desc :
 */
class WidgetDiscoverViewModel(application: Application) : BaseMultiplyPageViewModel<OpenEyeResponse.ItemListBean>(application) {

    private val onItemBind: OnItemBind<OpenEyeResponse.ItemListBean> = OnItemBind { itemBinding, position, item ->

        when (item.type) {
            TypeConfigUtil.HORIZONTAL_SCROLL_CARD -> {
                val scrollCardViewModel = WidgetOpenEyeHorizontalScrollCardViewModel(application)
                scrollCardViewModel.setData(item.data.itemList)
                itemBinding.set(BR.item, R.layout.widget_open_eye_item_horizontal_scroll_card).bindExtra(BR.viewModel, scrollCardViewModel)
            }

            TypeConfigUtil.COLUMN_CARD_LIST -> {
                val columnCardListViewModel = WidgetOpenEyeColumnCardListViewModel(application)
                columnCardListViewModel.setData(item.data.itemList)
                itemBinding.set(BR.item, R.layout.widget_open_eye_item_column_card_list).bindExtra(BR.viewModel, columnCardListViewModel)
            }

            else ->
                itemBinding.set(BR.item, getLayoutId(item))
        }
    }
    val itemBinding = ItemBinding.of(onItemBind)

    init {
        refresh()
    }

    override fun requestData(page: Int) {
        var openEyeData = JsonUtils.jsonToObject(getApplication(), "openeye.json", OpenEyeResponse::class.java)
        var data = openEyeData.itemList.filter { (it.type != TypeConfigUtil.SPECIAL_SQUARE_CARD_COLLECTION) }
        handleItemData(page, data)
    }
}