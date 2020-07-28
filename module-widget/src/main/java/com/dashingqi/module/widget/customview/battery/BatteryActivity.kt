package com.dashingqi.module.widget.customview.battery

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.os.Message
import androidx.annotation.NonNull
import androidx.appcompat.app.AppCompatActivity
import com.alibaba.android.arouter.facade.annotation.Route
import com.dashingqi.base.route.RoutePath
import com.dashingqi.module.widget.R
import kotlinx.android.synthetic.main.widget_battery_activity.*

@Route(path = RoutePath.Widget.WIDGET_BATTERY)
class BatteryActivity : AppCompatActivity() {


    private var batteryPowerValue = 0
    var handler: Handler = object : Handler() {
        @SuppressLint("HandlerLeak")
        override fun handleMessage(@NonNull msg: Message?) {
            if (batteryPowerValue > 100) {
                this.removeMessages(0)
            } else {
                batteryView.setBatteryPower(batteryPowerValue)
                batteryPowerValue++
                this.sendEmptyMessageDelayed(0, 300)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.widget_battery_activity)
        handler.sendEmptyMessageDelayed(0, 100)

    }
}