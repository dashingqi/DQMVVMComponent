package com.dashingqi.base.widget.bottomtab

import androidx.fragment.app.Fragment

/**
 * @author : zhangqi
 * @time : 2020/5/19
 * desc :
 */
interface FragmentCreator<T> {
    /**
     * 创建fragment
     * @param tag
     * @return
     */
    fun create(tag: T): Fragment?
}