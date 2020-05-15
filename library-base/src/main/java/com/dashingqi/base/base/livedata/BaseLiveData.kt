package com.dashingqi.base.base.livedata

import androidx.lifecycle.MutableLiveData
import com.alibaba.android.arouter.facade.Postcard
import com.dashingqi.base.base.callback.CallOwner
import com.dashingqi.base.widget.loading.IStateLayout

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

    val showLoading by lazy {
        MutableLiveData<MutableList<CallOwner>>()
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
        ThreadUtil.runOnUiThread(Runnable {
            stateLayout.value = IStateLayout.STATE_LOADING
        })
    }

    fun switchToSuccess() {

    }
}