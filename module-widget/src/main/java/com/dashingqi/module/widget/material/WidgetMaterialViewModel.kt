package com.dashingqi.module.widget.material

import android.app.Application
import androidx.databinding.ObservableArrayList
import com.dashingqi.base.base.viewmodel.BaseViewModel
import com.dashingqi.module.widget.BR
import com.dashingqi.module.widget.R
import me.tatarka.bindingcollectionadapter2.ItemBinding

/**
 * @author : zhangqi
 * @time : 2020/6/7
 * desc :
 */
class WidgetMaterialViewModel(application: Application) : BaseViewModel(application) {

    val items = ObservableArrayList<String>()

    val itemBinding = ItemBinding.of<String>(BR.item, R.layout.widget_material_item_layout)

    init {
        for (value in 0..60) {
            items.add("$value")
        }

    }
}