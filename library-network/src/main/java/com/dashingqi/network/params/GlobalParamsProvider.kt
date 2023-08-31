package com.dashingqi.network.params

import android.content.Context
import com.alibaba.android.arouter.facade.annotation.Route
import com.dashingqi.base.providers.params.IGlobalParams
import com.dashingqi.library_network.BuildConfig

/**
 * @author : zhangqi
 * @time : 2020/5/17
 * desc :
 */
@Route(path = "/service/params_provider", name = "全局请求链接的参数")
class GlobalParamsProvider : IGlobalParams {
    override fun getBaseUrl(): String {
        return when {
            BuildConfig.NET_WORK_TYPE_IS_RELEASE -> Url.RELEASE_URL
            BuildConfig.NET_WORK_TYPE_IS_UAT -> Url.UAT_URL
            else -> Url.BEAT_URL

        }

    }

    override fun init(context: Context?) {
    }

    object Url {
        //正式环境
        const val RELEASE_URL = "https://wanandroid.com/"

        //测试环境
        const val BEAT_URL = "https://wanandroid.com/"

        //预发布环境
        const val UAT_URL = "https://wanandroid.com/"
    }

    /**
     * 有时候项目中可能会有h5
     */
    object H5URL {

    }
}