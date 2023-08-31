package com.dashingqi.module.widget.openeye


import android.graphics.Color
import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.dashingqi.base.base.activity.BaseMVVMActivity
import com.dashingqi.base.route.RoutePath
import com.dashingqi.library.service.providers.common.initcode.init
import com.dashingqi.library.service.providers.common.initcode.initAndBindVP
import com.dashingqi.module.widget.databinding.WidgetOpenEyeActivityBinding

@Route(path = RoutePath.Widget.WIDGET_OPEN_EYE)
class WidgetOpenEyeActivity : BaseMVVMActivity<WidgetOpenEyeActivityBinding, WidgetOpenEyeViewModel>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding.viewPager.init(supportFragmentManager, viewModel.getFragmentData())
        dataBinding.magicIndicator.initAndBindVP(dataBinding.viewPager, viewModel.getTitleData(), "#888888", "#444444", "#444444", true)
    }

    override fun isFitsSystemWindows(): Boolean {
        return true
    }

    override fun setStatusBarColorInt(): Int {
        return Color.parseColor("#888888")
    }

}