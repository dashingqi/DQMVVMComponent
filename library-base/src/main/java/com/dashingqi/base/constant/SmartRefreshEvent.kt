package com.dashingqi.base.constant

/**
 * @author : zhangqi
 * @time : 2020/5/16
 * desc :
 */
object SmartRefreshEvent {

    /**
     * 加载更多
     */
    const val SMART_REFRESH_LAYOUT_LOAD_MORE_FINISH= 0

    /**
     * 加载更多结束，并且成功
     */
    const val SMART_REFRESH_LAYOUT_LOAD_MORE_FINISH_SUCCESS = 1

    /**
     * 加载更多结束，并且没有更多数据了
     */
    const val SMART_REFRESH_LAYOUT_LOAD_MORE_FINISH_AND_NO_MORE = 2
}