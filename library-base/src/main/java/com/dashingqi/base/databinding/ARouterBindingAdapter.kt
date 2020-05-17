package com.dashingqi.base.databinding

import android.view.View
import androidx.databinding.BindingAdapter
import com.alibaba.android.arouter.launcher.ARouter

/**
 * @author : zhangqi
 * @time : 2020/5/17
 * desc :
 */
object ARouterBindingAdapter {

    /**
     * 使用ARouter进行跳转
     */
    @JvmStatic
    @BindingAdapter(value = ["arPath"], requireAll = true)
    fun arPath(view: View, path: String) {
        view?.let {
            path?.let {
                view.setOnClickListener(AntiOnClickListener(View.OnClickListener {
                    ARouter.getInstance().build(path).navigation()
                }))
            }
        }
    }
}
