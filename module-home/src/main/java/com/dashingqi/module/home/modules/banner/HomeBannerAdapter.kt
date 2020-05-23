package com.dashingqi.module.home.modules.banner

import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dashingqi.module.home.net.HomeBannerResponse
import com.youth.banner.adapter.BannerAdapter

/**
 * @author : zhangqi
 * @time : 2020/5/23
 * desc : banner轮播图的适配器
 */
class HomeBannerAdapter(var datas: ArrayList<HomeBannerResponse.DataBean> = ArrayList()) : BannerAdapter<HomeBannerResponse.DataBean, HomeBannerAdapter.HomeBannerViewHolder>(datas) {

    class HomeBannerViewHolder(var imageView: ImageView) : RecyclerView.ViewHolder(imageView) {

    }

    override fun onCreateHolder(parent: ViewGroup?, viewType: Int): HomeBannerViewHolder {
        var imageView = ImageView(parent?.context)
        imageView.layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        imageView.scaleType = ImageView.ScaleType.CENTER_CROP
        return HomeBannerViewHolder(imageView)
    }

    override fun onBindView(holder: HomeBannerViewHolder?, data: HomeBannerResponse.DataBean?, position: Int, size: Int) {
        holder?.itemView?.context?.let { Glide.with(it).load(data!!.imagePath).into(viewHolder.imageView) }
    }
}