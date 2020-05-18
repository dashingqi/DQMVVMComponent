package com.dashingqi.mvvmcomponent

import android.app.Application
import com.alibaba.android.arouter.launcher.ARouter
import com.dashingqi.androidmvvmcomponentproject.BuildConfig
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.FormatStrategy
import com.orhanobut.logger.Logger
import com.orhanobut.logger.PrettyFormatStrategy


/**
 * @author : zhangqi
 * @time : 2020/5/16
 * desc :
 */
class AppApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        initARouter()
        //初始化打印
        initLog()

    }

    /**
     * 初始化路由
     */
    private fun initARouter() {
        if (BuildConfig.DEBUG) {
            ARouter.openLog()
            ARouter.openDebug()
        }
        ARouter.init(this)
    }

    /**
     * 初始化日志的打印
     */
    private fun initLog() {
        val formatStrategy: FormatStrategy = PrettyFormatStrategy.newBuilder()
                .showThreadInfo(false)
                .methodCount(0)
                .methodOffset(7)
                .tag("dq-m-v-v-m-component")
                .build()
        Logger.addLogAdapter(AndroidLogAdapter(formatStrategy))
    }
}