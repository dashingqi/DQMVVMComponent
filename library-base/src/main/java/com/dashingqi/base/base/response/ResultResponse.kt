package com.dashingqi.base.base.response

/**
 * 新的Response
 * @author zhangqi61
 * @since 6/19/21
 */
class ResultResponse<T> : IResponse {
    open var errorCode: Int = 0
    open var errorMsg: String = ""
    var data: T? = null
    override fun isTokenError(): Boolean = errorCode == -1001

    override fun isSuccess(): Boolean = errorCode == 0

    override fun getMessage(): String = errorMsg
}