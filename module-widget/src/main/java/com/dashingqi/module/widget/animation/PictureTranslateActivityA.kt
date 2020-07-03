package com.dashingqi.module.widget.animation

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.annotation.NonNull
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.alibaba.android.arouter.facade.annotation.Route
import com.bumptech.glide.Glide
import com.dashingqi.module.widget.R
import com.dashingqi.module.widget.databinding.WidgetActivityPictureTranslateABinding

@Route(path = "/widget/picture_translate_a")
class PictureTranslateActivityA : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.widget_activity_picture_translate_a)
        var viewBinding = DataBindingUtil.setContentView<WidgetActivityPictureTranslateABinding>(this, R.layout.widget_activity_picture_translate_a)
        Glide.with(this).load("http://a2.att.hudong.com/36/48/19300001357258133412489354717.jpg").into(viewBinding.imgA)
        viewBinding.imgA.setOnClickListener { view ->
            startActivity(Intent(this, PictureTranslateActivityB::class.java).apply {
                putExtra("imgUrl", "http://a2.att.hudong.com/36/48/19300001357258133412489354717.jpg")
                putExtra("data", captureValues(view))
            })
        }
    }

    private fun captureValues(@NonNull view: View): Bundle? {
        val b = Bundle()
        val screenLocation = IntArray(2)
        view.getLocationOnScreen(screenLocation)
        b.putInt("left", screenLocation[0])
        b.putInt("top", screenLocation[1])
        b.putInt("width", view.width)
        b.putInt("height", view.height)
        return b
    }
}