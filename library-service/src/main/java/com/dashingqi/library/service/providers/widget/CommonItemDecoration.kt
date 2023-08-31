package com.dashingqi.library.service.providers.widget

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.dashingqi.base.utils.DensityUtils

/**
 * @author : zhangqi
 * @time : 2020/8/25
 * desc :
 */
class CommonItemDecoration : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        var context = parent.context
        if (parent.getChildAdapterPosition(view) == 0) {
            outRect.top = DensityUtils.dip2pxInt(context, 16f)
        }
        outRect.left = DensityUtils.dip2pxInt(context, 16f)
        outRect.right = DensityUtils.dip2pxInt(context, 16f)
        outRect.bottom = DensityUtils.dip2pxInt(context, 16f)

    }
}