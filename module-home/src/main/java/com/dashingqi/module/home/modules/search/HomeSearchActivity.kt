package com.dashingqi.module.home.modules.search

import android.graphics.Color
import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.dashingqi.base.base.activity.BaseMVVMActivity
import com.dashingqi.module.home.databinding.HomeActivitySearchBinding
import kotlinx.android.synthetic.main.home_activity_search.*

/**
 *
 */
@Route(path = "/home/search")
class HomeSearchActivity : BaseMVVMActivity<HomeActivitySearchBinding, HomeSearchViewModel>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        searchText.requestFocus()
        searchHistory.bindExitText("home_article", searchText, refreshLayout, false) { key ->
            viewModel.refresh()
        }
    }

    override fun isFitsSystemWindows(): Boolean {
        return true
    }

    override fun setStatusBarColorInt(): Int {
        return Color.WHITE
    }
}