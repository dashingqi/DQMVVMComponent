package com.dashingqi.base.databinding

import androidx.databinding.BindingAdapter
import com.scwang.smartrefresh.layout.SmartRefreshLayout
import com.scwang.smartrefresh.layout.constant.RefreshState
import java.lang.Exception

/**
 * @author : zhangqi
 * @time : 2020/5/16
 * desc :
 */
object SmartRefreshBindingAdapter {

    @JvmStatic
    @BindingAdapter(value = ["refreshState"], requireAll = false)
    fun setSmartRefreshState(smartRefreshLayout: SmartRefreshLayout, state: Int) {
        if (state == 0) {
            if (smartRefreshLayout.state == RefreshState.Refreshing) {
                smartRefreshLayout.finishRefresh(0, true, null)
            } else {
                //需要封装一下log打印工具
            }
        }
    }

    @JvmStatic
    @BindingAdapter(value = ["refreshListener"], requireAll = false)
    fun setOnRefreshListener(smartRefreshLayout: SmartRefreshLayout, runnable: Runnable) {
        if (runnable != null) {
            smartRefreshLayout.setOnRefreshListener {
                try {
                    runnable.run()
                } catch (e: Exception) {
                    e.printStackTrace()
                }

            }
        }
    }

}