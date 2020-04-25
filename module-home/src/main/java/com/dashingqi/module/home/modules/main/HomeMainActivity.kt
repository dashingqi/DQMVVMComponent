package com.dashingqi.module.home.modules.main

import com.dashingqi.library_base.base.activity.BaseMVVMActivity
import com.dashingqi.module.home.R
import com.dashingqi.module.home.databinding.HomeActivityMainBinding

class HomeMainActivity : BaseMVVMActivity<HomeActivityMainBinding, HomeViewModel>() {
    override fun getLayoutId() = R.layout.home_activity_main
}
