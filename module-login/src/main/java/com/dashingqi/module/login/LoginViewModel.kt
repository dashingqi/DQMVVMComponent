package com.dashingqi.module.login

import android.app.Application
import androidx.databinding.ObservableField
import com.dashingqi.base.base.callback.LiveDataCallback
import com.dashingqi.base.base.response.BaseResponse
import com.dashingqi.base.base.viewmodel.BaseViewModel
import com.dashingqi.module.login.net.ILoginService
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
    var userNameField = ObservableField<String>()

    /**
     * 密码
     */
    var passwordField = ObservableField<String>()

    fun loginAction() {

        if (userNameField.get().isNullOrEmpty()) {
            return
        }

        if (passwordField.get().isNullOrEmpty()) {
            return
        }

        ILoginService.instance.login(userNameField.get().toString(), passwordField.get().toString()).enqueue(LiveDataCallback<BaseResponse>(baseLiveData)
                .bindLoading()
                .doOnResponseSuccess { _, response ->

                })
    }
}