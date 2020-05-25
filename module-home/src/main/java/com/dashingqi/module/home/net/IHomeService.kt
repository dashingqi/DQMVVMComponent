package com.dashingqi.module.home.net

import com.alibaba.android.arouter.launcher.ARouter
import com.dashingqi.base.base.response.BaseResponse
import com.dashingqi.base.providers.network.IServiceProvider
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * @author : zhangqi
 * @time : 2020/5/18
 * desc :
 */
interface IHomeService {

    /**
     * 获取到首页Banner数据
     */
    @GET("/banner/json")
    fun getBannerData(): Call<HomeBannerResponse>

    /**
     * 获取到首页文章列表数据
     */
    @GET("/article/list/{page}/json")
    fun getHomeArticleList(@Path("page") pageNo: Int): Call<HomeArticleListResponse>


    companion object {
        var instance = ARouter.getInstance().navigation(IServiceProvider::class.java).createService(IHomeService::class.java)
    }
}