package com.dashingqi.module.wx.modules

import android.app.Application
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import com.alibaba.android.arouter.launcher.ARouter
import com.dashingqi.base.base.callback.LiveDataCallback
import com.dashingqi.base.base.viewmodel.BaseViewModel
import com.dashingqi.library.service.providers.common.response.CommonClassifyResponse
import com.dashingqi.module.wx.net.IWxService
import com.dashingqi.module.wx.net.WxArticleChaptersResponse
import com.dashingqi.module.wx.net.WxArticleListResponse
import com.orhanobut.logger.Logger

/**
 * @author : zhangqi
 * @time : 2020/5/22
 * desc :
 */
class WXFragmentViewModel(application: Application) : BaseViewModel(application) {

    init {
        getWxArticleChapters()
    }

    var mDatas: ArrayList<CommonClassifyResponse> = arrayListOf()
    var wxArticleChapterLiveData = MutableLiveData<List<CommonClassifyResponse>>()

    private fun getWxArticleChapters() {
        IWxService.INSTANCE.getWxArticleChapters().enqueue(LiveDataCallback<WxArticleChaptersResponse>(baseLiveData)
                .bindStateLayout()
                .doOnResponseSuccess { _, response ->
                    Logger.d("wx_article_chapters_size == ${response.data.size}")
                    if (mDatas.size > 0) {
                        mDatas.clear()
                    }
                    mDatas.addAll(response.data)
                    wxArticleChapterLiveData.value = response.data
                }
        )
    }
}