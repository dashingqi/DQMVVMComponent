package com.dashingqi.module.home.modules.search

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.dashingqi.base.base.callback.LiveDataCallback
import com.dashingqi.base.base.viewmodel.BaseViewModel
import com.dashingqi.module.home.net.HomeArticleListResponse
import com.dashingqi.module.home.net.IHomeService

/**
 * @author : zhangqi
 * @time : 2020/6/15
 * desc :
 */
class HomeSearchViewModel(application: Application) : BaseViewModel(application) {

    val key = MutableLiveData<String>()

    init {

    }

    fun refresh() {
        IHomeService.instance.homeSearch(0, key.value ?: "")
                .enqueue(LiveDataCallback<HomeArticleListResponse>(baseLiveData)
                        .bindStateLayout()
                        .bindSmartRefresh()
                        .doOnResponseSuccess { call, response ->
                        }
                )
    }

}