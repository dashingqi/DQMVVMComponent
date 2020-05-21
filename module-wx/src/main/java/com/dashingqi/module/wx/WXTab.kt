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
                "/user/user_fragment",
                R.drawable.res_ic_like_selected,
                R.drawable.res_ic_like,
                R.color.res_colorAccent,
                R.color.res_colorBlack66
        )
    }
}