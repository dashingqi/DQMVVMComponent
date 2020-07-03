package com.dashingqi.module.widget.animation

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.ViewTreeObserver
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.dashingqi.module.widget.R
import com.dashingqi.module.widget.databinding.WidgetActivityPictureTranslateBBinding


class PictureTranslateActivityB : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var viewBinding = DataBindingUtil.setContentView<WidgetActivityPictureTranslateBBinding>(this, R.layout.widget_activity_picture_translate_b)
        var imgUrl = intent.getStringExtra("imgUrl")
        Glide.with(this).load(imgUrl).listener(object : RequestListener<Drawable> {
            override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Drawable>?, isFirstResource: Boolean): Boolean {
                return false
            }

            override fun onResourceReady(resource: Drawable?, model: Any?, target: Target<Drawable>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {
                onUiReady(viewBinding.imgB)
                return false
            }

        }).into(viewBinding.imgB)
    }

    private fun onUiReady(imageView: ImageView) {
        imageView.viewTreeObserver.addOnPreDrawListener(object : ViewTreeObserver.OnPreDrawListener {
            override fun onPreDraw(): Boolean {
                // remove previous listener
                imageView.viewTreeObserver.removeOnPreDrawListener(this)
                // prep the scene
                //prepareScene()
                // run the animation
                //runEnterAnimation()
                return true
            }
        })
    }
}