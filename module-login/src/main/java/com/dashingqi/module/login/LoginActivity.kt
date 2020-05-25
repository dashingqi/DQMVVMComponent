package com.dashingqi.module.login

import com.alibaba.android.arouter.facade.annotation.Route
import com.dashingqi.base.base.activity.BaseMVVMActivity
import com.dashingqi.module.login.databinding.LoginActivityLoginBinding

/**
 * 登录
 */
@Route(path = "/login/login_activity")
class LoginActivity : BaseMVVMActivity<LoginActivityLoginBinding, LoginViewModel>() {

}
