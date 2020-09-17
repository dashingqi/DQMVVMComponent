package com.dashingqi.module.widget.dialog

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.dashingqi.base.route.RoutePath
import com.dashingqi.dqdialog.BoxDialog
import com.dashingqi.module.widget.R

@Route(path = RoutePath.Widget.WIDGET_BOX_DIALOG)
class BoxDialogActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.widget_box_dialog_activity)
        BoxDialog(this)
                .setDialogTitle("测试")
                .setDialogContent("this is content")
                .setNegativeText("取消")
                .setPositiveText("确定")
                .show()

    }
}