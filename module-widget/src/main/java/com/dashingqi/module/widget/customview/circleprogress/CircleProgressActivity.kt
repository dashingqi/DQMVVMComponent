package com.dashingqi.module.widget.customview.circleprogress

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Message
import com.alibaba.android.arouter.facade.annotation.Route
import com.dashingqi.base.route.RoutePath
import com.dashingqi.module.widget.R
import kotlinx.android.synthetic.main.widget_activity_circle_progress.*

@Route(path = RoutePath.Widget.WIDGET_CIRCLE_PROGRESS)
class CircleProgressActivity : AppCompatActivity() {


    private var flowValue = 0

    var handler: Handler = object : Handler() {
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            if (flowValue == 200) {
                removeMessages(0)
            } else {
                flowValue++
                circleProgress.setFlowValue(flowValue)
                sendEmptyMessageDelayed(0, 50)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.widget_activity_circle_progress)
        handler.sendEmptyMessageDelayed(0, 100)
    }
}