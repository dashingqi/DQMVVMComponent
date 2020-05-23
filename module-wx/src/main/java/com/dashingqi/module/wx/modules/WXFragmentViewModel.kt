package com.dashingqi.module.wx.modules

import android.app.Application
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import com.dashingqi.base.base.callback.LiveDataCallback
import com.dashingqi.base.base.viewmodel.BaseViewModel
import com.dashingqi.module.wx.net.IWxService
import com.dashingqi.module.wx.net.WxArticleChaptersResponse
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

    var mFragments = ArrayList<Fragment>()
    var wxArticleChapterLiveData = MutableLiveData<List<WxArticleChaptersResponse.DataBean>>()

    private fun getWxArticleChapters() {
        IWxService.INSTANCE.getWxArticleChapters().enqueue(LiveDataCallback<WxArticleChaptersResponse>(baseLiveData)
                .doOnResponseSuccess { _, response ->
                    Logger.d("wx_article_chapters_size == ${response.data.size}")
                    response.data.forEach {
                        mFragments.add(WXArticleChapterFragment())
                    }
                    wxArticleChapterLiveData.value = response.data
                }
        )
    }
}