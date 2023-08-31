package com.dashingqi.mvvmcomponent

import android.app.Application
import android.os.SystemClock
import android.util.Log
import com.alibaba.android.arouter.launcher.ARouter
import com.dashingqi.base.application.ApplicationController
import com.dashingqi.base.utils.ProcessUtils
import com.dashingqi.dqlog.DQLog
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.FormatStrategy
import com.orhanobut.logger.Logger
import com.orhanobut.logger.PrettyFormatStrategy
import java.time.LocalDate


/**
 * @author : zhangqi
 * @time : 2020/5/16
 * desc :
 */
class AppApplication : Application() {
    private val TAG = "AppApplication"

    init {
        //初始化打印
        //initLog()
        ApplicationController.init(this, true)
    }

    private val activityLifecycleCallbacks = DQActivityLifecycleCallbacks(
        onActivityCreated = { activity, savedInstanceState ->
            DQLog.i("${activity::class.java.name} ---> perform onCreate()")
        },
        onActivityStarted = { activity ->
            DQLog.i("${activity::class.java.name} ---> perform onStart()")
        },
        onActivityResumed = { activity ->
            DQLog.i("${activity::class.java.name} ---> perform onResume()")
        },
        onActivityPaused = { activity ->
            DQLog.i("${activity::class.java.name} ---> perform onPause()")
        },
        onActivityStopped = { activity ->
            DQLog.i("${activity::class.java.name} ---> perform onStop()")
        },
        onActivityDestroyed = { activity ->
            DQLog.i("${activity::class.java.name} ---> perform onDestroy()")
        }
    )

    override fun onCreate() {
        super.onCreate()

        initARouter()
        ApplicationController.transformOnCreate()
        initTest()
        registerActivityLifecycleCallbacks(activityLifecycleCallbacks)

    }

    /**
     * 在模拟器上，在整个应用退出的时候会回调这个方法
     * 在真机上，整个应用退出的时候，是不会回调这个方法的。
     */
    override fun onTerminate() {
        super.onTerminate()
    }

    /**
     *当手机的内存存在紧张的时候，
     * 系统中进程的等级（前台--> 后台进程），通过AMS 给进程打个分，然后在linux中去做回收
     * 被回收的进程，在杀死的时候，会去回调这个方法
     */
    override fun onLowMemory() {
        ApplicationController.transformOnLowMemory()
        super.onLowMemory()
    }

    override fun onTrimMemory(level: Int) {
        super.onTrimMemory(level)
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
     * 测试代码
     */
    private fun initTest() {
        val nameStart = SystemClock.elapsedRealtimeNanos()
        var name = ProcessUtils.getCurrentProcessName(this)
        Log.d(TAG, "getCurrentProcessName cost == ${SystemClock.elapsedRealtimeNanos() - nameStart}")

        val pNameStart = SystemClock.elapsedRealtimeNanos()
        var pName = ProcessUtils.getCurrentProcessNameOnP()
        Log.d(TAG, "getCurrentProcessNameOnP cost == ${SystemClock.elapsedRealtimeNanos() - pNameStart}")

        val atNameStart = SystemClock.elapsedRealtimeNanos()
        var atName = ProcessUtils.getCurrentProcessNameByAT()
        Log.d(TAG, "getCurrentProcessNameByAT cost == ${SystemClock.elapsedRealtimeNanos() - atNameStart}")

        if (ProcessUtils.getCurrentProcessName(this) == BuildConfig.APPLICATION_ID) {
            Log.d(TAG, "init main application")
        }
    }
}