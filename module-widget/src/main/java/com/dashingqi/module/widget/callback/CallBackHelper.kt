package com.dashingqi.module.widget.callback

import android.util.Log
import androidx.lifecycle.MutableLiveData

/**
 * @author : zhangqi
 * @time : 2020/9/25
 * desc :
 */
object CallBackHelper {

    val liveData = MutableLiveData<Any>()

    fun doAnything() {
        Log.d(" --------> ", "开始做事情")

        Log.d("--------> ", "获取到结果中")

        liveData.value = "设置一个结果并且返回回去"

        Log.d("-------> ", "结果成功返回")
    }
}