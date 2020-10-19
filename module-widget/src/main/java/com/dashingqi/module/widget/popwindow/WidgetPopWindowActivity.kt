package com.dashingqi.module.widget.popwindow

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.dashingqi.base.route.RoutePath
import com.dashingqi.module.widget.R
import kotlinx.android.synthetic.main.widget_pop_window_activity.*

@Route(path = RoutePath.Widget.WIDGET_POP_WINDOW)
class WidgetPopWindowActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.widget_pop_window_activity)
        btnShowPopWindow.setOnClickListener {
            var dqCustomerPopWindow = DQCustomerPopWindow(this)
            dqCustomerPopWindow.showAsDropDown(btnShowPopWindow, 0, 0)
        }
    }
}