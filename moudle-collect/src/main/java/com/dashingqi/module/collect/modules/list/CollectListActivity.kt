package com.dashingqi.module.collect.modules.list

import android.graphics.Rect
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.alibaba.android.arouter.facade.annotation.Route
import com.dashingqi.base.base.activity.BaseMVVMActivity
import com.dashingqi.base.route.RoutePath
import com.dashingqi.base.utils.DensityUtils
import com.dashingqi.library.service.providers.widget.CommonItemDecoration
import com.dashingqi.moudle.collect.databinding.CollectListActivityBinding
import kotlinx.android.synthetic.main.collect_list_activity.*


@Route(path = RoutePath.Collect.COLLECT_LIST)
class CollectListActivity : BaseMVVMActivity<CollectListActivityBinding, CollectListViewModel>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        collectRV.addItemDecoration(CommonItemDecoration())
    }

    override fun isFitsSystemWindows(): Boolean {
        return true
    }
}