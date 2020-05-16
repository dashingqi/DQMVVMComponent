package com.dashingqi.mvvmcomponent

import android.app.Application
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger

/**
 * @author : zhangqi
 * @time : 2020/5/16
 * desc :
 */
class AppApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        //初始化打印
        Logger.addLogAdapter(AndroidLogAdapter())
    }
}