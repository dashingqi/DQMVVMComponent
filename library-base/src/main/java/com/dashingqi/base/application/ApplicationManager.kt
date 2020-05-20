package com.dashingqi.base.application

import com.dashingqi.base.providers.application.IApplicationProvider

/**
 * @author : zhangqi
 * @time : 2020/5/20
 * desc :
 */
class ApplicationManager {
    /**
     * 用来存储实现IApplicationProvider接口的
     */
    @JvmField
    val apps = mutableListOf<IApplicationProvider>()
}