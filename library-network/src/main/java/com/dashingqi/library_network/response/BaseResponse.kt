package com.dashingqi.library_network.response

import com.dashingqi.library_network.`interface`.IResponse

/**
 * @author : zhangqi
 * @time : 2020/5/2
 * desc : 响应的Response
 */
class BaseResponse : IResponse {
    /**
     *  error 和 msg这两个字段看接口文档 这里只是做了一个假设
     */
    open var error: Int = 0
    open var msg: String = ""
    override fun isTokenError(): Boolean = error == 600500
    override fun isSuccess(): Boolean = error == 0
    override fun getMessage(): String = msg
}