package com.dashingqi.module.home.app

import android.app.Application
import android.util.Log
import com.dashingqi.base.providers.application.IApplicationProvider
import com.orhanobut.logger.Logger


/**
 * @author : zhangqi
 * @time : 2020/5/20
 * desc :
 */
class HomeApplication : IApplicationProvider {
    private val TAG = "HomeApplication"
    override fun init(application: Application) {
    }

    override fun onCreate() {
        Log.d(TAG,"Home_OnCreate")
    }

    override fun onLowMemory() {
    }

    override fun onTerminate() {
    }

    override fun getPriority(): Int {
        return 80
    }
}