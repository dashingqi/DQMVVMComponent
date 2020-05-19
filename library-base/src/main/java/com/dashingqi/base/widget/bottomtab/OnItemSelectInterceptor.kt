package com.dashingqi.base.widget.bottomtab

/**
 * @author : zhangqi
 * @time : 2020/5/19
 * desc :
 */
open interface OnItemSelectInterceptor {
    fun onItemSelect(oldIndex: Int, newIndex: Int): Boolean
}
