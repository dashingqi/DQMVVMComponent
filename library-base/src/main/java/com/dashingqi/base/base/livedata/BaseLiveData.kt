package com.dashingqi.base.base.livedata

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import com.alibaba.android.arouter.facade.Postcard
import com.dashingqi.base.base.callback.CallOwner
import com.dashingqi.base.base.observer.BaseLiveDataObserver
import com.dashingqi.base.constant.SmartRefreshEvent
import com.dashingqi.base.widget.state.IStateLayout
import com.orhanobut.logger.Logger

/**
 * @author : zhangqi
 * @time : 2020/5/15
 * desc :
 */
class BaseLiveData {
    val finishLiveData by lazy {
        LostMutableLiveData<Int>()
    }

    val finishWithData by lazy {
        LostMutableLiveData<FinishData>()
    }

    val startActivityLiveData by lazy {
        LostMutableLiveData<Postcard>()
    }

    val messageLiveData by lazy {
        LostMutableLiveData<String>()
    }

    /**
     * 控制显示加载框
     */
     val showLoading by lazy {
        MutableLiveData<MutableList<Cancelable>>()
    }

    val smartRefresh by lazy {
        MutableLiveData<Int>()
    }

    val smartLoadMore by lazy {
        MutableLiveData<Int>()
    }

    val stateLayout by lazy {
        MutableLiveData<Int>()
    }

    /**
     * StateLayout 切换到空布局
     */
    fun switchEmpty() {

    }

    /**
     * StateLayout 切换到错误布局
     */
    fun switchToError() {

    }

    /**
     * StateLayout 切换到加载布局中
     */
    fun switchToLoading() {
        Logger.d("switchToLoading -----> transform")
        ThreadUtil.runOnUiThread(Runnable {
            stateLayout.value = IStateLayout.STATE_LOADING
        })
    }

    /**
     * StateLayout 切换到成功
     */
    fun switchToSuccess() {
        Logger.d("switchToSuccess -----> transform")
        ThreadUtil.runOnUiThread(Runnable {
            stateLayout.value = IStateLayout.STATE_SUCCESS
        })
    }

    /**
     * 开始刷新
     */
    fun startRefresh() {
        ThreadUtil.runOnUiThread(Runnable {
            if (smartRefresh.value == null) {
                smartRefresh.value = 1
            } else {
                smartRefresh.value = (smartRefresh.value!! + 1)
            }
        })
    }

    /**
     * 通知停止刷新
     */
    fun finishRefresh() {
        ThreadUtil.runOnUiThread(Runnable {
            if (smartRefresh.value == null) {
                smartRefresh.value = (0)
            } else {
                smartRefresh.value = (smartRefresh.value!! - 1)
            }
        })
    }

    /**
     * 结束刷新
     * 是在网络回调出现问题的时候调用
     */
    fun finishLoadMore() {
        Logger.d("finishLoadMore ---> transform")
        smartLoadMore.postValue(SmartRefreshEvent.SMART_REFRESH_LAYOUT_LOAD_MORE_FINISH)
    }

    /**
     * 通知开始弹出加载框
     */
    fun showLoading(cancelable: Cancelable) {
        Logger.d("showLoading ----> transform")
        ThreadUtil.runOnUiThread(Runnable {
            var value = showLoading.value
            if (value == null) {
                value = ArrayList<Cancelable>()
                value.add(cancelable)
            } else {
                value.add(cancelable)
            }
            showLoading.value = value
        })

    }

    /**
     * 通知隐藏加载框
     */
    fun hideLoading(cancelable: Cancelable) {
        Logger.d("hideLoading -----> transform")
        ThreadUtil.runOnUiThread(Runnable {
            var value = showLoading.value
            if (value == null) {
                value = ArrayList()
            } else {
                value.remove(cancelable)
            }

            showLoading.value = value
        })
    }

    /**
     * 是在网络请求回调成功的时候调用
     */
    fun finishLoadMoreSuccess() {
        Logger.d("finishLoadMoreSuccess ---> transform")
        smartLoadMore.postValue(SmartRefreshEvent.SMART_REFRESH_LAYOUT_LOAD_MORE_FINISH_SUCCESS)
    }

    /**
     * 是在加载最后一页数据的时候，进行的回调
     */
    fun finishLoadMoreWithNoMoreData() {
        Logger.d("finishLoadMoreWithNoMoreData ---> transform")
        smartLoadMore.postValue(SmartRefreshEvent.SMART_REFRESH_LAYOUT_LOAD_MORE_FINISH_AND_NO_MORE)
    }

    fun attach(owner: LifecycleOwner, activity: Activity): BaseLiveDataObserver {
        return BaseLiveDataObserver(this, owner, activity)
    }

    fun attach(activity: AppCompatActivity): BaseLiveDataObserver {
        return BaseLiveDataObserver(this, activity, activity)
    }

    fun attach(fragment: Fragment): BaseLiveDataObserver {
        return BaseLiveDataObserver(this, fragment)
    }

    /**
     * 通知当前的Activity结束
     */
    fun finish() {
        finishLiveData.postValue(Activity.RESULT_CANCELED)
    }
}