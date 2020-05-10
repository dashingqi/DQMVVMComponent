package com.dashingqi.network.livedata

import androidx.lifecycle.MutableLiveData
import com.alibaba.android.arouter.facade.Postcard
import com.dashingqi.network.callback.CallOwner

/**
 * @author : zhangqi
 * @time : 2020/5/10
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
}