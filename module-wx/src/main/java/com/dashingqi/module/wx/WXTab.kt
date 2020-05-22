package com.dashingqi.module.wx

import com.dashingqi.base.providers.bottomtab.BottomTabProvider
import com.dashingqi.base.widget.bottomtab.BottomBarItemBean

/**
 * @author : zhangqi
 * @time : 2020/5/21
 * desc :
 */
class WXTab : BottomTabProvider {
    override fun getBottomBarItemBean(): BottomBarItemBean {
        return BottomBarItemBean(
                "公众号",
                80,
                "/wx/wx_fragment",
                R.drawable.wx_icon_selected,
                R.drawable.wx_icon_default,
                R.color.res_colorAccent,
                R.color.res_colorBlack66
        )
    }
}