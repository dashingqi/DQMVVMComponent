package com.dashingqi.module.login

import android.app.Application
import android.graphics.Color
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.dashingqi.base.base.activity.BaseMVVMActivity
import com.dashingqi.base.base.viewmodel.ParamViewModelFactory
import com.dashingqi.base.route.RoutePath
import com.dashingqi.module.login.databinding.LoginActivityLoginBinding

/**
 * 登录
 */
@Route(path = RoutePath.Login.LOGIN_ACTIVITY)
class LoginActivity : BaseMVVMActivity<LoginActivityLoginBinding, LoginViewModel>() {

    @JvmField
    @Autowired(name = "isFinish")
    var isFinish = false

    override fun onCreate(savedInstanceState: Bundle?) {
        ARouter.getInstance().inject(this)
        super.onCreate(savedInstanceState)
    }
    override fun isFitsSystemWindows(): Boolean {
        return true
    }

    override fun setStatusBarColorInt(): Int {
        return Color.WHITE
    }

}
