package com.dashingqi.library_base.base.activity

import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

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
}