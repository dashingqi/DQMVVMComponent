package com.dashingqi.module.login

import android.graphics.Color
import com.alibaba.android.arouter.facade.annotation.Route
import com.dashingqi.base.base.activity.BaseMVVMActivity
import com.dashingqi.module.login.databinding.LoginActivityLoginBinding

/**
 * 登录
 */
@Route(path = "/login/login_activity")
class LoginActivity : BaseMVVMActivity<LoginActivityLoginBinding, LoginViewModel>() {


    override fun isFitsSystemWindows(): Boolean {
        return true
    }

    override fun setStatusBarColorInt(): Int {
        return Color.WHITE
    }
}
