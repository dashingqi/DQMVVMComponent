package com.dashingqi.module.widget.BottomSheetDialog

import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.dashingqi.base.base.activity.BaseMVVMActivity
import com.dashingqi.base.route.RoutePath
import com.dashingqi.module.widget.databinding.WidgetBottomSheetDialogActivityBinding
import kotlinx.android.synthetic.main.widget_bottom_sheet_dialog_activity.*

@Route(path = RoutePath.Widget.WIDGET_BOTTOM_SHEET_DIALOG)
class WidgetBottomSheetDialogActivity : BaseMVVMActivity<WidgetBottomSheetDialogActivityBinding, WidgetBottomSheetDialogViewModel>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        show.setOnClickListener { view ->
            DQWidgetBottomSheetDialog().show(supportFragmentManager, "widget_bottom_sheet_dialog")
        }
    }

}