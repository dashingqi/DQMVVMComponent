package com.dashingqi.module.widget.coroutine

import android.app.Application
import androidx.lifecycle.viewModelScope
import com.dashingqi.base.base.viewmodel.BaseViewModel
import com.dashingqi.module.widget.net.WidgetService
import com.dashingqi.module.widget.net.response.OpenEyeResponse
import io.reactivex.Single
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.suspendCancellableCoroutine
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.concurrent.thread
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

/**
 * 用于做协程测试的ViewModel
 * @author zhangqi61
 * @since 6/19/21
 */
class WidgetCoroutineViewModel(application: Application) : BaseViewModel(application) {
    init {
        getDiscoverDataWithCoroutine()
       // runTaskDefault()
    }

    /**
     * 没有使用协程做网络请求
     */
    fun getDiscoverData() {
        WidgetService.openEyeInstance.getDiscoveryData().enqueue(object : Callback<OpenEyeResponse> {
            override fun onResponse(call: Call<OpenEyeResponse>, response: Response<OpenEyeResponse>) {
                var body = response.body()
            }

            override fun onFailure(call: Call<OpenEyeResponse>, t: Throwable) {
            }
        })
    }

    /**
     * 使用协程做的请求
     */
    fun getDiscoverDataWithCoroutine() {
        try {
            viewModelScope.launch {
                var discoveryDataCoroutine = WidgetService.openEyeInstance.getDiscoveryDataCoroutine()
            }
        } catch (e: Exception) {
        }
    }

    fun useLaunch() {
        launch(success = {
            // doSomething
        }, failure = {
            // handle Exception
        })
    }

    /**
     * 执行耗时操作
     */
    private fun runTask(callback: SingleMethodCallback) {
        thread {
            Thread.sleep(1000)
            callback.onCallBack("result")
        }
    }

    /**
     * 默认的单一方法接口的实现
     */
    private fun runTaskDefault() {
        runTask(object : SingleMethodCallback {
            override fun onCallBack(value: String) {
                TODO("Not yet implemented")
            }
        })
    }

    private fun runTaskSuspend() {
        viewModelScope.launch {
            runTaskWithSuspend()
        }
    }


    /**
     * 使用协程将单一回到函数转成挂起函数
     */
    suspend fun runTaskWithSuspend(): String {
        // suspendCoroutine是一个挂起函数
        return suspendCoroutine { continuation ->
            runTask(object : SingleMethodCallback {
                override fun onCallBack(value: String) {
                    continuation.resume(value)
                }
            })
        }
    }

    /**
     * 模拟一个耗时操作
     */
    private fun request(callback: ICallBack) {
        thread {
            try {
                callback.onSuccess("success")
            } catch (e: Exception) {
                callback.onFailure(e)
            }
        }
    }


    private fun requestDefault() {
        request(object : ICallBack {
            override fun onSuccess(data: String) {
                // doSomething
            }

            override fun onFailure(t: Throwable) {
                // handle Exception
            }

        })
    }

    private suspend fun requestWithSuspend(): String {
        return suspendCancellableCoroutine { cancellableContinuation->
            request(object : ICallBack {
                override fun onSuccess(data: String) {
                    cancellableContinuation.resume(data)
                }

                override fun onFailure(t: Throwable) {
                    cancellableContinuation.resumeWithException(t)
                }
            })
        }
    }

    private fun runRequestSuspend() {

        try {
            viewModelScope.launch {
                requestWithSuspend()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

}