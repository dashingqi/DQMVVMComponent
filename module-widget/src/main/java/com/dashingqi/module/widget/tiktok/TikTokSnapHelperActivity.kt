package com.dashingqi.module.widget.tiktok

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.alibaba.android.arouter.facade.annotation.Route
import com.dashingqi.base.base.activity.BaseMVVMActivity
import com.dashingqi.base.route.RoutePath
import com.dashingqi.module.widget.databinding.WidgetTikTokSnapHelperActivityBinding

@Route(path = RoutePath.Widget.WIDGET_TIK_TOK_SNAP)
class TikTokSnapHelperActivity : BaseMVVMActivity<WidgetTikTokSnapHelperActivityBinding, TikTokSnapHelperViewModel>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var pagerSnapHelper = object : PagerSnapHelper() {
            //在Adapter的onBindViewHolder之后执行
            override fun findTargetSnapPosition(
                layoutManager: RecyclerView.LayoutManager?,
                velocityX: Int,
                velocityY: Int
            ): Int {
                return super.findTargetSnapPosition(layoutManager, velocityX, velocityY)
            }

            //在Adapter的onBindViewHolder之后执行
            override fun findSnapView(layoutManager: RecyclerView.LayoutManager?): View? {
                return super.findSnapView(layoutManager)
            }
        }

        pagerSnapHelper.attachToRecyclerView(dataBinding.rv)
        viewModel.liveData.observe(this, Observer { str ->
            Log.d("TikTokActivity", "str --> $str ")
        })
    }

    override fun isFitsSystemWindows(): Boolean {
        return true
    }

}