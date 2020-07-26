package com.dashingqi.module.user.modules.app

import android.content.Context
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.dashingqi.base.providers.mmkv.IMMKVProviders
import com.dashingqi.base.route.RoutePath
import com.dashingqi.library.service.providers.user.UserBean
import com.dashingqi.library.service.providers.user.UserService

/**
 * @author : zhangqi
 * @time : 2020/7/26
 * desc :
 */
@Route(path = RoutePath.User.USER_SERVICE, name = "用户模块入口")
class UserServiceImpl : UserService {

    private val mkvm = ARouter.getInstance().navigation(IMMKVProviders::class.java)

    override fun setToken(token: String) {
        mkvm?.let { it.getDefaultMMKV().putString(TOKEN_KEY_KV, token) }
    }

    override fun getToken(): String {
        return mkvm?.let {
            it.getDefaultMMKV().getString(TOKEN_KEY_KV)
        }!!
    }

    override fun setUserData(userBean: UserBean) {
        mkvm?.getDefaultMMKV()?.putObject(USER_BEAN_KEY_KV, userBean)
    }

    /**
     * 清除数据
     */
    override fun clearData() {
        mkvm?.apply {
            getDefaultMMKV().clear(TOKEN_KEY_KV)
            getDefaultMMKV().clear(USER_BEAN_KEY_KV)
        }
    }

    override fun init(context: Context?) {
    }

    companion object {
        /**
         *存储token的key
         */
        const val TOKEN_KEY_KV = "token_key_kv"

        const val USER_BEAN_KEY_KV = "user_bean_key_kv"
    }
}