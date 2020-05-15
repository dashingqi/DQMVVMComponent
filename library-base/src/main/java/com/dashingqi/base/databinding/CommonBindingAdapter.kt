package com.dashingqi.base.databinding

import android.graphics.Color
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.view.View
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.dashingqi.base.utils.DensityUtils

/**
 * @author : zhangqi
 * @time : 2020/5/13
 * desc : xml组件中的特殊用法
 */
object CommonBindingAdapter {

    /**
     * 自定义一个带有圆角的shape的View
     */
    @JvmStatic
    @BindingAdapter(value = ["dbBackgroundColor", "dbShapeRadius"], requireAll = true)
    fun setBackgroundShape(view: View, color: Int, radius: Float) {
        var drawable = GradientDrawable()
        drawable.setColor(color)
        drawable.cornerRadius = DensityUtils.dip2px(view.context, radius)
        view.background = drawable
    }

    @JvmStatic
    @BindingAdapter(value = ["dbBackgroundColor", "dbShapeRadius"], requireAll = true)
    fun setBackgroundShape(view: View, color: String, radius: Float) {
        var drawable = GradientDrawable()
        drawable.setColor(Color.parseColor(color))
        drawable.cornerRadius = DensityUtils.dip2px(view.context, radius)
        view.background = drawable
    }

    @JvmStatic
    @BindingAdapter(value = ["dbBackgroundColorId", "dbShapeRadius"], requireAll = true)
    fun setBackgroundShapeForColorId(view: View, colorId: Int, radius: Float) {
        var drawable = GradientDrawable()
        drawable.setColor(ContextCompat.getColor(view.context, colorId))
        drawable.cornerRadius = DensityUtils.dip2px(view.context, radius)
        view.background = drawable
    }

    /**
     * 加载图片的
     */
    @JvmStatic
    @BindingAdapter(value = ["dbImageUrl"], requireAll = true)
    fun setImageUrl(view: ImageView, dbImageUrl: String) {
        Glide.with(view.context).load(dbImageUrl).into(view)
    }

    @JvmStatic
    @BindingAdapter(value = ["dbImageSrc"], requireAll = true)
    fun setImageViewDrawable(view: ImageView, drawable: Drawable) {
        view.setImageDrawable(drawable)
    }

    @JvmStatic
    @BindingAdapter(value = ["dbImageResource"], requireAll = true)
    fun setImageViewResource(view: ImageView, resource: Int) {
        if (resource == null || resource == 0) {
            view.setImageDrawable(null)
        } else {
            view.setImageResource(resource)
        }
    }
}