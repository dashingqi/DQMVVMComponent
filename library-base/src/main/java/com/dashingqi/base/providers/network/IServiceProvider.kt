package com.dashingqi.base.providers.network

import com.alibaba.android.arouter.facade.template.IProvider

/**
 * @author : zhangqi
 * @time : 2020/5/17
 * desc :
 */
interface IServiceProvider : IProvider {

    fun <T> createService(cla: Class<T>): T

    fun <T> createService(cla: Class<T>, baseUrl: String): T
}