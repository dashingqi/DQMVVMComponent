package com.dashingqi.module.widget.dialog

import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.dashingqi.base.base.activity.BaseMVVMActivity
import com.dashingqi.base.route.RoutePath
import com.dashingqi.module.widget.databinding.WidgetActivitLogisticsBinding

/**
 * 物流进度
 */

@Route(path = RoutePath.Widget.WIDGET_LOGISTICS)
class WidgetLogisticsActivity : BaseMVVMActivity<WidgetActivitLogisticsBinding, WidgetLogisticsViewModel>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        LogisticsPathDialog(this).show()
    }

}