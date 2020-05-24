package com.dashingqi.project.net

import com.alibaba.android.arouter.launcher.ARouter
import com.dashingqi.base.providers.network.IServiceProvider
import retrofit2.Call
import retrofit2.http.GET

/**
 * @author : zhangqi
 * @time : 2020/5/25
 * desc :
 */
interface IProjectService {

    /**
     * 获取到项目分类接口
     */
    @GET("/project/tree/json")
    fun getProjectTree(): Call<ProjectTreeResponse>

    companion object {
        val INSTANCE = ARouter.getInstance().navigation(IServiceProvider::class.java).createService(IProjectService::class.java)
    }
}