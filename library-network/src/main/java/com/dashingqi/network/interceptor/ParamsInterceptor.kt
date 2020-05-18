package com.dashingqi.network.interceptor

/**
 * @author : zhangqi
 * @time : 2020/5/18
 * desc :  参数拦截器
 */
class ParamsInterceptor : BaseParamsInterceptor() {
    override fun getAddHeaderParams(): MutableMap<String, String> {
        val headerParams = mutableMapOf<String, String>()
        headerParams?.let {
            //TODO 需要完善头部公共请求参数
        }
        return headerParams
    }

    override fun transformParams(old: MutableMap<String, String>): MutableMap<String, String> {
        return old
    }
}