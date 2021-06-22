package com.dashingqi.module.widget.coroutine

import android.util.Log
import androidx.lifecycle.lifecycleScope
import com.alibaba.android.arouter.facade.annotation.Route
import com.dashingqi.base.base.activity.BaseMVVMActivity
import com.dashingqi.base.route.RoutePath
import com.dashingqi.module.widget.databinding.WidgetActivityCoroutineBinding
import kotlinx.coroutines.*

@Route(path = RoutePath.Widget.WIDGET_COROUTINE)
class WidgetCoroutineActivity : BaseMVVMActivity<WidgetActivityCoroutineBinding,
        WidgetCoroutineViewModel>() {
    private val TAG = "WidgetCoroutineActivity"
    override fun onLoad(viewModel: WidgetCoroutineViewModel) {
        super.onLoad(viewModel)
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