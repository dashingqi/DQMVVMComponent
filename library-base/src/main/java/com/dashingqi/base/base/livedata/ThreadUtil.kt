package com.dashingqi.base.base.livedata

import android.os.Handler
import android.os.Looper

/**
 * @author : zhangqi
 * @time : 2020/5/15
 * desc :
 */
object ThreadUtil {

    private val mainHandler = Handler(Looper.getMainLooper())
    private val isMainThread: Boolean
        get() = Thread.currentThread() == Looper.getMainLooper().thread

    fun runOnUiThread(runnable: Runnable) {
        if (isMainThread) {
            runnable.run()
        } else {
            mainHandler.post(runnable)
        }
    }

    fun postOnUiThread(runnable: Runnable) {
        mainHandler.post(runnable)
    }
}