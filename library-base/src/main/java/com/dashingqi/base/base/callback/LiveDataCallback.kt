package com.dashingqi.base.base.callback

import com.dashingqi.base.base.`interface`.IResponse
import com.dashingqi.base.base.livedata.BaseLiveData
import retrofit2.Call
import retrofit2.Response

/**
 * @author : zhangqi
 * @time : 2020/5/15
 * desc :
 */
class LiveDataCallback<T : IResponse> : BaseCallback<T> {


    private var baseLiveData: BaseLiveData? = null

    init {

    }

    /**
     * 次构造函数
     */
    constructor() : super()

    constructor(baseLiveData: BaseLiveData) : super() {
        this.baseLiveData = baseLiveData
    }


    fun bindStateLayout(): LiveDataCallback<T> {
        baseLiveData?.switchToLoading()
        return this
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