package com.dashingqi.module.home.modules.main

import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.dashingqi.base.base.activity.BaseMVVMActivity
import com.dashingqi.base.base.callback.LiveDataCallback
import com.dashingqi.base.providers.params.IGlobalParams
import com.dashingqi.base.widget.loading.LoadingDialog
import com.dashingqi.module.home.databinding.HomeActivityMainBinding
import com.dashingqi.module.home.net.IHomeService
import kotlinx.android.synthetic.main.home_activity_main.*

@Route(path = "/home/main_activity")
class HomeMainActivity : BaseMVVMActivity<HomeActivityMainBinding, HomeViewModel>() {

    private val loadingDialog by lazy {
        LoadingDialog(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //测试网络请求
        IHomeService.instance.getWxArticle().enqueue(LiveDataCallback())

        btnShowLoading.setOnClickListener {
            loadingDialog.show()
        }
    }
}
