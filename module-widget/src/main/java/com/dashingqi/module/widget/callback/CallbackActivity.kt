package com.dashingqi.module.widget.callback

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import com.alibaba.android.arouter.facade.annotation.Route
import com.dashingqi.base.base.activity.BaseMVVMActivity
import com.dashingqi.base.route.RoutePath
import com.dashingqi.dqcommonutils.toast
import com.dashingqi.module.widget.R
import com.dashingqi.module.widget.databinding.WidgetCallbackActivityBinding
import kotlinx.android.synthetic.main.widget_callback_activity.*

@Route(path = RoutePath.Widget.WIDGET_CALL_BACK)
class CallbackActivity : BaseMVVMActivity<WidgetCallbackActivityBinding, CallbackViewModel>() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        btnChangeData.setOnClickListener {
            CallBackHelper.liveData.value = "改变后的值"
        }
        CallBackHelper.liveData.observe(this, Observer {
            it?.let {
                var str = it as String
                Log.d("------> ", "获取到的结果 $str")
                toast(str)
            }
        })
    }

    override fun isFitsSystemWindows(): Boolean {
        return true
    }
}