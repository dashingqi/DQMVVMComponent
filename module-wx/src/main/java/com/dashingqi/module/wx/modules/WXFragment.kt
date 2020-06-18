package com.dashingqi.module.wx.modules

import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.dashingqi.base.base.fragment.BaseMvvMFragment
import com.dashingqi.library.service.providers.common.initcode.init
import com.dashingqi.library.service.providers.common.initcode.initAndBindVP
import com.dashingqi.module.wx.databinding.WxFragmentBinding
import com.orhanobut.logger.Logger

/**
 * @author : zhangqi
 * @time : 2020/5/22
 * desc :
 */
@Route(path = "/wx/wx_fragment")
class WXFragment : BaseMvvMFragment<WxFragmentBinding, WXFragmentViewModel>() {
    var fragments = ArrayList<Fragment>()
    override fun onLoad(view: View) {
        super.onLoad(view)
        createObserver()
        //初始化ViewPager
        dataBinding.viewPager.init(this, fragments)
        //绑定ViewPager
        dataBinding.magicIndicator.initAndBindVP(dataBinding.viewPager, viewModel.mDatas)

    }

    /**
     * 创建观察者
     */
    private fun createObserver() {
        viewModel.wxArticleChapterLiveData.observe(this, Observer { data ->
            if (fragments.isNotEmpty()) {
                fragments.clear()
            }
            data.forEach { it ->
                var fragment = ARouter
                        .getInstance()
                        .build("/wx/article_list_fragment")
                        .withInt("id", it.id)
                        .navigation() as Fragment
                fragments.add(fragment)
            }
            dataBinding.magicIndicator.navigator.notifyDataSetChanged()
            dataBinding.viewPager.adapter?.notifyDataSetChanged()
        })
    }
}