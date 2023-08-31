package com.dashingqi.module.widget.dialog

import android.content.Context
import android.view.Gravity
import android.view.LayoutInflater
import androidx.databinding.ObservableArrayList
import com.dashingqi.base.widget.loading.BaseDialog
import com.dashingqi.module.widget.BR
import com.dashingqi.module.widget.R
import com.dashingqi.module.widget.databinding.WidgetLogisticsDialogBinding
import me.tatarka.bindingcollectionadapter2.ItemBinding

/**
 * @author : zhangqi
 * @time : 2020/7/9
 * desc : 物流进度弹窗
 */
class LogisticsPathDialog(context: Context) : BaseDialog(context) {

    var items = ObservableArrayList<String>()
    var itemBinding = ItemBinding.of<String>(BR.item, R.layout.widget_item_logistics_path)
            .bindExtra(BR.viewModel, this)


    init {
        var rootView = WidgetLogisticsDialogBinding.inflate(LayoutInflater.from(context), null, false)
        setContentView(rootView.root)
        rootView.viewModel = this
        setCanceledOnTouchOutside(false)
        setDialogWidthPercent(0.9f)
        setDialogGravity(Gravity.CENTER)
        setDialogBackground(null)
        items.add("1")
        items.add("2")
        items.add("3")
        items.add("4")
        items.add("5")
        items.add("6")

    }

    fun isHeaderView(item: String): Boolean {
        return items.indexOf(item) == 0
    }

    fun isFootView(item: String): Boolean {
        return items.indexOf(item) == items.size - 1
    }

}