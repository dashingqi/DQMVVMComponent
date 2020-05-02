package com.dashingqi.library_network.`interface`

/**
 * @author : zhangqi
 * @time : 2020/4/27
 * desc :
 */
interface IResponse {
    /**
     * token失效
     */
    fun isTokenError(): Boolean

    /**
     * 请求成功
     */
    fun isSuccess(): Boolean

    /**
     * 获取到msg
     */
    fun getMessage():String
}