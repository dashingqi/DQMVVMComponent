package com.dashingqi.module.user.modules.user

import android.view.View
import com.alibaba.android.arouter.facade.annotation.Route
import com.dashingqi.base.base.fragment.BaseMvvMFragment
import com.dashingqi.module.user.databinding.UserFragmentBinding
import com.google.android.material.appbar.AppBarLayout
import kotlinx.android.synthetic.main.user_fragment.*
import java.lang.Math.abs

/**
 * @author : zhangqi
 * @time : 2020/5/21
 * desc :
 */
@Route(path = "/user/user_fragment")
class UserFragment : BaseMvvMFragment<UserFragmentBinding, UserFragmentViewModel>() {

    override fun onLoad(view: View) {
        super.onLoad(view)
        dataBinding.tvName.alpha = 0.0F
        dataBinding.ivMeIcon.alpha = 0.0f
        registerAppBarLayoutListener()
    }

    override fun isFitsSystemWindow(): Boolean {
        return false
    }

    private fun registerAppBarLayoutListener() {
        dataBinding.appBarLayout.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { applbarLayout, verticalOffset ->
            var percent = (kotlin.math.abs(verticalOffset * 1.0f)) / appBarLayout.totalScrollRange
            if (percent > 0.15 || percent == 0.0f) {
                dataBinding.ivMeIconBig.alpha = 1 - percent
            }

            if (percent > 0.25 || percent == 0.0f) {
                dataBinding.tvNameBig.alpha = 1 - percent
            }

            if (percent > 0.35 || percent == 0.0f) {
                dataBinding.tvCollect.alpha = 1 - percent
            }

            if (percent > 0.45 || percent == 0.0f) {
                dataBinding.tvDesc.alpha = 1 - percent
            }

            if (percent > 0.65 || percent == 0.0f) {
                dataBinding.labelRV.alpha = 1 - percent
            }

            if (percent == 1.0f) {
                dataBinding.tvName.alpha = 1.0f
                dataBinding.ivMeIcon.alpha = 1.0f
            } else {
                dataBinding.tvName.alpha = 0.0f
                dataBinding.ivMeIcon.alpha = 0.0f
            }

        })
    }
}