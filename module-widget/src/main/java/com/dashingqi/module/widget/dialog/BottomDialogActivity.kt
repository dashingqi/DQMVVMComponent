package com.dashingqi.module.widget.dialog

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.dashingqi.base.route.RoutePath
import com.dashingqi.module.widget.R
import kotlinx.android.synthetic.main.widget_bottom_dialog_activity.*

@Route(path = RoutePath.Widget.WIDGET_BOTTOM_DIALOG)
class BottomDialogActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.widget_bottom_dialog_activity)
        btn_bottom_dialog.setOnClickListener {
            MyBottomDialog(this).show()
        }
    }
}