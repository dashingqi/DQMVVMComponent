package com.dashingqi.module.login.net

import com.alibaba.android.arouter.launcher.ARouter
import com.dashingqi.base.base.response.BaseResponse
import com.dashingqi.base.providers.network.IServiceProvider
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

/**
 * @author : zhangqi
 * @time : 2020/7/26
 * desc :
 */
interface ILoginService {

    /**
     * 登录接口
     */
    @FormUrlEncoded
    @POST("/user/login")
    fun login(@Field("username") userName: String, @Field("password") password: String): Call<LoginResponse>

    /**
     * 注册接口
     */
    @POST("/user/register")
    @FormUrlEncoded
    fun register(@Field("username") userName: String, @Field("password") password: String, @Field("repassword") repassword: String): Call<BaseResponse>

    companion object {
        var instance = ARouter.getInstance().navigation(IServiceProvider::class.java).createService(ILoginService::class.java)
    }
}