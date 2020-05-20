package com.dashingqi.base.widget.bottomtab

import androidx.lifecycle.MutableLiveData
import com.dashingqi.base.providers.bottomtab.BottomTabProvider

/**
 * @author : zhangqi
 * @time : 2020/5/19
 * desc :
 */
object TabManager {

    @JvmField
    var liveData = MutableLiveData<ArrayList<BottomBarItemBean>>()

    @JvmField
    var tabs = ArrayList<BottomBarItemBean>()

    @JvmStatic
    fun register(tabProvider: BottomTabProvider) {
        var bottomBarItemBean: BottomBarItemBean = tabProvider.getBottomBarItemBean() ?: return
        tabs.add(bottomBarItemBean)
        liveData.postValue(tabs)

    }

    @JvmStatic
    fun getTabsLiveData(): MutableLiveData<ArrayList<BottomBarItemBean>> {
        return liveData
    }

    @JvmStatic
    fun getTabsData(): ArrayList<BottomBarItemBean> {
        return tabs
    }
}