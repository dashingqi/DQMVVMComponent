package com.dashingqi.project

import com.dashingqi.base.providers.bottomtab.BottomTabProvider
import com.dashingqi.base.widget.bottomtab.BottomBarItemBean

/**
 * @author : zhangqi
 * @time : 2020/5/21
 * desc :
 */
class ProjectTab : BottomTabProvider {
    override fun getBottomBarItemBean(): BottomBarItemBean {
        return BottomBarItemBean(
                "项目",
                60,
                "/user/user_fragment",
                R.drawable.res_ic_like_selected,
                R.drawable.res_ic_like,
                R.color.res_colorAccent,
                R.color.res_colorBlack66
        )
    }
}