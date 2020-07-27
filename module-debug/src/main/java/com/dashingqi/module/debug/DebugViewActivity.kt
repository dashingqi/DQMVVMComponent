package com.dashingqi.module.debug

import android.graphics.Color
import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.dashingqi.base.base.activity.BaseMVVMActivity
import com.dashingqi.base.route.RoutePath
import com.dashingqi.base.widget.loading.LoadingDialog
import com.dashingqi.module.debug.databinding.DebugActivityViewBinding
import kotlinx.android.synthetic.main.debug_activity_view.*

@Route(path = RoutePath.Debug.DEBUG_VIEW)
class DebugViewActivity : BaseMVVMActivity<DebugActivityViewBinding, DebugViewModel>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        btnShowLoading.setOnClickListener {
            LoadingDialog(this).show()
        }
    }

    override fun isFitsSystemWindows(): Boolean {
        return false
    }

    override fun setStatusBarColorInt(): Int {
        return Color.WHITE
    }
}