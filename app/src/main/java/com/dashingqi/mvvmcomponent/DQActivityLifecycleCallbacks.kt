package com.dashingqi.mvvmcomponent

import android.app.Activity
import android.app.Application
import android.os.Bundle

/**
 * 全局监听Activity的生命周期方法
 * @author zhangqi61
 * @since 2021/9/28
 */
class DQActivityLifecycleCallbacks(
    private val onActivityCreated: ((activity: Activity, savedInstanceState: Bundle?) -> Unit)? = null,
    private val onActivityStarted: ((activity: Activity) -> Unit)? = null,
    private val onActivityResumed: ((activity: Activity) -> Unit)? = null,
    private val onActivityPaused: ((activity: Activity) -> Unit)? = null,
    private val onActivityStopped: ((activity: Activity) -> Unit)? = null,
    private val onActivitySaveInstanceState: ((activity: Activity, outState: Bundle) -> Unit)? = null,
    private val onActivityDestroyed: ((activity: Activity) -> Unit)? = null

) : Application.ActivityLifecycleCallbacks {

    override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
        onActivityCreated?.invoke(activity, savedInstanceState)
    }


    override fun onActivityStarted(activity: Activity) {
        onActivityStarted?.invoke(activity)
    }

    override fun onActivityResumed(activity: Activity) {
        onActivityResumed?.invoke(activity)
    }

    override fun onActivityPaused(activity: Activity) {
        onActivityPaused?.invoke(activity)
    }

    override fun onActivityStopped(activity: Activity) {
        onActivityStopped?.invoke(activity)
    }

    override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {
        onActivitySaveInstanceState?.invoke(activity, outState)
    }

    override fun onActivityDestroyed(activity: Activity) {
        onActivityDestroyed?.invoke(activity)
    }
}