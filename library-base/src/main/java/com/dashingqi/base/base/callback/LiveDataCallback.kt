package com.dashingqi.base.base.callback

import com.dashingqi.base.base.response.IResponse
import com.dashingqi.base.base.livedata.BaseLiveData
import com.orhanobut.logger.Logger
import retrofit2.Call
import retrofit2.Response

/**
 * @author : zhangqi
 * @time : 2020/5/15
 * desc :
 */
class LiveDataCallback<T : IResponse> : BaseCallback<T> {
    /**
     * 用于记录是否调用了bindStateLayout
     */
    private var isBindStateLayout = false

    /**
     * 用于记录是否调用了bindSmartRefresh
     */
    private var isBindSmartRefresh = false

    /**
     * 用于记录是否调用了加载框，一个状态
     */
    private var isBindLoading = false

    /**
     * m 与 v的中转站 ---> BaseViewModel
     */
    private var baseLiveData: BaseLiveData? = null

    init {
        doOnResponseCodeError { _, response ->
            //提示一个toast
        }

        // 请求成功的回调
        doOnResponseSuccess { call, response ->
            //判断当前此次请求是否显示加载布局了
            baseLiveData?.let {
                if (isBindStateLayout) {
                    it.switchToSuccess()
                }

                if (isBindSmartRefresh) {
                    it.finishRefresh()
                    it.finishLoadMoreSuccess()
                }

                if (isBindLoading) {
                    it.hideLoading()
                }
            }

        }

        doOnAnyFail {

            baseLiveData?.let {
                if (isBindStateLayout) {
                    it.switchToError()
                }

                if (isBindSmartRefresh) {
                    //停止下拉刷新的动画
                    it.finishRefresh()
                    it.finishLoadMore()
                }

                if (isBindLoading) {
                    it.hideLoading()
                }
            }
        }

    }

    /**
     * 次构造函数
     */
    constructor() : super()

    constructor(baseLiveData: BaseLiveData) : super() {
        this.baseLiveData = baseLiveData
    }


    /**
     * 用于绑定状态页面
     */
    fun bindStateLayout(): LiveDataCallback<T> {
        Logger.d("bindStateLayout  ---->  transform")
        baseLiveData?.switchToLoading()
        isBindStateLayout = true
        return this
    }

    /**
     * 用于绑定下拉刷新
     */
    fun bindSmartRefresh(): LiveDataCallback<T> {
        Logger.d("bindSmartRefresh  ---->  transform")
        baseLiveData?.startRefresh()
        isBindSmartRefresh = true
        return this
    }

    fun bindLoading(): LiveDataCallback<T> {
        Logger.d("bindLoading -----> transform")
        baseLiveData?.showLoading()
        isBindLoading = true
        return this
    }

    override fun doOnResponse(doOnResponse: (call: Call<T>, response: Response<T>) -> Unit): BaseCallback<T> {
        return super.doOnResponse(doOnResponse) as LiveDataCallback<T>
    }

    /**
     * 高阶函数
     */
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