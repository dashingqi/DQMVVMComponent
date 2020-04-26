package com.dashingqi.library_network.callback

import com.dashingqi.library_network.`interface`.IResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


/**
 * @author : zhangqi
 * @time : 2020/4/27
 * desc : 自定义请求回调，用于自定义处理
 * 该层仅仅做业务逻辑的分发，不做界面的处理
 * 待实现
 */
open class BaseCallback<T : IResponse> : Callback<T> {
    override fun onFailure(call: Call<T>, t: Throwable) {
        TODO("Not yet implemented")
    }

    override fun onResponse(call: Call<T>, response: Response<T>) {
        TODO("Not yet implemented")
    }
}