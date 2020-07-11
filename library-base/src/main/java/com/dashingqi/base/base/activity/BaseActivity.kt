package com.dashingqi.base.base.activity

import android.content.Intent
import android.content.pm.ActivityInfo
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import com.dashingqi.library_base.R
import com.gyf.immersionbar.ImmersionBar

/**
 * @author : zhangqi
 * @time : 2020/4/25
 * desc :
 */
open class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        if (isScreenForcePortrait()) {
            requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        }
        super.onCreate(savedInstanceState)
    }

    init {
        if (isImmersionEnable()) {
            lifecycle.addObserver(object : LifecycleObserver {
                @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
                fun onCreate() {
                    ImmersionBar.with(this@BaseActivity)
                            .statusBarDarkFont(isDarkFont())
                            .fitsSystemWindows(isFitsSystemWindows())
                            .statusBarColorInt(setStatusBarColorInt())
                            .keyboardEnable(true, WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
                            .init()
                }
            })
        }
    }

    /**
     * 是否强制竖屏
     */
    fun isScreenForcePortrait() = true

    /**
     * 提供简单的页面跳转封装
     * 组件化中更多是使用ARouter进行页面间跳转
     */
    fun gotoActivity(clazz: Class<*>) {
        startActivity(Intent(this, clazz))
    }

    /**
     * 用来控制 是否要做状态栏的改变
     */
    open fun isImmersionEnable(): Boolean {
        return true
    }

    open fun isDarkFont(): Boolean {
        return true
    }

    /**
     * 为了防止布局和顶部的状态栏重叠
     */
    open fun isFitsSystemWindows(): Boolean {
        return false
    }

    /**
     * 设置状态栏的颜色
     */
    open fun setStatusBarColorInt(): Int {
        return resources.getColor(R.color.res_colorPrimary)
    }

    override fun onBackPressed() {
        super.onBackPressed()
       /// moveTaskToBack(true)
    }
}