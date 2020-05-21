package com.dashingqi.module.user.other.tablayout


import android.os.Bundle
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.alibaba.android.arouter.facade.annotation.Route
import com.dashingqi.base.base.activity.BaseMVVMActivity
import com.dashingqi.module.user.R
import com.dashingqi.module.user.databinding.UserActivityTabLayoutTestBinding
import com.dashingqi.module.user.other.fragment.Test1Fragment
import com.dashingqi.module.user.other.fragment.Test2Fragment
import com.dashingqi.module.user.other.fragment.Test3Fragment
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.user_activity_tab_layout_test.*

@Route(path = "/user/tab_layout_test")
class UserTabLayoutTestActivity : BaseMVVMActivity<UserActivityTabLayoutTestBinding, UserTabLayoutTestViewModel>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initViewPagerAdapter()

    }

    private fun initViewPagerAdapter() {
        viewPager.adapter = object : FragmentStateAdapter(this) {
            override fun getItem(position: Int): Fragment {
                return when (position) {
                    0 -> Test1Fragment()
                    1 -> Test2Fragment()
                    else -> Test3Fragment()
                }
            }

            override fun getItemCount(): Int = 3

        }
        tabLayout.isTabIndicatorFullWidth = false

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            when (position) {
                0 -> tab.customView?.findViewById<TextView>(R.id.customerTableLayoutTitle)?.text = "测试标题1"
                1 -> tab.customView?.findViewById<TextView>(R.id.customerTableLayoutTitle)?.text = "测试标题2"
                else -> tab.customView?.findViewById<TextView>(R.id.customerTableLayoutTitle)?.text = "测试标题3"
            }
        }.attach()
    }

}
