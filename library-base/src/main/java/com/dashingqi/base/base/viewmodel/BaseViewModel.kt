package com.dashingqi.base.base.viewmodel

import android.app.Activity
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.dashingqi.base.base.livedata.BaseLiveData
import java.lang.ref.WeakReference


/**
 * @author : zhangqi
 * @time : 2020/4/25
 * desc : BaseViewModel
 * 在Kotlin中，所有新建的类是final类型，如果想要有子类需要使用
 * 关键字open来修饰这个表明这个类可以被继承。
 */
open class BaseViewModel(application: Application) : AndroidViewModel(application) {

    var baseLiveData = BaseLiveData()

    private var mActivityWeakReference: WeakReference<Activity>? = null

    /**
     * 用于绑定到xml布局上的 （BindingAdapter）
     */
    fun getStateLayout() = baseLiveData.stateLayout

    fun getRefreshState() = baseLiveData.smartRefresh

    fun getLoadMoreState() = baseLiveData.smartLoadMore


    /**
     * 设置Activity
     */
    fun setActivity(activity: Activity) {
        mActivityWeakReference = WeakReference(activity)
    }

    /**
     * 获取到Activity
     */
    fun getActivity(): Activity? {
        return mActivityWeakReference?.get()
    }

}