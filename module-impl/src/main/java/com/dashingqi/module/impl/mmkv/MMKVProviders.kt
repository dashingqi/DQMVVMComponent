package com.dashingqi.module.impl.mmkv

import android.content.Context
import com.alibaba.android.arouter.facade.annotation.Route
import com.dashingqi.base.providers.mmkv.IKV
import com.dashingqi.base.providers.mmkv.IMMKVProviders
import com.tencent.mmkv.MMKV

/**
 * @author : zhangqi
 * @time : 2020/6/10
 * desc :
 */

@Route(path = "/service/mmkv", name = "mmkv工具类入口")
class MMKVProviders : IMMKVProviders {
    var context: Context? = null
    var defaultMMKV: DQMMKV? = null
    override fun getDefaultMMKV(): IKV = defaultMMKV!!

    override fun init(context: Context?) {
        MMKV.initialize(context)
        this.context = context
        defaultMMKV = DQMMKV(context!!)
    }
}