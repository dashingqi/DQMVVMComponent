package com.dashingqi.module.widget.coroutine

import android.app.Application
import androidx.lifecycle.viewModelScope
import com.dashingqi.base.base.viewmodel.BaseViewModel
import com.dashingqi.module.widget.net.WidgetService
import com.dashingqi.module.widget.net.response.OpenEyeResponse
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * 用于做协程测试的ViewModel
 * @author zhangqi61
 * @since 6/19/21
 */
class WidgetCoroutineViewModel(application: Application) : BaseViewModel(application) {
    init {
        getDiscoverDataWithCoroutine()
    }

    /**
     * 没有使用协程做网络请求
     */
    fun getDiscoverData() {
        WidgetService.openEyeInstance.getDiscoveryData().enqueue(object : Callback<OpenEyeResponse> {
            override fun onResponse(call: Call<OpenEyeResponse>, response: Response<OpenEyeResponse>) {
                var body = response.body()
            }
            override fun onFailure(call: Call<OpenEyeResponse>, t: Throwable) {
            }
        })
    }

    /**
     * 使用协程做的请求
     */
    fun getDiscoverDataWithCoroutine() {
        try {
            viewModelScope.launch {
                var discoveryDataCoroutine = WidgetService.openEyeInstance.getDiscoveryDataCoroutine()
            }
        } catch (e: Exception) {
        }

    }

    fun useLaunch(){
        launch(success = {
                 // doSomething
        },failure = {
            // handle Exception
        })
    }
}