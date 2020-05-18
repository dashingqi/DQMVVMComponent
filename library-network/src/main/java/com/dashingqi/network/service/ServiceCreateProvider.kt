package com.dashingqi.network.service

import android.content.Context
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.dashingqi.base.providers.network.IServiceProvider
import com.dashingqi.base.providers.params.IGlobalParams
import com.dashingqi.network.interceptor.LogInterceptor
import com.dashingqi.network.interceptor.ParamsInterceptor
import okhttp3.logging.HttpLoggingInterceptor

/**
 * @author : zhangqi
 * @time : 2020/5/17
 * desc :
 */
@Route(path = "/network/IServiceProvider", name = "Service创建模块入口")
class ServiceCreateProvider : IServiceProvider {

    private val serviceController by lazy {
        //TODO 需要通过ARouter拿到BaseUrl
       val baseUrl =  ARouter.getInstance().navigation(IGlobalParams::class.java).getBaseUrl()
        createServiceController(baseUrl)
    }

    override fun init(context: Context?) {
    }

    override fun <T> createService(cla: Class<T>): T {
        return serviceController.createService(cla)
    }

    override fun <T> createService(cla: Class<T>, baseUrl: String): T {
        return createServiceController(baseUrl).createService(cla)
    }

    private fun createServiceController(baseUrl: String): ServiceController {
        return ServiceBuilder()
                .setBaseUrl(baseUrl)
                .isIgnoreSSL(true)
                .setOkHttpBuilder {
                    //添加参数拦截器拦截器
                    it.addInterceptor(ParamsInterceptor())
                    //添加打印拦截器
                    it.addInterceptor(HttpLoggingInterceptor(LogInterceptor())
                            .setLevel(HttpLoggingInterceptor.Level.BODY))

                    //失败重联
                    it.retryOnConnectionFailure(true)
                }
                .builder()
    }
}