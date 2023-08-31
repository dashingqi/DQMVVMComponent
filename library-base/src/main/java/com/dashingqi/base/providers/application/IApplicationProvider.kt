package com.dashingqi.base.providers.application

import android.app.Application

/**
 * @author : zhangqi
 * @time : 2020/5/20
 * desc : 对外体统的统一Application实现类，通过自动注册，将实现该接口的类注册到某一个类（ApplicationManager）的方法中，
 * 通过集合保存保存这些对象，在app（Application）的自定义Application中 的合适方法中去调用
 */
interface IApplicationProvider {
    /**
     * 初始化的操作
     */
    fun init(application: Application)

    fun onCreate()

    fun onLowMemory()

    fun onTerminate()

    /**
     * 获取到等级
     */
    fun getPriority(): Int

}