package com.dashingqi.module.widget.ddmc.detail

import android.graphics.Color
import android.graphics.Rect
import android.os.Bundle
import android.view.View
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.recyclerview.widget.RecyclerView
import com.alibaba.android.arouter.facade.annotation.Route
import com.dashingqi.base.base.activity.BaseMVVMActivity
import com.dashingqi.base.route.RoutePath
import com.dashingqi.base.utils.DensityUtils
import com.dashingqi.dqlog.DQLog
import com.dashingqi.module.widget.databinding.WidgetActivityDdmcProductDetailBinding
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.tabs.TabLayout
import com.scwang.smartrefresh.layout.listener.CoordinatorLayoutListener
import kotlinx.android.synthetic.main.widget_activity_ddmc_product_detail.*
import kotlin.math.abs


/**
 * 叮咚买菜详情页面
 */
@Route(path = RoutePath.Widget.WIDGET_DDMC_PRODUCT_DETAIL)
class DdmcProductDetailActivity : BaseMVVMActivity<WidgetActivityDdmcProductDetailBinding, DdmcProductDetailViewModel>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val itemDecoration = object : RecyclerView.ItemDecoration() {
            override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
                super.getItemOffsets(outRect, view, parent, state)
                val childPosition = parent.getChildAdapterPosition(view)
                if (childPosition == 0) {
                    outRect.left = DensityUtils.dip2pxInt(parent.context, 16f)
                }
                outRect.right = DensityUtils.dip2pxInt(parent.context, 16f)
            }
        }
        rvRecommend.addItemDecoration(itemDecoration)
        rvRecommendFood.addItemDecoration(itemDecoration)

        rvRecommendList.addItemDecoration(object : RecyclerView.ItemDecoration() {
            override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
                val childPosition = parent.getChildAdapterPosition(view)
                if (childPosition % 2 == 0) {
                    outRect.left = DensityUtils.dip2pxInt(parent.context, 16f)
                    outRect.right = DensityUtils.dip2pxInt(parent.context, 8f)
                } else if (childPosition % 2 == 1) {
                    outRect.right = DensityUtils.dip2pxInt(parent.context, 16f)
                    outRect.left = DensityUtils.dip2pxInt(parent.context, 8f)
                }
                if (childPosition == 0 || childPosition == 1) {
                    outRect.top = DensityUtils.dip2pxInt(parent.context, 16f)
                }
                outRect.bottom = DensityUtils.dip2pxInt(parent.context, 16f)
            }
        })
        toolBar.alpha = 0.0f
        configAppbarLayout()
        configTabLayout()
    }

    override fun isFitsSystemWindows(): Boolean {
        return true
    }

    override fun setStatusBarColorInt(): Int {
        return Color.parseColor("#ffffff")
    }

    private fun configAppbarLayout() {
        viewModel.tabData.forEach {
            val newTab = tabLayout.newTab()
            tabLayout.addTab(newTab.setText(it))
        }

        appBarLayout.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { applbarLayout, verticalOffset ->
            var percent = (abs(verticalOffset * 1.0f)) / appBarLayout.totalScrollRange
            DQLog.d("verticalOffset --> ", "$verticalOffset")
            val viewline3Top = viewLine3.top
            DQLog.d("viewline3Top ---> ", "$viewline3Top")
            val viewLine5Top = viewLine5.top
            if (percent > 0.01 && percent <= 0.5) {
                toolBar.alpha = percent * 10
            }
            if (percent == 1.0f) {
                toolBar.alpha = 1.0f
            } else if (percent == 0.0f) {
                toolBar.alpha = 0.0f
            }
            val offset = abs(verticalOffset) + toolBar.height

            val location = IntArray(2)
            rvRecommendList.getLocationInWindow(location) //获取在当前窗口内的绝对坐标，含toolBar
            val x = location[0]
            val y = location[1]
            DQLog.d("x == ", "$x")
            DQLog.d("y === ", "$y")
            val screenHeight = DensityUtils.getScreenHeightPixels(this)
            DQLog.d("screenHeight==> ", "$screenHeight")
            var selectPosition = 0
            when {
                (screenHeight - toolBar.height >= y) -> {
                    selectPosition = 3
                }
                (offset in viewline3Top until viewLine5Top) -> {
                    selectPosition = 1
                }
                offset >= viewLine5Top -> {

                    selectPosition = 2
                }
                offset < viewline3Top -> {
                    selectPosition = 0
                }
            }
            //滑动tabLayout
            tabLayout.setScrollPosition(selectPosition, 0f, true, true)
        })
    }

    private fun configTabLayout() {
        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
                val childPosition = tab?.position
                val appBarLayoutParams = (appBarLayout.layoutParams) as (CoordinatorLayout.LayoutParams)
                val appBarLayoutBehavior = appBarLayoutParams.behavior as CoordinatorLayout.Behavior
                if (appBarLayoutBehavior is AppBarLayout.Behavior) {
                    if (childPosition == 0) {
                        appBarLayoutBehavior.topAndBottomOffset = 0
                    }
                }
            }
        })
    }

}