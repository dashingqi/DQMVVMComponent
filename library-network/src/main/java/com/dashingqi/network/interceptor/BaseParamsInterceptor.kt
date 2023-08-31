package com.dashingqi.network.interceptor

import okhttp3.*

/**
 * @author : zhangqi
 * @time : 2020/5/18
 * desc : 参数拦截器（主要就是对外提供修改参数的方法）
 * 提供添加头部参数
 */
abstract class BaseParamsInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val body = request.body
        val newBuilder = request.newBuilder()
        var params: MutableMap<String, String>? = null
        if (request.method.equals("GET", false)) {
            //GET请求
            request.url.queryParameterNames
            params = getUrlParams(request.url)
            params = transformParams(params)
            val newUrl = buildNewUrlWithNewParams(request.url, params)
            newBuilder.url(newUrl)
        } else {
            val newFormBody: RequestBody?
            if (body is FormBody) {
                params = LinkedHashMap<String, String>()
                val formBody = body
                for (i in 0 until formBody.size) {
                    params[formBody.name(i)] = formBody.value(i)
                }
                val buildNewBodyWithParams = buildNewBodyWithParams(params)
                newFormBody = buildNewBodyWithParams
            } else {
                newFormBody = body
            }
            newBuilder.method(request.method, newFormBody)
        }

        getAddHeaderParams().forEach {
            newBuilder.addHeader(it.key, it.value)
        }
        return chain.proceed(newBuilder.build())
    }

    /**
     * 从url中获取到请求参数
     */
    private fun getUrlParams(url: HttpUrl): MutableMap<String, String> {
        val params = LinkedHashMap<String, String>()
        url.queryParameterNames.forEach { key ->
            val value = url.queryParameter(key)
            value?.let {
                params[key] = value
            }
        }
        return params
    }

    /**
     * 通过参数构造新的HttpUrl
     */
    private fun buildNewUrlWithNewParams(url: HttpUrl, params: MutableMap<String, String>): HttpUrl {
        val newBuilder = url.newBuilder()
        url.queryParameterNames.forEach {
            newBuilder.removeAllQueryParameters(it)
        }
        params.forEach {
            newBuilder.addQueryParameter(it.key, it.value)
        }
        return newBuilder.build()
    }

    /**
     * 构建新的表单参数
     */
    private fun buildNewBodyWithParams(oldParams: MutableMap<String, String>): FormBody {
        val body = FormBody.Builder()
        val newParams = transformParams(oldParams)
        for ((key, value) in newParams) {
            body.add(key, value)
        }
        return body.build()
    }


    /**
     * 对外提供添加头部参数的方法
     */
    abstract fun getAddHeaderParams(): MutableMap<String, String>

    /**
     * 参数转化
     */
    abstract fun transformParams(old: MutableMap<String, String>): MutableMap<String, String>
}