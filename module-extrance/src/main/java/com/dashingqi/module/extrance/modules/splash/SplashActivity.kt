package com.dashingqi.module.extrance.modules.splash


import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import com.alibaba.android.arouter.launcher.ARouter
import com.dashingqi.base.base.activity.BaseMVVMActivity
import com.dashingqi.base.route.RoutePath
import com.dashingqi.module.extrance.databinding.ExtranceActivitySplashBinding

class SplashActivity : BaseMVVMActivity<ExtranceActivitySplashBinding, SplashViewModel>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var handler = Handler()
        handler.postDelayed(Runnable {
            ARouter.getInstance().build(RoutePath.Home.HOME_ACTIVITY).withBoolean("isFinish", false).navigation()
            finish()
        }, 2000)
    }

    override fun isFitsSystemWindows(): Boolean {
        return true
    }

    override fun setStatusBarColorInt(): Int {
        return Color.WHITE
    }


}