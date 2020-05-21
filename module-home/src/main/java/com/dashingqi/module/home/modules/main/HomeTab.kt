package com.dashingqi.module.home.modules.main

import com.dashingqi.base.providers.bottomtab.BottomTabProvider
import com.dashingqi.base.widget.bottomtab.BottomBarItemBean
import com.dashingqi.module.home.R

/**
 * @author : zhangqi
 * @time : 2020/5/20
 * desc :
 */
class HomeTab : BottomTabProvider {
    override fun getBottomBarItemBean(): BottomBarItemBean {
        return BottomBarItemBean(
                "测试",
                10,
                "/home/test_activity",
                R.drawable.home_ic_one_selected,
                R.drawable.home_ic_one_un,
                R.color.res_colorAccent,
                R.color.res_colorAccent
        )
    }
}