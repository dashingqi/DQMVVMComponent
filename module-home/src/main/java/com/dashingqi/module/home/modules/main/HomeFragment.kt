package com.dashingqi.module.home.modules.main

import android.view.View
import androidx.lifecycle.Observer
import com.alibaba.android.arouter.facade.annotation.Route
import com.dashingqi.base.base.fragment.BaseMvvMFragment
import com.dashingqi.module.home.databinding.HomeFragmentBinding
import com.dashingqi.module.home.modules.banner.HomeBannerAdapter
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
        viewModel.bannerData.observe(this, Observer { data ->
            homeBannerAdapter.datas.clear()
            homeBannerAdapter.datas.addAll(data)
            homeBannerAdapter.notifyDataSetChanged()
        })
    }

    private fun configHomeBanner() {
        homeBannerAdapter = HomeBannerAdapter()
        dataBinding.homeBanner.adapter = homeBannerAdapter
        dataBinding.homeBanner.indicator = CircleIndicator(activity)

    }

    override fun onStart() {
        super.onStart()
        homeBanner.start()
    }

    override fun onStop() {
        super.onStop()
        homeBanner.stop()
    }

    override fun onDestroy() {
        super.onDestroy()

    }

    override fun isFitsSystemWindow(): Boolean {
        return false
    }
}