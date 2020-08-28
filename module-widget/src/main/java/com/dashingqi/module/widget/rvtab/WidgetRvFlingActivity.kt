package com.dashingqi.module.widget.rvtab


import android.graphics.Rect
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.alibaba.android.arouter.facade.annotation.Route
import com.dashingqi.base.base.activity.BaseMVVMActivity
import com.dashingqi.base.route.RoutePath
import com.dashingqi.base.utils.DensityUtils
import com.dashingqi.module.widget.databinding.WidgetRvFlingActivityBinding

@Route(path = RoutePath.Widget.WIDGET_RV_FLING)
class WidgetRvFlingActivity : BaseMVVMActivity<WidgetRvFlingActivityBinding, WidgetRvFlingViewModel>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding.rv.addItemDecoration(object : RecyclerView.ItemDecoration() {
            override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
                outRect.left = DensityUtils.dip2pxInt(parent.context, 16f)
            }
        })
    }

}