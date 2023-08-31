package com.dashingqi.module.home.modules.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.dashingqi.base.widget.bottomtab.BottomBar
import com.dashingqi.base.widget.bottomtab.BottomBarItemBean
import com.dashingqi.module.home.R
import com.dashingqi.module.home.databinding.HomeBottomBarItemBinding

/**
 * @author : zhangqi
 * @time : 2020/5/19
 * desc :
 */
open class BottomBarAdapter(var context: AppCompatActivity) : BottomBar.Adapter<BottomBarAdapter.ViewHolder, BottomBarItemBean>() {
    private val inflate: LayoutInflater = LayoutInflater.from(context)

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var binding: HomeBottomBarItemBinding = HomeBottomBarItemBinding.bind(itemView)
        val data = MutableLiveData<BottomBarItemBean>()
        val selected = MutableLiveData<Boolean>()

        init {
            binding.viewHolder = this
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int, selectedPosition: Int) {
        var isSelect = position == selectedPosition
        var item = getItem(position)
        holder.data.postValue(item)
        holder.selected.postValue(isSelect)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var viewHolder = ViewHolder(inflate.inflate(R.layout.home_bottom_bar_item, parent, false))
        viewHolder.binding.lifecycleOwner = context
        return viewHolder
    }
}