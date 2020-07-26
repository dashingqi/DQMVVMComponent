package com.dashingqi.module.user.modules.app

import android.content.Context
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
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

    @JvmField
    @Autowired
    var mkvm: IMMKVProviders? = null

    override fun setToken(token: String) {
        mkvm?.let { it.getDefaultMMKV().putString(TOKEN_KEY_KV, token) }
    }

    override fun getToken(): String? {
        return mkvm?.let {
            it.getDefaultMMKV().getString(TOKEN_KEY_KV)
        }
    }

    override fun setUserData(userBean: UserBean) {
        mkvm?.getDefaultMMKV()?.putObject(USER_BEAN_KEY_KV, userBean)
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