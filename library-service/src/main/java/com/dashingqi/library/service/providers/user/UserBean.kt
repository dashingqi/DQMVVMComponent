package com.dashingqi.library.service.providers.user

/**
 * @author : zhangqi
 * @time : 2020/7/26
 * desc :
 */
data class UserBean(var admin: Boolean,
                     var coinCount: Int,
                     var collectIds: List<Int>,
                     var email: String,
                     var icon: String,
                     var id: String,
                     var nickname: String,
                     var password: String,
                     var publishName: String,
                     var token: String,
                     var type: Int,
                     var username: String)