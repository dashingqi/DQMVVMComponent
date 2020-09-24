package com.dashingqi.module.widget.customview.timepickerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.dashingqi.base.route.RoutePath
import com.dashingqi.module.widget.R


@Route(path = RoutePath.Widget.WIDGET_TIME_PICKER)
class WidgetTimePickerPlusActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.widget_time_picker_plus_activity)
        TimePickerPlus(this).show()
    }
}