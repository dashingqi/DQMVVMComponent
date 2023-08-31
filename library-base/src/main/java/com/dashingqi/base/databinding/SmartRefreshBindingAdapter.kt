package com.dashingqi.base.databinding

import androidx.databinding.BindingAdapter
import com.dashingqi.base.constant.SmartRefreshEvent
import com.orhanobut.logger.Logger
import com.scwang.smart.refresh.layout.SmartRefreshLayout
import com.scwang.smart.refresh.layout.constant.RefreshState
import java.lang.Exception

/**
 * @author : zhangqi
 * @time : 2020/5/16
 * desc :
 */
object SmartRefreshBindingAdapter {

    /**
     * 下拉刷新的状态
     */
    @JvmStatic
    @BindingAdapter(value = ["refreshState"], requireAll = false)
    fun setSmartRefreshState(smartRefreshLayout: SmartRefreshLayout, state: Int) {
        if (state == 0) {
            if (smartRefreshLayout.state == RefreshState.Refreshing) {
                Logger.d("smart_refresh_finish_success")
                smartRefreshLayout.finishRefresh(0, true, null)
            } else {
                //需要封装一下log打印工具
                Logger.d("smart_refresh_finish_failed")
            }
        }
    }

    /**
     * 下拉刷新的事件
     */
    @JvmStatic
    @BindingAdapter(value = ["refreshListener"], requireAll = false)
    fun setOnRefreshListener(smartRefreshLayout: SmartRefreshLayout, runnable: Runnable) {
        if (runnable != null) {
            smartRefreshLayout.setOnRefreshListener {
                try {
                    Logger.d("smart_refresh -----> transform")
                    runnable.run()
                } catch (e: Exception) {
                    e.printStackTrace()
                }

            }
        }
    }

    /**
     * 加载更多的状态
     */
    @JvmStatic
    @BindingAdapter(value = ["loadMoreState"], requireAll = false)
    fun setLoadMoreState(smartRefreshLayout: SmartRefreshLayout, loadMoreState: Int) {
        when (loadMoreState) {
            SmartRefreshEvent.SMART_REFRESH_LAYOUT_LOAD_MORE_FINISH -> {
                smartRefreshLayout.resetNoMoreData()
                //结束加载更多
                smartRefreshLayout.finishLoadMore()
            }

            SmartRefreshEvent.SMART_REFRESH_LAYOUT_LOAD_MORE_FINISH_AND_NO_MORE -> {
                //结束加载更多，并且无更多数据
                smartRefreshLayout.finishLoadMoreWithNoMoreData()
            }

            SmartRefreshEvent.SMART_REFRESH_LAYOUT_LOAD_MORE_FINISH_SUCCESS -> {
                smartRefreshLayout.resetNoMoreData()
                //结束加载更多
                smartRefreshLayout.finishLoadMore()
            }
        }
    }

    @JvmStatic
    @BindingAdapter(value = ["loadMoreListener"], requireAll = false)
    fun setLoadMoreListener(smartRefreshLayout: SmartRefreshLayout, runnable: Runnable) {
        if (runnable != null) {
            smartRefreshLayout.setOnLoadMoreListener {
                try {
                    Logger.d("smart_refresh ----> transform")
                    runnable.run()
                } catch (exception: Exception) {
                    exception.printStackTrace()
                }
            }
        }
    }

    /**
     * 设置是否能够下拉刷新
     */
    @JvmStatic
    @BindingAdapter(value = ["enableRefresh"], requireAll = true)
    fun setCanRefresh(smartRefreshLayout: SmartRefreshLayout, enableRefresh: Boolean) {
        smartRefreshLayout.setEnableRefresh(enableRefresh)
    }


    /**
     * 设置是否能够加载更多
     */
    @JvmStatic
    @BindingAdapter(value = ["enableCanLoadMore"], requireAll = true)
    fun setCanLoadMore(smartRefreshLayout: SmartRefreshLayout, enableLoadMore: Boolean) {
        smartRefreshLayout.setEnableLoadMore(enableLoadMore)
    }
}