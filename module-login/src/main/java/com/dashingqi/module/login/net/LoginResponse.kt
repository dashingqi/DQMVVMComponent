package com.dashingqi.module.login.net

import com.dashingqi.base.base.response.BaseResponse
import com.dashingqi.library.service.providers.user.UserBean

/**
 * @author : zhangqi
 * @time : 2020/7/26
 * desc :
 */
data class LoginResponse(var data: UserBean) : BaseResponse()