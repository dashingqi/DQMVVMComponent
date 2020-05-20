package com.dashingqi.module.home.modules.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.dashingqi.base.base.activity.BaseMVVMActivity
import com.dashingqi.base.widget.bottomtab.FragmentCreator
import com.dashingqi.base.widget.bottomtab.FragmentSwitchController
import com.dashingqi.base.widget.bottomtab.OnItemSelectIChangedListener
import com.dashingqi.base.widget.bottomtab.TabManager
import com.dashingqi.module.home.R
import com.dashingqi.module.home.databinding.HomeActivityMainBinding
import com.orhanobut.logger.Logger
import kotlinx.android.synthetic.main.home_activity_main.*

@Route(path = "/home/main_activity")
class HomeMainActivity : BaseMVVMActivity<HomeActivityMainBinding, HomeViewModel>() {

    val fsc by lazy {

        val tags: MutableSet<String> = HashSet()
        TabManager.getTabsLiveData().value!!.forEach { it ->
            tags.add(it.pathLD.value!!)
        }

        FragmentSwitchController<String>(supportFragmentManager, R.id.containerLayout, object : FragmentCreator<String> {
            override fun create(tag: String): Fragment? {
                return ARouter.getInstance().build(tag).navigation() as Fragment
            }
        }, tags)
    }

    /**
     * 创建Adapter
     */
    val bottomAdapter by lazy {
        BottomBarAdapter(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        configBottomBar()
    }

    /**
     * 配置BottomBar
     */
    private fun configBottomBar() {

        bottomBar.setOnItemSelectChangedListener(object : OnItemSelectIChangedListener {
            override fun onItemSelect(oldIndex: Int, newIndex: Int) {
                fsc.showFragment(
                        bottomAdapter.getItem(newIndex)?.pathLD?.value!!
                )
            }
        })

        TabManager.getTabsLiveData().observe(this, Observer { data ->
            var size = data.size
            Logger.d("size = $size")
//            bottomAdapter.setData(data)
//            bottomAdapter.notifyDataSetChanged()
//
//            if (bottomBar.getSelectIndex() < 0) {
//                data.indexOfFirst {
//                    it?.visibilityLD?.value == true
//                }.run {
//                    bottomBar.setSelectedIndex(this)
//                }
//            }

        })

    }

}
