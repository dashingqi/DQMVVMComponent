package com.dashingqi.base.application

import android.app.Application

/**
 * @author : zhangqi
 * @time : 2020/5/20
 * desc : Application的控制器
 * 主要用来
 *
 */
object ApplicationController {

    @JvmField
    var isDebug = true

    @JvmStatic
    fun init(application: Application, isDebug: Boolean) {
        this.isDebug = isDebug
        for (applicationProvider in ApplicationManager.apps) {
            applicationProvider.init(application)
        }
    }

    /**
     * 用于去执行各个实现了IApplicationProvider类的onCreate方法
     */
    @JvmStatic
    fun transformOnCreate() {
        for (applicationProvider in ApplicationManager.apps) {
            applicationProvider.onCreate()
        }
    }

    @JvmStatic
    fun transformOnLowMemory() {
        for (applicationProvider in ApplicationManager.apps) {
            applicationProvider.onLowMemory()
        }
    }
}