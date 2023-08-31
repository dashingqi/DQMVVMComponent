package com.dashingqi.module.widget.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.dashingqi.module.widget.R
import kotlinx.android.synthetic.main.widget_share_element_two_activity.*

class WidgetShareElementTwoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.widget_share_element_two_activity)
        Glide.with(this).load("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1601188226461&di=faf1fb0225697b7bc0dc82738280a767&imgtype=0&src=http%3A%2F%2Fa2.att.hudong.com%2F36%2F48%2F19300001357258133412489354717.jpg").into(iv)
        iv.post {  }
    }
}