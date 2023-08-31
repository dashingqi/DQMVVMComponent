package com.dashingqi.module.widget.callback

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.alibaba.android.arouter.facade.annotation.Route
import com.dashingqi.base.base.activity.BaseMVVMActivity
import com.dashingqi.base.route.RoutePath
import com.dashingqi.dqcommonutils.toast
import com.dashingqi.dqlog.DQLog
import com.dashingqi.module.widget.R
import com.dashingqi.module.widget.databinding.WidgetCallbackActivityBinding
import com.dashingqi.network.service.ServiceController
import com.google.gson.GsonBuilder
import com.google.gson.TypeAdapter
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonWriter
import kotlinx.android.synthetic.main.widget_callback_activity.*

@Route(path = RoutePath.Widget.WIDGET_CALL_BACK)
class CallbackActivity : BaseMVVMActivity<WidgetCallbackActivityBinding, CallbackViewModel>() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val gson = GsonBuilder().registerTypeAdapter(ServiceController.LiveDataType().type, object :
                TypeAdapter<MutableLiveData<String>>() {
            override fun write(out: JsonWriter, value: MutableLiveData<String>?) {
                DQLog.d("write")
                out.value(value?.value)
            }

            override fun read(reader: JsonReader): MutableLiveData<String> {
                DQLog.d("read")
                return MutableLiveData<String>().apply {
                    value = reader.nextString()
                }
            }

        }).create()

        jsonToBean.setOnClickListener {
            var fromJson = gson.fromJson("{\"name\":\"dashingqi\"}", CallBackBean::class.java)
            Log.d("tag",fromJson.name.value)
        }

        beanToJson.setOnClickListener {
            val bean = CallBackBean()
            bean.name.value = "dashingqi"
            var toJson = gson.toJson(bean)
            Log.d("tag", "toJosn ---> ${toJson}")
        }

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