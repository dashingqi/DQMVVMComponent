package com.dashingqi.module.widget.openeye.discover

import android.app.Application
import com.dashingqi.base.base.viewmodel.BasePageViewModel
import com.dashingqi.module.widget.R
import com.dashingqi.module.widget.net.response.OpenEyeResponse

/**
 * @author : zhangqi
 * @time : 2020/8/27
 * desc :
 */
class WidgetOpenEyeColumnCardListViewModel(application: Application):BasePageViewModel<OpenEyeResponse.DataItemListDataBean>(application) {

    override fun requestData(page: Int) {

    }


    fun setData(data : List<OpenEyeResponse.DataItemListDataBean>){
        items.addAll(data)
    }

    override fun getItemLayoutId(): Int {
       return R.layout.widget_open_eye_item_column_card_list_sun
    }
}