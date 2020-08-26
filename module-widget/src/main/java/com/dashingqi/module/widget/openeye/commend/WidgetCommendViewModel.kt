package com.dashingqi.module.widget.openeye.commend

import android.app.Application
import com.dashingqi.base.base.callback.LiveDataCallback
import com.dashingqi.base.base.response.BaseResponse
import com.dashingqi.base.base.viewmodel.BaseMultiplyPageViewModel
import com.dashingqi.dqlog.DQLog
import com.dashingqi.module.widget.BR
import com.dashingqi.module.widget.R
import com.dashingqi.module.widget.net.WidgetService
import com.dashingqi.module.widget.net.response.OpenEyeResponse
import com.dashingqi.module.widget.openeye.constant.TypeConfigUtil
import me.tatarka.bindingcollectionadapter2.ItemBinding
import me.tatarka.bindingcollectionadapter2.OnItemBind

/**
 * @author : zhangqi
 * @time : 2020/8/26
 * desc :
 */
class WidgetCommendViewModel(application: Application) : BaseMultiplyPageViewModel<OpenEyeResponse.ItemListBean>(application) {


    private val onItemBind: OnItemBind<OpenEyeResponse.ItemListBean> = OnItemBind { itemBinding, position, item ->
        itemBinding.set(BR.item, getLayoutId(item))
    }

    val itemBinding = ItemBinding.of(onItemBind)

    init {
        refresh()
    }

    override fun requestData(page: Int) {
        WidgetService.openEyeInstance.getAllRec().enqueue(LiveDataCallback<OpenEyeResponse>().doOnResponseSuccess { _, response ->

           var data =  response.itemList.filter { it.type != TypeConfigUtil.SQUARE_CARD_COLLECTION }
            handleItemData(page, data)
        })
    }

    private fun getLayoutId(item: OpenEyeResponse.ItemListBean): Int {
        return when (item.type) {
            TypeConfigUtil.TEXT_CARD -> R.layout.widget_open_eye_item_text
            TypeConfigUtil.PICTURE_FOLLOW_CARD -> R.layout.widget_open_eye_item_picture_follow_card
            TypeConfigUtil.FOLLOW_CARD -> R.layout.widget_open_eye_item_video_follow_card
            TypeConfigUtil.VIDEO_SMALL_CARD -> R.layout.widget_open_eye_item_samll_video
            TypeConfigUtil.BANNER2 -> R.layout.widget_open_eye_item_banner2
            else -> R.layout.widget_open_eye_item_text
        }
    }
}