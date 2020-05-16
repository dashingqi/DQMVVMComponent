package com.dashingqi.module.home.modules.main

import android.os.Bundle
import com.dashingqi.base.base.activity.BaseMVVMActivity
import com.dashingqi.module.home.R
import com.dashingqi.module.home.databinding.HomeActivityMainBinding
import com.orhanobut.logger.Logger

class HomeMainActivity : BaseMVVMActivity<HomeActivityMainBinding, HomeViewModel>() {
    private val TAG = "HomeMainActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Logger.d(TAG, "test---main")
    }
}
