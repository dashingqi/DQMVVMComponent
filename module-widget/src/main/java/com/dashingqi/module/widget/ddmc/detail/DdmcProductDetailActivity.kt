package com.dashingqi.module.widget.ddmc.detail

import android.graphics.Color
import android.graphics.Rect
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.alibaba.android.arouter.facade.annotation.Route
import com.dashingqi.base.base.activity.BaseMVVMActivity
import com.dashingqi.base.route.RoutePath
import com.dashingqi.base.utils.DensityUtils
import com.dashingqi.dqlog.DQLog
import com.dashingqi.module.widget.databinding.WidgetActivityDdmcProductDetailBinding
import com.google.android.material.appbar.AppBarLayout
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
    }

    override fun isFitsSystemWindows(): Boolean {
        return true
    }

    override fun setStatusBarColorInt(): Int {
        return Color.parseColor("#ffffff")
    }

    private fun configAppbarLayout() {
        viewModel.tabData.forEach {
            tabLayout.addTab(tabLayout.newTab().setText(it))
        }


        appBarLayout.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { applbarLayout, verticalOffset ->
            var percent = (kotlin.math.abs(verticalOffset * 1.0f)) / appBarLayout.totalScrollRange
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
            when {
                (screenHeight - toolBar.height >= y) -> {
                    tabLayout.setScrollPosition(3, 0f, true)
                }
                (offset in viewline3Top until viewLine5Top) -> {
                    tabLayout.setScrollPosition(1, 0f, true)
                }
                offset >= viewLine5Top -> {
                    tabLayout.setScrollPosition(2, 0f, true)
                }
                offset < viewline3Top -> {
                    tabLayout.setScrollPosition(0, 0f, true)
                }
            }


        })

    }

}