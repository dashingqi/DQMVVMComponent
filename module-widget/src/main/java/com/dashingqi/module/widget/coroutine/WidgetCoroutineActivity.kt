package com.dashingqi.module.widget.coroutine

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import com.alibaba.android.arouter.facade.annotation.Route
import com.dashingqi.base.route.RoutePath
import com.dashingqi.module.widget.R
import kotlinx.coroutines.*

@Route(path = RoutePath.Widget.WIDGET_COROUTINE)
class WidgetCoroutineActivity : AppCompatActivity() {
    private val TAG = "WidgetCoroutineActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_widget_coroutine)
        // lifecycleScope 是能绑定Activity和Fragment的生命周期
        // 在组件销毁的时候会自动取消协程
        lifecycleScope.launch(Dispatchers.Default) {
            //在协中开启一个子协程
            launch {
                // doSomething
            }

            val asyncData: Deferred<String> = async(Dispatchers.IO) {
                // doSomething
                "zhangqi"
            }
            var data = asyncData.await()

            Log.d(TAG, "onCreate: data == $data") // data == zhangqi

        }
    }
}