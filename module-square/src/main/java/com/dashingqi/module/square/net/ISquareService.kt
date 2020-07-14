package com.dashingqi.module.square.net

import com.alibaba.android.arouter.launcher.ARouter
import com.dashingqi.base.providers.network.IServiceProvider
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * @author : zhangqi
 * @time : 2020/7/12
 * desc :
 */
interface ISquareService {


    /**
     * 获取到广场列表的数据
     */
    @GET("/user_article/list/{page}/json")
    fun getUserArticleList(@Path("page") pageNum: Int): Call<SquareListResponse>

    companion object {
        val instance = ARouter.getInstance().navigation(IServiceProvider::class.java).createService(ISquareService::class.java)
    }
}