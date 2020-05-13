package com.dashingqi.base.widget.loading

/**
 * @author : zhangqi
 * @time : 2020/5/13
 * desc : 加载状态接口
 */
interface IStateLayout {


    val STATE_SUCCESS: Int
        get() = 0

    val STATE_LOADING: Int
        get() = 1

    val STATE_EMPTY: Int
        get() = 2

    val STATE_ERROR: Int
        get() = 3

    /**
     * 空布局
     */
    fun switchToEmptyLayout()

    /**
     * 错误布局
     */
    fun switchToErrorLayout()

    /**
     * 成功
     */
    fun switchToSuccessLayout()

    /**
     * 加载布局
     */
    fun switchToLoadingLayout()


}