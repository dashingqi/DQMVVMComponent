package com.dashingqi.network.service

import okhttp3.OkHttpClient
import retrofit2.Retrofit

/**
 * @author : zhangqi
 * @time : 2020/5/18
 * desc :
 */
class ServiceBuilder {

    /**
     * 服务器域名
     */
    private var baseUrl: String? = null

    /**
     * 忽略ssl
     */
    private var ignoreSSL: Boolean = false

    /**
     * 保持Cookie的持久化
     */
    private var keepCookie: Boolean = false

    private var buildOKHttp: ((OkHttpClient.Builder) -> Unit)? = null

    private var buildRetrofit: ((Retrofit.Builder) -> Unit)? = null

    fun setBaseUrl(url: String): ServiceBuilder {
        this.baseUrl = url
        return this
    }

    fun isIgnoreSSL(ignoreSSL: Boolean): ServiceBuilder {
        this.ignoreSSL = ignoreSSL
        return this
    }

    fun setKeepCookie(keepCookie: Boolean): ServiceBuilder {
        this.keepCookie = keepCookie
        return this
    }

    fun setOkHttpBuilder(okBuilder:((OkHttpClient.Builder)->Unit)):ServiceBuilder{
        this.buildOKHttp = okBuilder
        return this
    }

    fun setRetrofitBuilder(builderRetrofit:((Retrofit.Builder)->Unit)):ServiceBuilder{
        this.buildRetrofit = builderRetrofit
        return this
    }

    fun builder(): ServiceController {
        return ServiceController(baseUrl!!, ignoreSSL, keepCookie,buildOKHttp,buildRetrofit)
    }
}