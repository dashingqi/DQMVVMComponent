package com.dashingqi.library.service.providers.common.initcode

import android.content.Context
import android.graphics.Color
import android.text.Html
import android.view.animation.AccelerateInterpolator
import android.view.animation.DecelerateInterpolator
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.ViewPager
import com.dashingqi.base.widget.indicator.ScaleTransitionPagerTitleView
import com.dashingqi.library.service.providers.common.response.CommonClassifyResponse
import net.lucode.hackware.magicindicator.MagicIndicator
import net.lucode.hackware.magicindicator.buildins.UIUtil
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator

/**
 * @author : zhangqi
 * @time : 2020/5/24
 * desc : 通用型初始化代码
 */

/**
 * viewpager初始化的通用代码
 */
fun ViewPager.init(fragment: Fragment, fragments: ArrayList<Fragment>): ViewPager {
    adapter = object : FragmentStatePagerAdapter(fragment.childFragmentManager) {
        override fun getItem(position: Int): Fragment = fragments[position]

        override fun getCount(): Int = fragments.size
    }
    return this
}

fun ViewPager.init(manager: FragmentManager, fragments: ArrayList<Fragment>): ViewPager {
    adapter = object : FragmentStatePagerAdapter(manager) {
        override fun getItem(position: Int): Fragment = fragments[position]

        override fun getCount(): Int = fragments.size
    }
    return this
}

fun MagicIndicator.initAndBindVP(viewPager: ViewPager, datas: ArrayList<CommonClassifyResponse>, normalDefaultColor: String = "#ffffff", selectDefaultColor: String = normalDefaultColor, lineDefaultColor: String = normalDefaultColor) {
    initAndBindVP(viewPager, datas, normalDefaultColor, selectDefaultColor, lineDefaultColor, false)
}

/**
 * MagicIndicator初始化的通用代码
 */
fun MagicIndicator.initAndBindVP(viewPager: ViewPager, datas: ArrayList<CommonClassifyResponse>, normalDefaultColor: String = "#ffffff", selectDefaultColor: String = normalDefaultColor, lineDefaultColor: String = normalDefaultColor, isAdjustMode: Boolean) {
    var commonNavigator = CommonNavigator(viewPager.context)
    commonNavigator.isAdjustMode = isAdjustMode
    commonNavigator.adapter = object : CommonNavigatorAdapter() {
        override fun getTitleView(context: Context, index: Int): IPagerTitleView {
            return ScaleTransitionPagerTitleView(context.applicationContext).apply {
                text = Html.fromHtml(datas[index].name)
                textSize = 17f

                normalColor = Color.parseColor(normalDefaultColor)
                selectedColor = Color.parseColor(selectDefaultColor)
                setOnClickListener {
                    viewPager.currentItem = index
                }
            }
        }

        override fun getCount(): Int {
            return datas.size ?: 0
        }

        override fun getIndicator(context: Context): IPagerIndicator {
            return LinePagerIndicator(context).apply {
                mode = LinePagerIndicator.MODE_EXACTLY
                //线条的宽高度
                lineHeight = UIUtil.dip2px(context.applicationContext, 3.0).toFloat()
                lineWidth = UIUtil.dip2px(context.applicationContext, 30.0).toFloat()
                //线条的圆角
                roundRadius = UIUtil.dip2px(context.applicationContext, 6.0).toFloat()
                startInterpolator = AccelerateInterpolator()
                endInterpolator = DecelerateInterpolator(2.0f)
                //线条的颜色
                setColors(Color.parseColor(lineDefaultColor))
            }
        }

    }

    this.navigator = commonNavigator

    viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
        override fun onPageScrollStateChanged(state: Int) {
            this@initAndBindVP.onPageScrollStateChanged(state)
        }

        override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
            this@initAndBindVP.onPageScrolled(position, positionOffset, positionOffsetPixels)
        }

        override fun onPageSelected(position: Int) {
            this@initAndBindVP.onPageSelected(position)
        }

    })

    viewPager.currentItem = 0

}