package com.dashingqi.module.widget.customview.timepickerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.alibaba.android.arouter.facade.annotation.Route
import com.dashingqi.base.route.RoutePath
import com.dashingqi.dqcommonutils.DateUtils
import com.dashingqi.module.widget.R
import kotlinx.android.synthetic.main.widget_time_picker_plus_activity.*


@Route(path = RoutePath.Widget.WIDGET_TIME_PICKER)
class WidgetTimePickerPlusActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.widget_time_picker_plus_activity)
        var timePickerPlus = TimePickerPlus(this)
        timePickerPlus.okListener = { date ->
            var dateToString = DateUtils.dateToString(date, "yyyy-MM-dd")
            tvDate.text = dateToString
        }
        timePickerPlus.show()
    }
}