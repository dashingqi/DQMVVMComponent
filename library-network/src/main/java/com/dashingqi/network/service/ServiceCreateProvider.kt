package com.dashingqi.network.service

import android.content.Context
import com.alibaba.android.arouter.facade.annotation.Route
import com.dashingqi.base.providers.network.IServiceProvider

/**
 * @author : zhangqi
 * @time : 2020/5/17
 * desc :
 */
@Route(path = "/network/IServiceProvider", name = "Service创建模块入口")
class ServiceCreateProvider : IServiceProvider {
    override fun init(context: Context?) {
    }

    override fun <T> createService(cla: Class<T>): T {
        TODO("Not yet implemented")
    }

}