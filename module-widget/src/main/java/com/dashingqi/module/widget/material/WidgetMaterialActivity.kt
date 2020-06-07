package com.dashingqi.module.widget.material

import android.os.Bundle
import android.view.View
import com.alibaba.android.arouter.facade.annotation.Route
import com.dashingqi.base.base.activity.BaseMVVMActivity
import com.dashingqi.module.widget.databinding.WidgetMaterialActivityBinding
import com.google.android.material.appbar.AppBarLayout
import kotlin.math.abs


@Route(path = "/widget/material")
class WidgetMaterialActivity : BaseMVVMActivity<WidgetMaterialActivityBinding, WidgetMaterialViewModel>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initAppBarLayoutListener()
    }

    private fun initAppBarLayoutListener() {
        dataBinding.appBarLayout.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { appBarLayout, verticalOffset ->

            var percent = (abs(verticalOffset * 1.0f)) / appBarLayout.totalScrollRange
            if (percent > 0.8) {
                dataBinding.myInfo.visibility = View.VISIBLE

                var alpha = 1 - (1 - percent) * 5
                dataBinding.myInfo.alpha = alpha
            } else {
                dataBinding.myInfo.visibility = View.GONE
            }
        })
    }
}