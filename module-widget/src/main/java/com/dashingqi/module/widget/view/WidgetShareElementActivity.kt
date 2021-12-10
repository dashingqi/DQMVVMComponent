package com.dashingqi.module.widget.view

import android.app.ActivityOptions
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.alibaba.android.arouter.facade.annotation.Route
import com.bumptech.glide.Glide
import com.dashingqi.base.route.RoutePath
import com.dashingqi.module.widget.R

import com.dashingqi.module.widget.view.data.WeatherData
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.widget_share_element_activity.*

@Route(path = RoutePath.Widget.WIDGET_SHARE_ELEMENT)
class WidgetShareElementActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.widget_share_element_activity)
        Glide.with(this)
            .load(
                "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1601188226461&di" +
                        "=faf1fb0225697b7bc0dc82738280a767&imgtype=0&src=http%3A%2F%2Fa2." +
                        "att.hudong.com%2F36%2F48%2F19300001357258133412489354717.jpg"
            )
            .into(jump)
        jump.setOnClickListener {
            val intent = Intent(this, WidgetShareElementTwoActivity::class.java)
            startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this, jump, "sharedView").toBundle())
        }
        weatherView.setData(updateWeatherData())


        val gson = Gson()
        val lists = gson.fromJson<List<String>>("", object : TypeToken<List<String>>() {}.type)

    }


    override fun onResume() {
        super.onResume()
        Log.d("width ----> ", "${jump.width}")
        Log.d("height ---->", "${jump.height}")
    }

    private fun updateWeatherData(): WeatherData {
        return WeatherData().apply {
            city = "北京"
            weatherTemperature = "33"
            weatherDesc = "多云"
            pm = "46"
            weatherQuality = "优"
        }
    }
}