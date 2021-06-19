package com.dashingqi.module.widget.net


import com.alibaba.android.arouter.launcher.ARouter
import com.dashingqi.base.base.response.BaseResponse
import com.dashingqi.base.providers.network.IServiceProvider
import com.dashingqi.module.widget.net.response.OpenEyeResponse
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
    fun getDiscoveryData(): Call<OpenEyeResponse>

    /**
     * 通过协程做本次请求
     * @return OpenEyeResponse
     */
    @GET("/api/v7/index/tab/discovery")
    suspend fun getDiscoveryDataCoroutine(): OpenEyeResponse

    /**
     * 推荐页面的数据
     */
    @GET("/api/v5/index/tab/allRec")
    fun getAllRec(): Call<OpenEyeResponse>

    /**
     * 日报页面数据
     */
    @GET("/api/v5/index/tab/feed")
    fun getFeedData(): Call<OpenEyeResponse>

    companion object {
        var openEyeInstance = ARouter.getInstance().navigation(IServiceProvider::class.java)
                .createService(WidgetService::class.java, " http://baobab.kaiyanapp.com/")
    }
}