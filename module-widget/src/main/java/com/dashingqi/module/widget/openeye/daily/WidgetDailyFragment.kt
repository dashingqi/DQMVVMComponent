package com.dashingqi.module.widget.openeye.daily

import android.view.View
import com.alibaba.android.arouter.facade.annotation.Route
import com.dashingqi.base.base.fragment.BaseMvvMFragment
import com.dashingqi.base.route.RoutePath
import com.dashingqi.library.service.providers.widget.CommonItemDecoration
import com.dashingqi.module.widget.databinding.WidgetDailyFragmentBinding

/**
 * @author : zhangqi
 * @time : 2020/8/26
 * desc :
 */
@Route(path = RoutePath.Widget.WIDGET_OPEN_EYE_DAILY)
class WidgetDailyFragment : BaseMvvMFragment<WidgetDailyFragmentBinding, WidgetDailyViewModel>() {

    override fun onLoad(view: View) {
        dataBinding.rv.addItemDecoration(CommonItemDecoration())
        super.onLoad(view)
    }
}