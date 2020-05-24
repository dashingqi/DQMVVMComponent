package com.dashingqi.module.wx.modules

import android.app.Application
import com.dashingqi.base.base.callback.LiveDataCallback
import com.dashingqi.base.base.response.BaseResponse
import com.dashingqi.base.base.viewmodel.BasePageViewModel
import com.dashingqi.library.service.providers.common.response.CommonArticleResponse
import com.dashingqi.library.service.providers.common.response.CommonClassifyResponse
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
    }

    fun getArticleList() {
        IWxService.INSTANCE.getWxArticleList(id, 1).enqueue(LiveDataCallback<WxArticleListResponse>(baseLiveData)
                .doOnResponseSuccess { _, response ->
                    Logger.d("size ----> ${response.datas.size}")
                })
    }

    override fun requestData(pag: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemLayoutId(): Int {
        TODO("Not yet implemented")
    }


}