package com.dashingqi.module.home.modules.main

import android.view.View
import androidx.lifecycle.Observer
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.dashingqi.base.base.fragment.BaseMvvMFragment
import com.dashingqi.module.home.databinding.HomeFragmentBinding
import com.dashingqi.module.home.modules.banner.HomeBannerAdapter
import com.dashingqi.module.home.net.HomeBannerResponse
import com.google.android.material.appbar.AppBarLayout
import com.youth.banner.indicator.CircleIndicator
import kotlinx.android.synthetic.main.home_fragment.*

/**
 * @author : zhangqi
 * @time : 2020/5/21
 * desc :
 */

@Route(path = "/home/home_fragment")
class HomeFragment : BaseMvvMFragment<HomeFragmentBinding, HomeFragmentViewModel>() {
    private lateinit var homeBannerAdapter: HomeBannerAdapter

    override fun onLoad(view: View) {
        super.onLoad(view)
        configHomeBanner()
        createObserver()
        registerAppbarLayoutListener()
        dataBinding.layoutSearch.alpha = 0.0F

    }

    private fun configHomeBanner() {
        homeBannerAdapter = HomeBannerAdapter()
        dataBinding.homeBanner.adapter = homeBannerAdapter
        dataBinding.homeBanner.indicator = CircleIndicator(activity)
        dataBinding.homeBanner.setOnBannerListener { data, position ->
            var data = dataBinding.homeBanner.adapter.getData(position) as HomeBannerResponse.DataBean
            data?.let {
                ARouter.getInstance().build("/web/commonView").withString("url", it.url).withString("title", it.title).navigation()
            }

        }

    }

    /**
     * 监听AppbarLayout的事件
     */
    private fun registerAppbarLayoutListener() {
        appBarLayout.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { appBarLayout, verticalOffset ->
            var percent = (kotlin.math.abs(verticalOffset * 1.0f)) / appBarLayout.totalScrollRange
            var alpha = 1 - (1 - percent) * 5
            if (percent > 0.2) {
                dataBinding.layoutSearch.alpha = alpha
            } else if (percent == 0.0F) {
                dataBinding.layoutSearch.alpha = 0.0F
            }
        })
    }

    /**
     * 创建观察者
     */
    private fun createObserver() {
        viewModel.bannerData.observe(this, Observer { data ->
            homeBannerAdapter.datas.clear()
            homeBannerAdapter.datas.addAll(data)
            homeBannerAdapter.notifyDataSetChanged()
        })
    }

    override fun onStart() {
        super.onStart()
        homeBanner.start()
    }

    override fun onStop() {
        super.onStop()
        homeBanner.stop()
    }

    override fun isFitsSystemWindow(): Boolean {
        return false
    }
}