package com.dashingqi.module.home.modules.main

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.dashingqi.base.base.activity.BaseMVVMActivity
import com.dashingqi.base.eventbus.EventBusPath
import com.dashingqi.base.providers.eventbus.IEventBusProvider
import com.dashingqi.base.widget.bottomtab.*
import com.dashingqi.module.home.R
import com.dashingqi.module.home.databinding.HomeActivityMainBinding
import com.orhanobut.logger.Logger
import kotlinx.android.synthetic.main.home_activity_main.*

@Route(path = "/home/main_activity")
class HomeMainActivity : BaseMVVMActivity<HomeActivityMainBinding, HomeViewModel>() {

    val fsc by lazy {

        val tags: MutableSet<String?> = HashSet()

        for (it in TabManager.getTabsLiveData().value!!) {
            tags.add(it?.pathLD?.value)
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

        createTestObserver()
    }

    /**
     * 配置BottomBar
     */
    private fun configBottomBar() {

        bottomBar.setOnItemSelectChangedListener(object : OnItemSelectIChangedListener {
            override fun onItemSelect(oldIndex: Int, newIndex: Int) {
                Logger.d("oldIndex == $oldIndex, newIndex == $newIndex")
                fsc.showFragment(
                        bottomAdapter.getItem(newIndex)?.pathLD?.value!!
                )
            }
        })

        bottomBar.setAdapter(bottomAdapter)
        //监听数据的变化
        TabManager.getTabsLiveData().observe(this, Observer { bottomBarItemBeans: ArrayList<BottomBarItemBean?> ->
            var size = bottomBarItemBeans.size
            //设置数据
            bottomAdapter.data = bottomBarItemBeans
            //通知一下，
            bottomAdapter.notifyDataSetChanged()

            if (bottomBar.selectIndex < 0) {
                bottomBarItemBeans.indexOfFirst {
                    it?.visibilityLD?.value == true
                }.run {
                    bottomBar.setSelectedIndex(this)
                }
            }
        })
    }

    override fun setStatusBarColorInt(): Int {
        return Color.parseColor("#ffffffff")
    }

    private fun createTestObserver() {
        ARouter.getInstance().navigation(IEventBusProvider::class.java).with(EventBusPath.Test.TEST_PATH)
                .observe(this){
                    Logger.d("test_event_bus_perform ----> ${it.toString()}")
                }
    }
}
