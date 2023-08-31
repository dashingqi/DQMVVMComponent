package com.dashingqi.base.ext

import androidx.lifecycle.viewModelScope
import com.dashingqi.base.base.viewmodel.BaseViewModel
import com.dashingqi.dqlog.DQLog
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

/**
 * @author : zhangqi
 * @time : 12/14/20
 * desc :
 */


/**
 * 使用协程用于做网络的请求
 */
fun BaseViewModel.request(
        block: suspend () -> Unit
): Job {

    GlobalScope.launch(context = Dispatchers.Main) { }
    return viewModelScope.launch {
        kotlin.runCatching {
            DQLog.d("currentThread == ${Thread.currentThread().name}")
            block()
        }.onSuccess {
            DQLog.d("currentThread == ${Thread.currentThread().name}")
            DQLog.d("请求成功")
            //做一些UI上的操作

        }.onFailure {
            DQLog.d("请求失败")
        }
    }
}