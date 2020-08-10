package com.dashingqi.module.square.modules.main

import com.dashingqi.base.providers.bottomtab.BottomTabProvider
import com.dashingqi.base.route.RoutePath
import com.dashingqi.base.widget.bottomtab.BottomBarItemBean
import com.dashingqi.module.square.R

/**
 *
 * @ProjectName: DQMVVMComponent
 * @Package: com.dashingqi.module.square.modules.main
 * @ClassName: SquareTab
 * @Author: DashingQI
 * @CreateDate: 2020/8/10 11:30 PM
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/8/10 11:30 PM
 * @UpdateRemark:
 * @Version: 1.0
 */
class SquareTab : BottomTabProvider {
    override fun getBottomBarItemBean(): BottomBarItemBean {
        return BottomBarItemBean(
                "广场",
                70,
                RoutePath.Square.SQUARE_LIST,
                R.drawable.square_ic_selected,
                R.drawable.square_ic_default,
                R.color.res_colorAccent,
                R.color.res_colorBlack66

        )
    }
}