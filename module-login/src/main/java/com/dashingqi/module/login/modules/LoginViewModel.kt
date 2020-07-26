package com.dashingqi.module.login.modules

import android.app.Application
import androidx.databinding.ObservableField
import com.dashingqi.base.base.callback.LiveDataCallback
import com.dashingqi.base.base.response.BaseResponse
import com.dashingqi.base.base.viewmodel.BaseViewModel
import com.dashingqi.base.utils.toast
import com.dashingqi.module.login.net.ILoginService
import com.dashingqi.module.login.net.LoginResponse
import com.orhanobut.logger.Logger

/**
 * @author : zhangqi
 * @time : 2020/5/21
 * desc :
 */
class LoginViewModel(application: Application) : BaseViewModel(application) {

    /**
     * 用户名
     */
    val userNameField = ObservableField<String>()

    /**
     * 密码
     */
    val passwordField = ObservableField<String>()

    fun loginAction() {
        Logger.d("userName -->${userNameField.get()}")
        Logger.d("password ---> ${passwordField.get()}")
        if (userNameField.get().isNullOrEmpty()) {
            toast("请输入用户名")
            return
        }

        if (passwordField.get().isNullOrEmpty()) {
            toast("请输入密码")
            return
        }

        ILoginService.instance.login(userNameField.get()!!, passwordField.get()!!).enqueue(LiveDataCallback<LoginResponse>(baseLiveData)
                .bindLoading()
                .doOnResponseSuccess { _, response ->

                }
                .doOnResponseCodeError { call, response ->
                    toast(response.errorMsg)
                }

        )

    }
}