package com.dashingqi.base.providers.bottomtab

import com.dashingqi.base.widget.bottomtab.BottomBarItemBean

/**
 * @author : zhangqi
 * @time : 2020/5/19
 * desc :
 */
interface BottomTabProvider {

    fun getBottomBarItemBean(): BottomBarItemBean
}