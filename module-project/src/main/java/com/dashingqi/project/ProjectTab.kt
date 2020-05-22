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
                "/project/project_fragment",
                R.drawable.project_icon_selected,
                R.drawable.project_icon_default,
                R.color.res_colorAccent,
                R.color.res_colorBlack66
        )
    }
}