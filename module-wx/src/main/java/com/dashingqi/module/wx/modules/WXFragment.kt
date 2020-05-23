package com.dashingqi.module.wx.modules

import android.view.View
import androidx.lifecycle.Observer
import com.alibaba.android.arouter.facade.annotation.Route
import com.dashingqi.base.base.fragment.BaseMvvMFragment
import com.dashingqi.library.service.providers.common.initcode.init
import com.dashingqi.library.service.providers.common.initcode.initAndBindVP
import com.dashingqi.module.wx.databinding.WxFragmentBinding

/**
 * @author : zhangqi
 * @time : 2020/5/22
 * desc :
 */
@Route(path = "/wx/wx_fragment")
class WXFragment : BaseMvvMFragment<WxFragmentBinding, WXFragmentViewModel>() {

    override fun onLoad(view: View) {
        super.onLoad(view)
        createObserver()
        //初始化ViewPager
        dataBinding.viewPager.init(this, viewModel.mFragments)
        //绑定ViewPager
        dataBinding.magicIndicator.initAndBindVP(dataBinding.viewPager,viewModel.mDatas)

    }

    /**
     * 创建观察者
     */
    private fun createObserver() {
        viewModel.wxArticleChapterLiveData.observe(this, Observer { data ->
            dataBinding.magicIndicator.navigator.notifyDataSetChanged()
            dataBinding.viewPager.adapter?.notifyDataSetChanged()
        })
    }
}