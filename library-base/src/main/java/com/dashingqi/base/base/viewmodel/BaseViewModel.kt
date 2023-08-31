package com.dashingqi.base.base.viewmodel

import android.app.Activity
import android.app.Application
import androidx.fragment.app.Fragment
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.dashingqi.base.base.livedata.BaseLiveData
import com.dashingqi.dqlog.DQLog
import kotlinx.coroutines.launch
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

    private var mFragmentWeakReference: WeakReference<Fragment>? = null

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

    /**
     * 设置Fragment
     */
    fun setFragment(fragment: Fragment) {
        mFragmentWeakReference = WeakReference(fragment)
    }

    /**
     * 获取到Fragment
     */
    fun getFragment(): Fragment? {
        return mFragmentWeakReference?.get()
    }

    /**
     * BaseViewModel中协程的封装
     * @param success SuspendFunction0<Unit> 成功的的高阶函数
     * @param failure Function1<Exception, Unit> 失败的高阶函数
     * @param complete Function0<Unit> 完成的高阶函数 对于本次请求结尾的工作
     */
    open fun launch(success: suspend () -> Unit, failure: (Exception) -> Unit, complete: (() -> Unit)? = null) {
        viewModelScope.launch {
            try {
                DQLog.d("perform launch success ")
                success.invoke()
            } catch (exception: Exception) {
                DQLog.d("perform launch failure ")
                failure.invoke(exception)
            } finally {
                complete?.let {
                    DQLog.d("perform launch success ")
                    it.invoke()
                }
            }
        }
    }

}