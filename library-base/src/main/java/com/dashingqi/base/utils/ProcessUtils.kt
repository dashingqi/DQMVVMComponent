package com.dashingqi.base.utils

import android.app.ActivityManager
import android.app.Application
import android.content.Context
import android.os.Build
import android.os.Process
import java.lang.reflect.Method

/**
 *
 * @ProjectName: DQMVVMComponent
 * @Package: com.dashingqi.base.utils
 * @ClassName: ProcessUtils
 * @Author: DashingQI
 * @CreateDate: 2020/10/19 10:01 PM
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/10/19 10:01 PM
 * @UpdateRemark:
 * @Version: 1.0
 */
object ProcessUtils {

    /**
     * 通过ActivityManager获取到进程名字
     * 该方法需要跨进程
     * 并且需要循环比对
     * 耗时，耗性能
     */
    fun getCurrentProcessName(context:Context):String?{
        var myPid = Process.myPid()
        var activityManager = context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager

        var runningAppProcesses = activityManager.runningAppProcesses
        if (!runningAppProcesses.isNullOrEmpty()){
            runningAppProcesses.forEach {
                if (it.pid == myPid){
                    return it.processName
                }
            }
        }
        return null
    }

    /**
     * AP28以上才能使用
     * 直接获取到进程名称，不会有跨进程，反射的耗时操作
     */
    fun getCurrentProcessNameOnP():String?{

            return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                Application.getProcessName()
            } else {
                return null
            }
    }



    fun getCurrentProcessNameByAT(): String? {
        var processName: String? = null
        try {
            val declaredMethod: Method = Class.forName("android.app.ActivityThread", false, Application::class.java.classLoader)
                    .getDeclaredMethod("currentProcessName")
            declaredMethod.isAccessible = true
            val invoke: Any = declaredMethod.invoke(null)
            if (invoke is String) {
                processName = invoke
            }
        } catch (e: Throwable) {
            e.printStackTrace()
        }
        return processName
    }

}