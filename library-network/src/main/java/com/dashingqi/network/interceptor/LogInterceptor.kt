package com.dashingqi.network.interceptor

import com.dashingqi.dqlog.DQLog
import com.orhanobut.logger.Logger
import okhttp3.logging.HttpLoggingInterceptor

/**
 * @author : zhangqi
 * @time : 2020/5/18
 * desc : 日志打印拦截器
 */
class LogInterceptor : HttpLoggingInterceptor.Logger {
    override fun log(message: String) {
        if (message.isEmpty()) {
            DQLog.eJson("net-component-http", "response is null")
        } else {
            DQLog.eJson("net-component-http", message)
        }
    }
}