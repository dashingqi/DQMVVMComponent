package com.dashingqi.base.widget.imageview

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.dashingqi.library_base.R

/**
 * @author : zhangqi
 * @time : 2020/5/26
 * desc : 加载本地Gif的ImageView
 */
class GifImageVIew : ImageView {

    constructor(context: Context, attributeSet: AttributeSet) : super(context, attributeSet) {
        initAttribute(attributeSet)
    }

    private fun initAttribute(attr: AttributeSet) {
        var obtainAttr = context.obtainStyledAttributes(attr, R.styleable.base_GifImageView)
        var gifResourceId = obtainAttr.getResourceId(R.styleable.base_GifImageView_base_gif_src, View.NO_ID)
        Glide.with(context).load(gifResourceId).into(this)
        obtainAttr.recycle()
    }
}