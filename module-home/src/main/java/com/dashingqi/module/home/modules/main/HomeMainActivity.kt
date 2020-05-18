package com.dashingqi.module.home.modules.main

import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.dashingqi.base.base.activity.BaseMVVMActivity
import com.dashingqi.base.providers.params.IGlobalParams
import com.dashingqi.module.home.R
import com.dashingqi.module.home.databinding.HomeActivityMainBinding
import com.orhanobut.logger.Logger
import kotlinx.android.synthetic.main.home_activity_main.*

@Route(path = "/home/main_activity")
class HomeMainActivity : BaseMVVMActivity<HomeActivityMainBinding, HomeViewModel>() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Logger.d("test---main")

        var baseUrl = ARouter.getInstance().navigation(IGlobalParams::class.java).getBaseUrl()
        Logger.d("baseUrl == $baseUrl")
        btnClick.setOnClickListener {
            ARouter.getInstance().build("/home/test_activity").navigation()
        }
    }
}
