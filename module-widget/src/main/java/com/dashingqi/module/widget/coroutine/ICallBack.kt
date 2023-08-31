package com.dashingqi.module.widget.coroutine

/**
 * @author : zhangqi
 * @time : 6/22/21
 * desc :
 */
interface ICallBack {
    fun onSuccess(data: String)
    fun onFailure(t: Throwable)
}