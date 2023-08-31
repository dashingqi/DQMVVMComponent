package com.dashingqi.base.providers.params

import com.alibaba.android.arouter.facade.template.IProvider

/**
 * @author : zhangqi
 * @time : 2020/5/17
 * desc :
 */
interface IGlobalParams : IProvider {


    /**
     * 提供对外获取baseUrl的方法
     */
    fun getBaseUrl(): String
}