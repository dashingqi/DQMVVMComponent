package com.dashingqi.base.base.fragment


import android.content.Context
import android.content.res.Configuration
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.dashingqi.library_base.R
import com.gyf.immersionbar.ImmersionBar
import com.gyf.immersionbar.components.SimpleImmersionOwner
import com.gyf.immersionbar.components.SimpleImmersionProxy


/**
 * @author : zhangqi
 * @time : 2020/4/25
 * desc :
 */
open class BaseFragment : Fragment(), SimpleImmersionOwner {
    private var showing = false
    private var touchable = false

    //Fragment沉浸式的代理类
    private val simpleImmersionProxy = SimpleImmersionProxy(this)
    override fun onStart() {
        super.onStart()
        showing = true
    }

    override fun onResume() {
        super.onResume()
        touchable = true
    }

    override fun onPause() {
        super.onPause()
        touchable = false
    }

    override fun onStop() {
        super.onStop()
        showing = false
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        simpleImmersionProxy.onActivityCreated(savedInstanceState)
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        simpleImmersionProxy.onConfigurationChanged(newConfig)
    }

    override fun onDestroy() {
        super.onDestroy()
        simpleImmersionProxy.onDestroy()
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        simpleImmersionProxy.isUserVisibleHint = isVisibleToUser
    }

    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        simpleImmersionProxy.onHiddenChanged(hidden)
    }

    /**
     * fragment是否可见
     */
    fun fragmentIsShowing(): Boolean = showing

    /**
     * fragment是否可触摸的
     */
    fun isTouchable(): Boolean = touchable

    /**
     * 用来控制Fragment快速实现沉浸式，当为true的时候才能去执行initImmersionBar()方法
     */
    override fun immersionBarEnabled(): Boolean = true

    override fun initImmersionBar() {
        val immersionBar = ImmersionBar.with(this).statusBarDarkFont(isDarkFont()).keyboardEnable(true)
        immersionBar.fitsSystemWindows(isFitsSystemWindow())
        if (isFitsSystemWindow()) {
            immersionBar.statusBarColorInt(setStatusBarColor())
        }
        immersionBar.navigationBarColorInt(Color.WHITE).init()
    }

    open fun isDarkFont(): Boolean {
        return true
    }

    /**
     * 防止状态栏和布局重叠的问题
     */
    open fun isFitsSystemWindow(): Boolean {
        return true
    }

    /**
     * 设置状态栏的颜色
     */
    open fun setStatusBarColor(): Int {
        return resources.getColor(R.color.res_colorPrimary)
    }

}