package com.dashingqi.network.service

import android.content.Context
import com.alibaba.android.arouter.facade.annotation.Route
import com.dashingqi.base.providers.network.IServiceProvider
import com.dashingqi.network.interceptor.LogInterceptor
import okhttp3.logging.HttpLoggingInterceptor

/**
 * @author : zhangqi
 * @time : 2020/5/17
 * desc :
 */
@Route(path = "/network/IServiceProvider", name = "Service创建模块入口")
class ServiceCreateProvider : IServiceProvider {
    override fun init(context: Context?) {
    }

    override fun <T> createService(cla: Class<T>): T {
        TODO("Not yet implemented")
    }

    override fun <T> createService(cla: Class<T>, baseUrl: String): T {
        TODO("Not yet implemented")
    }

    private fun createServiceController(baseUrl: String): ServiceController {
        return ServiceBuilder()
                .setBaseUrl(baseUrl)
                .isIgnoreSSL(true)
                .setOkHttpBuilder {
                    //添加头部拦截器

                    //添加打印拦截器
                    it.addInterceptor(HttpLoggingInterceptor(LogInterceptor())
                            .setLevel(HttpLoggingInterceptor.Level.BODY))

                    //失败重联
                    it.retryOnConnectionFailure(true)
                }
                .builder()
    }
}