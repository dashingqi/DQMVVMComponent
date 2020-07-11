package com.dashingqi.module.home.modules.newproject

import com.alibaba.android.arouter.facade.annotation.Route
import com.dashingqi.base.base.fragment.BaseMvvMFragment
import com.dashingqi.base.route.RoutePath
import com.dashingqi.module.home.databinding.HomeNewProjectFragmentBinding

/**
 * @author : zhangqi
 * @time : 2020/7/11
 * desc : 最新项目
 */
@Route(path = RoutePath.Home.NEW_PROJECT)
class HomeNewProjectFragment : BaseMvvMFragment<HomeNewProjectFragmentBinding, HomeNewProjectViewModel>() {
}