package com.dashingqi.module.widget.select

import android.os.Bundle
import android.widget.Toast
import com.alibaba.android.arouter.facade.annotation.Route
import com.dashingqi.base.base.activity.BaseMVVMActivity
import com.dashingqi.base.route.RoutePath
import com.dashingqi.module.widget.databinding.WidgetActivityLetterViewBinding
import com.orhanobut.logger.Logger

@Route(path = RoutePath.Widget.WIDGET_LETTER_VIEW)
class WidgetLetterViewActivity : BaseMVVMActivity<WidgetActivityLetterViewBinding, WidgetLetterViewModel>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding.letterView.setOnTouchListener(object : WidgetLitterListView.OnTouchListener {
            override fun onLetterTouch(letterStr: String, isTouch: Boolean) {
                if (isTouch) {
                    Logger.d("letterStr = $letterStr")
                    Toast.makeText(this@WidgetLetterViewActivity, letterStr, Toast.LENGTH_SHORT).show()
                }
            }
        })
    }
}