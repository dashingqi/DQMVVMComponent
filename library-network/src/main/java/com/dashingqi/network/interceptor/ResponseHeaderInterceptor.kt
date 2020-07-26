package com.dashingqi.network.interceptor

import com.alibaba.android.arouter.launcher.ARouter
import com.dashingqi.library.service.providers.user.UserService
import com.orhanobut.logger.Logger
import okhttp3.Interceptor
import okhttp3.Response
import java.lang.StringBuilder

/**
 * @author : zhangqi
 * @time : 2020/7/26
 * desc : 获取到相应头的拦截器
 */
class ResponseHeaderInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {

        var request = chain.request()
        var encodedPath = request.url.encodedPath
        var response = chain.proceed(chain.request())

        if (encodedPath.contains("/user/login")) {
            if (response.headers("set-cookie").isNotEmpty()) {
                var cookies = response.headers("set-cookie")
                var cookiesStr = handleCookies(cookies)
                ARouter.getInstance().navigation(UserService::class.java).setToken(cookiesStr)
                var token = ARouter.getInstance().navigation(UserService::class.java).getToken()
                Logger.d("token ---> $token")
            }
        }

        return response
    }

    /**
     * 处理cookies
     */
    private fun handleCookies(cookies: List<String>): String {
        val sb = StringBuilder()
        for (index in cookies.indices) {
            var cookie = cookies[index].split(";")[0]
            Logger.d("cookie ----> $cookie")
            sb.append(cookie)
            if (index < cookies.size - 1) {
                sb.append(";")
            }
        }
        Logger.d("sb -----> ${sb.toString()}")
        return sb.toString()
    }
}