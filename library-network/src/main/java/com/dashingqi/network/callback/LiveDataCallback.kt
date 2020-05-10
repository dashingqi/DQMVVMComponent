package com.dashingqi.network.callback

import com.dashingqi.network.`interface`.IResponse
import retrofit2.Call
import retrofit2.Response

/**
 * @author : zhangqi
 * @time : 2020/5/10
 * desc :
 */
class LiveDataCallback<T : IResponse> : BaseCallback<T> {
    /**
     * 次构造函数
     */
    constructor() : super()

    init {

    }

    override fun doOnResponse(doOnResponse: (call: Call<T>, response: Response<T>) -> Unit): BaseCallback<T> {
        return super.doOnResponse(doOnResponse) as LiveDataCallback<T>
    }

    override fun doOnResponseSuccess(doOnResponseSuccess: (call: Call<T>, response: T) -> Unit): BaseCallback<T> {
        return super.doOnResponseSuccess(doOnResponseSuccess) as LiveDataCallback<T>
    }

    override fun doOnResponseSuccessHeader(doOnResponseSuccess: (call: Call<T>, response: T) -> Unit): BaseCallback<T> {
        return super.doOnResponseSuccessHeader(doOnResponseSuccess) as LiveDataCallback<T>
    }

    override fun doOnAnyFail(doOnAnyFail: (call: Call<T>) -> Unit): BaseCallback<T> {
        return super.doOnAnyFail(doOnAnyFail) as LiveDataCallback<T>
    }

    override fun doOnResponseCodeError(doOnResponseCodeError: (call: Call<T>, response: T) -> Unit): BaseCallback<T> {
        return super.doOnResponseCodeError(doOnResponseCodeError) as LiveDataCallback<T>
    }
}