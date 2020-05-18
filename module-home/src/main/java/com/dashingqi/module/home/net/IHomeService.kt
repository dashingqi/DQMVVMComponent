package com.dashingqi.module.home.net

import com.alibaba.android.arouter.launcher.ARouter
import com.dashingqi.base.base.response.BaseResponse
import com.dashingqi.base.providers.network.IServiceProvider
import retrofit2.Call
import retrofit2.http.GET

/**
 * @author : zhangqi
 * @time : 2020/5/18
 * desc :
 */
interface IHomeService {

    @GET("/wxarticle/chapters/json")
    fun getWxArticle(): Call<BaseResponse>


    companion object {
        var instance = ARouter.getInstance().navigation(IServiceProvider::class.java).createService(IHomeService::class.java)
    }
}