package com.dashingqi.module.widget.rvtab

import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.alibaba.android.arouter.facade.annotation.Route
import com.dashingqi.base.base.activity.BaseMVVMActivity
import com.dashingqi.base.route.RoutePath
import com.dashingqi.library.service.providers.widget.CommonItemDecoration
import com.dashingqi.module.widget.databinding.WidgetRvTabActivityBinding
import com.google.android.material.tabs.TabLayout

@Route(path = RoutePath.Widget.WIDGET_RV_TAB_LAYOUT)
class WidgetRvTabActivity : BaseMVVMActivity<WidgetRvTabActivityBinding, WidgetRvTabViewModel>() {

    val rvManager by lazy {
        dataBinding.rv.layoutManager as LinearLayoutManager
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        configRV()
        configTabData()
    }

    private fun configTabData() {
        viewModel.setTabData().forEach { data ->
            dataBinding.tabLayout.addTab(dataBinding.tabLayout.newTab().setText(data))
        }
        dataBinding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
                var currentTabPosition = tab?.position
                rvManager.scrollToPositionWithOffset(currentTabPosition ?: 0, 0)
            }

        })
    }


    @RequiresApi(Build.VERSION_CODES.M)
    private fun configRV() {
        dataBinding.rv.addItemDecoration(CommonItemDecoration())
        dataBinding.rv.setOnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->
            dataBinding.tabLayout.setScrollPosition(rvManager.findFirstVisibleItemPosition(), 0f, true)
        }
    }

    override fun isImmersionEnable(): Boolean {
        return false
    }
}