package com.dashingqi.base.base.callback

import retrofit2.Call

/**
 * @author : zhangqi
 * @time : 2020/5/15
 * desc :
 */
interface CallOwner {
    fun getCall(): Call<*>?
}