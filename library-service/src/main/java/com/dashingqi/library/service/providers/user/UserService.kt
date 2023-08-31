package com.dashingqi.library.service.providers.user

import com.alibaba.android.arouter.facade.template.IProvider

/**
 * @author : zhangqi
 * @time : 2020/4/29
 * desc :
 */
interface UserService : IProvider {

    /**
     *
     */
    fun setToken(token: String)

    /**
     * 获取到Token
     */
    fun getToken(): String

    /**
     * 保存用户数据
     */
    fun setUserData(userBean: UserBean)

    /**
     * 清除数据
     */
    fun clearData()
}