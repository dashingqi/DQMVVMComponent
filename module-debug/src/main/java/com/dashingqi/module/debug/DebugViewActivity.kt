package com.dashingqi.module.debug

import android.graphics.Color
import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.dashingqi.base.base.activity.BaseMVVMActivity
import com.dashingqi.base.route.RoutePath
import com.dashingqi.module.debug.databinding.DebugActivityViewBinding


@Route(path = RoutePath.Debug.DEBUG_VIEW)
class DebugViewActivity : BaseMVVMActivity<DebugActivityViewBinding, DebugViewModel>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun isFitsSystemWindows(): Boolean {
        return true
    }

}