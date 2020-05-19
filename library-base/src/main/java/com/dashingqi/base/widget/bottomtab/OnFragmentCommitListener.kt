package com.dashingqi.base.widget.bottomtab

import androidx.fragment.app.Fragment

/**
 * @author : zhangqi
 * @time : 2020/5/19
 * desc :
 */
open interface OnFragmentCommitListener<T> {
    /**
     * frament创建之后，提交之前调用
     *
     * @param tag
     * @param fragment
     */
    fun onFragmentCommit(tag: T, fragment: Fragment?)
}