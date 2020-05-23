package com.dashingqi.module.wx.modules

import android.os.Parcel
import android.os.Parcelable
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.alibaba.android.arouter.facade.annotation.Route
import com.dashingqi.base.base.fragment.BaseMvvMFragment
import com.dashingqi.module.wx.databinding.WxFragmentBinding
import kotlinx.android.synthetic.main.wx_fragment.*

/**
 * @author : zhangqi
 * @time : 2020/5/22
 * desc :
 */
@Route(path = "/wx/wx_fragment")
class WXFragment : BaseMvvMFragment<WxFragmentBinding, WXFragmentViewModel>() {

    override fun onLoad(view: View) {
        super.onLoad(view)
        dataBinding.viewPager.adapter = object : FragmentStateAdapter(activity!!) {
            override fun getItem(position: Int): Fragment {
                return WXArticleChapterFragment()
            }

            override fun getItemCount(): Int {
                return 5
            }

        }


//        dataBinding.tabLayout.isTabIndicatorFullWidth = false
        viewModel.wxArticleChapterLiveData.observe(this, Observer { data ->


//            viewPagerAdapter.notifyDataSetChanged()
//            TabLayoutMediator(dataBinding.tabLayout, dataBinding.viewPager) { tab, position ->
//                tab.customView?.findViewById<TextView>(R.id.customerTableLayoutTitle)?.text = data[position].name
//            }.attach()

        })

    }


}