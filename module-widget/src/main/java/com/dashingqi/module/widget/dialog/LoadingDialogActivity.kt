package com.dashingqi.module.widget.dialog

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.dashingqi.base.route.RoutePath
import com.dashingqi.dqdialog.LoadingDialog
import com.dashingqi.module.widget.R
import kotlinx.android.synthetic.main.widget_loading_dialog_activity.*

@Route(path = RoutePath.Widget.WIDGET_LOADING_DIALOG)
class LoadingDialogActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(
                R.layout.widget_loading_dialog_activity)

        LoadingDialog(this).show()
        btn_show_loading.setOnClickListener {

        }
    }
}