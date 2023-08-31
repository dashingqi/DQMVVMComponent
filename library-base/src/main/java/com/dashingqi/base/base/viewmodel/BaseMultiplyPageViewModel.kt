package com.dashingqi.base.base.viewmodel

import android.app.Application
import androidx.databinding.ObservableArrayList

/**
 * @author : zhangqi
 * @time : 2020/8/26
 * desc : 多type item的ViewModel
 */
 abstract class BaseMultiplyPageViewModel<T>(application: Application):BaseViewModel(application) {

    val items  = ObservableArrayList<T>()

    open fun getPageSize() = 20

    open fun getStartPageNum() = 0



    fun refresh(){
        requestData(getStartPageNum())
    }


    /**
     * 子类用于实现请求数据
     */
    abstract fun requestData(page: Int)

    /**
     * 加载更多
     */
    fun loadMore() {
        requestData(items.size / getPageSize() + getStartPageNum())
    }

    /**
     * 处理数据的
     */
    fun handleItemData(page: Int, data: List<T>) {
        /**
         * 当是下拉刷新的话
         */
        if (page == getStartPageNum()) {
            items.clear()
        }
        items.addAll(data)
        /**
         * 当第一次请求没有数据的时候，就显示空数据页面
         */
        if (items.isEmpty()) {
            baseLiveData.switchEmpty()
        }

        /**
         * 上滑加载到最后一页了。
         */
        if (data.size<getPageSize()) {
            baseLiveData.finishLoadMoreWithNoMoreData()
        }
    }
}