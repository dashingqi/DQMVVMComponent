package com.dashingqi.module.home.modules.main

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.dashingqi.base.base.callback.LiveDataCallback
import com.dashingqi.base.base.response.BaseResponse
import com.dashingqi.base.base.viewmodel.BaseViewModel
import com.dashingqi.module.home.net.HomeBannerResponse
import com.dashingqi.module.home.net.IHomeService
import com.orhanobut.logger.Logger

/**
 * @author : zhangqi
 * @time : 2020/5/21
 * desc :
 */
class HomeFragmentViewModel(application: Application) : BaseViewModel(application) {

    init {
        getBannerData()
    }

    var bannerData = MutableLiveData<List<HomeBannerResponse.DataBean>>()

    /**
     * 获取到首页Banner数据
     */
    private fun getBannerData() {
        IHomeService.instance.getBannerData().enqueue(LiveDataCallback<HomeBannerResponse>(baseLiveData)
                .doOnResponseSuccess { _, response ->
                    var data = response.data
                    bannerData.value = data
                }
        )
    }
}