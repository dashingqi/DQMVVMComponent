package com.dashingqi.base.widget.history

import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import com.dashingqi.library_base.R
import com.zhy.view.flowlayout.FlowLayout
import com.zhy.view.flowlayout.TagAdapter

/**
 * @author : zhangqi
 * @time : 2020/6/10
 * desc : 搜索布局的适配器
 */
class DQHistoryLayoutAdapter(var layoutInflater: LayoutInflater, var data: MutableList<String> = ArrayList<String>()) : TagAdapter<String>(data) {
    override fun getView(parent: FlowLayout?, position: Int, t: String?): View {
        var itemView = layoutInflater.inflate(R.layout.base_history_item, null, false) as TextView
        itemView.text = t
        return itemView
    }
}