package com.dashingqi.module.widget.openeye.commend

import android.view.View
import com.alibaba.android.arouter.facade.annotation.Route
import com.dashingqi.base.base.fragment.BaseMvvMFragment
import com.dashingqi.base.route.RoutePath
import com.dashingqi.library.service.providers.widget.CommonItemDecoration
import com.dashingqi.module.widget.databinding.WidgetCommendFragmentBinding

/**
 * @author : zhangqi
 * @time : 2020/8/26
 * desc :
 */
@Route(path = RoutePath.Widget.WIDGET_OPEN_EYE_COMMEND)
class WidgetCommendFragment : BaseMvvMFragment<WidgetCommendFragmentBinding, WidgetCommendViewModel>() {

    override fun onLoad(view: View) {
        dataBinding.rv.addItemDecoration(CommonItemDecoration())
        super.onLoad(view)
    }
}