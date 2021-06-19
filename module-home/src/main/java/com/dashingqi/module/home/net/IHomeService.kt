package com.dashingqi.module.home.net

import com.alibaba.android.arouter.launcher.ARouter
import com.dashingqi.base.base.response.BaseResponse
import com.dashingqi.base.base.response.ResultResponse
import com.dashingqi.base.providers.network.IServiceProvider
import retrofit2.Call
import retrofit2.http.*

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
     * 协程的方式获取首页Banner数据
     * @return ResultResponse<List<HomeBannerResponse.DataBean>>
     */
    @GET("/banner/json")
    suspend fun getNewBannerData(): ResultResponse<List<HomeBannerResponse.DataBean>>

    /**
     * 获取到首页文章列表数据
     */
    @GET("/article/list/{page}/json")
    fun getHomeArticleList(@Path("page") pageNo: Int): Call<HomeArticleListResponse>

    /**
     * 获取到首页最新项目的列表
     */
    @GET("/article/listproject/{page}/json")
    fun getHomeProjectList(@Path("page") pageNo: Int): Call<HomeProjectListResponse>

    /**
     * 首页搜索布局
     */
    @POST("/article/query/{page}/json")
    fun homeSearch(@Path("page") pageNo: Int, @Query("k") key: String): Call<HomeArticleListResponse>


    /**
     * 每日一问
     */
    @GET("/wenda/list/{pageId}/json ")
    fun getWenDa(@Path("pageId") pageNo: Int): Call<HomeWenDaListResponse>


    companion object {
        var instance = ARouter.getInstance().navigation(IServiceProvider::class.java).createService(IHomeService::class.java)
    }
}