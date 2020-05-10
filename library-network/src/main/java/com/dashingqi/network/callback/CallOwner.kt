package com.dashingqi.network.callback

import retrofit2.Call

/**
 * @author : zhangqi
 * @time : 2020/5/10
 * desc :
 */
interface CallOwner {
    fun getCall(): Call<*>?
}