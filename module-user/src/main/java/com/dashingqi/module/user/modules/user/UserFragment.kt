package com.dashingqi.module.user.modules.user

import com.alibaba.android.arouter.facade.annotation.Route
import com.dashingqi.base.base.fragment.BaseMvvMFragment
import com.dashingqi.module.user.databinding.UserFragmentBinding

/**
 * @author : zhangqi
 * @time : 2020/5/21
 * desc :
 */
@Route(path = "/user/user_fragment")
class UserFragment : BaseMvvMFragment<UserFragmentBinding, UserFragmentViewModel>() {
}