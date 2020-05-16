package com.dashingqi.base.base.callback

import com.dashingqi.base.base.response.IResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * @author : zhangqi
 * @time : 2020/4/27
 * desc : 自定义请求回调，用于自定义处理
 * 该层仅仅做业务逻辑的分发，不做界面的处理
 * 待实现
 * onResponse
 *   - body为空的情况
 *   - code不是200的情况
 *   - token失效的情况
 *   - 成功的情况
 *   - 执行过程中发生了异常的处理
 * onFailure
 */
open class BaseCallback<T : IResponse> : Callback<T> {
    override fun onFailure(call: Call<T>, t: Throwable) {
        doOnAnyFailContainer.forEach { it.invoke(call) }
    }

    override fun onResponse(call: Call<T>, response: Response<T>) {
        try {
            doOnResponseContainer.forEach { it.invoke(call, response) }

            if (response.code() != 200) {
                doOnAnyFailContainer.forEach { it.invoke(call) }
                onHttpCodeError(call, response)
                return
            }

            var body = response.body()
            if (body == null) {
                doOnAnyFailContainer.forEach { it.invoke(call) }
                onResponseFailureBodyNull(call, response)
                return
            }

            when {
                body.isSuccess() -> {
                    doOnResponseSuccessContainer.forEach { it.invoke(call, body) }
                }

                body.isTokenError() -> {
                    doOnAnyFailContainer.forEach { it.invoke(call) }
                    doOnResponseCodeErrorContainer.forEach { it.invoke(call, body) }
                    onResponseLoginStatusError(body)
                    return
                }

                else -> {

                }
            }
        } catch (t: Throwable) {
            onFailure(call, t)
        }
    }

    protected open fun onHttpCodeError(call: Call<T>, response: Response<T>) {}
    protected open fun onResponseFailureBodyNull(call: Call<T>, response: Response<T>) {}

    /**
     * 登陆的Token失效
     */
    protected open fun onResponseLoginStatusError(body: T) {

    }

    private val doOnResponseContainer = ArrayList<(call: Call<T>, response: Response<T>) -> Unit>()
    open fun doOnResponse(doOnResponse: (call: Call<T>, response: Response<T>) -> Unit): BaseCallback<T> {
        doOnResponseContainer.add(doOnResponse)
        return this
    }

    private val doOnResponseSuccessContainer = ArrayList<(call: Call<T>, response: T) -> Unit>()
    open fun doOnResponseSuccess(doOnResponseSuccess: (call: Call<T>, response: T) -> Unit): BaseCallback<T> {
        doOnResponseSuccessContainer.add(doOnResponseSuccess)
        return this
    }

    open fun doOnResponseSuccessHeader(doOnResponseSuccess: (call: Call<T>, response: T) -> Unit): BaseCallback<T> {
        doOnResponseSuccessContainer.add(0, doOnResponseSuccess)
        return this
    }

    private val doOnAnyFailContainer = ArrayList<(call: Call<T>) -> Unit>()
    open fun doOnAnyFail(doOnAnyFail: (call: Call<T>) -> Unit): BaseCallback<T> {
        doOnAnyFailContainer.add(doOnAnyFail)
        return this
    }

    private val doOnResponseCodeErrorContainer = ArrayList<(call: Call<T>, response: T) -> Unit>()
    open fun doOnResponseCodeError(doOnResponseCodeError: (call: Call<T>, response: T) -> Unit): BaseCallback<T> {
        doOnResponseCodeErrorContainer.add(doOnResponseCodeError)
        return this
    }
}