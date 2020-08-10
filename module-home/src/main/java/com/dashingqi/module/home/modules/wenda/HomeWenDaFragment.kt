package com.dashingqi.module.home.modules.wenda

import com.alibaba.android.arouter.facade.annotation.Route
import com.dashingqi.base.base.fragment.BaseMvvMFragment
import com.dashingqi.base.route.RoutePath
import com.dashingqi.module.home.databinding.HomeWenDaFragmentBinding

/**
 *
 * @ProjectName: DQMVVMComponent
 * @Package: com.dashingqi.module.home.modules.wenda
 * @ClassName: HomeWenDaFragment
 * @Author: DashingQI
 * @CreateDate: 2020/8/10 10:00 PM
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/8/10 10:00 PM
 * @UpdateRemark:
 * @Version: 1.0  每日一问
 */
@Route(path = RoutePath.Home.WEN_DA)
class HomeWenDaFragment:BaseMvvMFragment<HomeWenDaFragmentBinding,HomeWenDaViewModel>() {
}