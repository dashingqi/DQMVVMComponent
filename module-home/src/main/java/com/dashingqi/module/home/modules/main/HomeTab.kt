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
                "主页",
                100,
                "/home/home_fragment",
                R.drawable.home_ic_selected,
                R.drawable.home_ic_default,
                R.color.res_colorAccent,
                R.color.res_colorBlack66

        )
    }
}