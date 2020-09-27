package com.dashingqi.module.widget.view

import android.app.ActivityOptions
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.dashingqi.base.route.RoutePath
import com.dashingqi.module.widget.R
import kotlinx.android.synthetic.main.widget_share_element_activity.*

@Route(path = RoutePath.Widget.WIDGET_SHARE_ELEMENT)
class WidgetShareElementActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.widget_share_element_activity)
        jump.setOnClickListener {
            val intent = Intent(this, WidgetShareElementTwoActivity::class.java)
            startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this, jump, "sharedView").toBundle())
        }

    }
}