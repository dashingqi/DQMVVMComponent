package com.dashingqi.base.application

import com.dashingqi.base.providers.application.IApplicationProvider

/**
 * @author : zhangqi
 * @time : 2020/5/20
 * desc :
 */
object ApplicationManager {
    /**
     * 用来存储实现IApplicationProvider接口的
     */
    @JvmStatic
    val apps = mutableListOf<IApplicationProvider>()

    /**
     * 用来注册实现IApplicationProvider的接口的类
     */
    @JvmStatic
    fun register(applicationProvider: IApplicationProvider) {
        apps.add(applicationProvider)
    }
}