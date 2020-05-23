package com.dashingqi.module.wx.net

import com.alibaba.android.arouter.launcher.ARouter
import com.dashingqi.base.providers.network.IServiceProvider
import retrofit2.Call
import retrofit2.http.GET

/**
 * @author : zhangqi
 * @time : 2020/5/23
 * desc :
 */
interface IWxService {

    @GET("/wxarticle/chapters/json")
    fun getWxArticleChapters(): Call<WxArticleChaptersResponse>

    companion object {
        val INSTANCE = ARouter.getInstance().navigation(IServiceProvider::class.java).createService(IWxService::class.java)
    }
}