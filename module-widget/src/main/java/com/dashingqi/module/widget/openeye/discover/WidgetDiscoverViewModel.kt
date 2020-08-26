package com.dashingqi.module.widget.openeye.discover

import android.app.Application
import com.dashingqi.base.base.callback.LiveDataCallback
import com.dashingqi.base.base.response.BaseResponse
import com.dashingqi.base.base.viewmodel.BaseMultiplyPageViewModel
import com.dashingqi.module.widget.openeye.net.WidgetService

/**
 * @author : zhangqi
 * @time : 2020/8/26
 * desc :
 */
class WidgetDiscoverViewModel(application: Application) : BaseMultiplyPageViewModel<String>(application) {
    init {
        refresh()
    }

    override fun requestData(page: Int) {
        WidgetService.openEyeInstance.getDiscoveryData().enqueue(LiveDataCallback<BaseResponse>())
    }
}