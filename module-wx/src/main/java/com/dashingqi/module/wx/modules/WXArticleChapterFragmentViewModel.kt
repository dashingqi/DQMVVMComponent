package com.dashingqi.module.wx.modules

import android.app.Application
import com.dashingqi.base.base.callback.LiveDataCallback
import com.dashingqi.base.base.viewmodel.BasePageViewModel
import com.dashingqi.library.service.providers.common.response.CommonArticleResponse
import com.dashingqi.module.wx.R
import com.dashingqi.module.wx.net.IWxService
import com.dashingqi.module.wx.net.WxArticleListResponse
import com.orhanobut.logger.Logger

/**
 * @author : zhangqi
 * @time : 2020/5/23
 * desc :
 */
class WXArticleChapterFragmentViewModel(application: Application, var id: Int) : BasePageViewModel<CommonArticleResponse>(application) {
    init {
        Logger.d("viewModel ---> id ----> $id")
        refresh()
    }


    override fun requestData(page: Int) {
        IWxService.INSTANCE.getWxArticleList(id, page).enqueue(LiveDataCallback<WxArticleListResponse>(baseLiveData)
                .bindSmartRefresh()
                .bindStateLayout()
                .doOnResponseSuccess { _, response ->
                    Logger.d("size = ${response.data.datas.size}")
                    handleItemData(page, response.data.datas)
                })
    }

    override fun getItemLayoutId(): Int = R.layout.wx_item_article


}