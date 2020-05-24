package com.dashingqi.module.wx.net

import com.alibaba.android.arouter.launcher.ARouter
import com.dashingqi.base.base.response.BaseResponse
import com.dashingqi.base.providers.network.IServiceProvider
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * @author : zhangqi
 * @time : 2020/5/23
 * desc :
 */
interface IWxService {

    /**
     * 获取公众号列表
     */
    @GET("/wxarticle/chapters/json")
    fun getWxArticleChapters(): Call<WxArticleChaptersResponse>

    /**
     * 获取到某个公众号下的数据
     */
    @GET("/wxarticle/list/{id}/{page}/json")
    fun getWxArticleList(@Path("id") id: Int, @Path("page") pageNO: Int): Call<WxArticleListResponse>

    companion object {
        val INSTANCE = ARouter.getInstance().navigation(IServiceProvider::class.java).createService(IWxService::class.java)
    }
}