package com.dashingqi.module.home.modules.main

import com.alibaba.android.arouter.facade.annotation.Route
import com.dashingqi.base.base.fragment.BaseMvvMFragment
import com.dashingqi.module.home.databinding.HomeFragmentBinding

/**
 * @author : zhangqi
 * @time : 2020/5/21
 * desc :
 */

@Route(path = "/home/home_fragment")
class HomeFragment : BaseMvvMFragment<HomeFragmentBinding, HomeFragmentViewModel>() {
}