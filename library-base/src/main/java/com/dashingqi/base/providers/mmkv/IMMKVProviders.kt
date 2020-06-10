package com.dashingqi.base.providers.mmkv

import com.alibaba.android.arouter.facade.template.IProvider

/**
 * @author : zhangqi
 * @time : 2020/6/10
 * desc :
 */
interface IMMKVProviders : IProvider {

    fun getDefaultMMKV(): IKV

}