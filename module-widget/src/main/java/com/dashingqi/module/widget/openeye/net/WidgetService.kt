package com.dashingqi.module.widget.openeye.net

import com.alibaba.android.arouter.launcher.ARouter
import com.dashingqi.base.base.response.BaseResponse
import com.dashingqi.base.providers.network.IServiceProvider
import retrofit2.Call
import retrofit2.http.GET

/**
 * @author : zhangqi
 * @time : 2020/8/26
 * desc :
 */
interface WidgetService {

    /**
     * 发现页面的数据
     */
    @GET("/api/v7/index/tab/discovery")
    fun getDiscoveryData(): Call<BaseResponse>

    companion object {
        var openEyeInstance = ARouter.getInstance().navigation(IServiceProvider::class.java)
                .createService(WidgetService::class.java, " http://baobab.kaiyanapp.com/")
    }
}