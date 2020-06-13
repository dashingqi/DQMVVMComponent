package com.dashingqi.module.widget.history

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.alibaba.android.arouter.facade.model.TypeWrapper
import com.alibaba.android.arouter.launcher.ARouter
import com.dashingqi.base.providers.mmkv.IMMKVProviders
import com.dashingqi.module.widget.R
import com.dashingqi.module.widget.databinding.WidgetHistoryLayoutBinding

/**
 * @author : zhangqi
 * @time : 2020/6/10
 * desc : 搜索历史布局
 */
class DQHistoryLayout : ConstraintLayout {

    var adapter: DQHistoryLayoutAdapter

    var historyBinding = WidgetHistoryLayoutBinding
            .bind(LayoutInflater.from(context)
                    .inflate(R.layout.widget_history_layout, null, false))

    var mmkv = ARouter.getInstance().navigation(IMMKVProviders::class.java)

    private val MMKV_COMMON_PATH = "DQ_WAN_ANDROID_PATH"

    private var mmkvKey = ""

    constructor(context: Context) : super(context, null) {
    }

    constructor(context: Context, attributes: AttributeSet) : super(context, attributes) {
    }

    init {
        /**
         * 添加布局
         */
        addView(historyBinding.root)
        adapter = DQHistoryLayoutAdapter(LayoutInflater.from(context), mutableListOf())
        //设置适配器
        historyBinding.tagFlowLayout.adapter = adapter
        historyBinding.ivClearHistory.setOnClickListener { view ->
            /**
             * 刷新适配器数据
             */
            adapter.data.clear()
            adapter.notifyDataChanged()
        }
    }

    /**
     * 对外提供调用的方法
     */
    fun bindExitText(key: String) {
        mmkvKey = key

    }

    /**
     * 从MMKV中获取数据
     */
    private fun getDataFromMMKV() {
        var type = object : TypeWrapper<List<String>>() {}.type
        var data: MutableList<String> = mmkv.getDefaultMMKV().getObject(MMKV_COMMON_PATH + mmkvKey, type)
        adapter.data.clear()
        adapter.data.addAll(data)
        adapter.notifyDataChanged()
    }


}