package com.dashingqi.module.collect.net

import com.alibaba.android.arouter.launcher.ARouter
import com.dashingqi.base.base.response.BaseResponse
import com.dashingqi.base.providers.network.IServiceProvider
import retrofit2.Call
import retrofit2.http.POST
import retrofit2.http.Path

/**
 * @author : zhangqi
 * @time : 2020/7/25
 * desc :
 */
interface ICollectService {

    /**
     * 收藏文章
     */
    @POST("/lg/collect/{id}/json")
    fun collectArticle(@Path("id") id: String): Call<BaseResponse>

    /**
     * 取消收藏文章
     */
    @POST("/lg/uncollect_originId/{id}/json")
    fun unCollectArticle(@Path("id") id: String): Call<BaseResponse>

    companion object {
        var instance = ARouter.getInstance().navigation(IServiceProvider::class.java).createService(ICollectService::class.java)
    }
}