package com.dashingqi.base.base.response

/**
 * @author : zhangqi
 * @time : 2020/5/2
 * desc : 响应的Response
 */
open class BaseResponse : IResponse {
    /**
     *  error 和 msg这两个字段看接口文档 这里只是做了一个假设
     */
    open var errorCode: Int = 0
    open var errorMsg: String = ""
    override fun isTokenError(): Boolean = errorCode == -1001
    override fun isSuccess(): Boolean = errorCode == 0
    override fun getMessage(): String = errorMsg
}