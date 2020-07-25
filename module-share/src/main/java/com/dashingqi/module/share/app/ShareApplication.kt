package com.dashingqi.module.share.app

import android.app.Application
import android.content.Context
import com.dashingqi.base.providers.application.IApplicationProvider
import com.umeng.commonsdk.UMConfigure
import com.umeng.socialize.PlatformConfig

/**
 * @author : zhangqi
 * @time : 2020/7/25
 * desc :
 */
class ShareApplication : IApplicationProvider {

    private var context: Context? = null
    override fun init(application: Application) {
        context = application
    }

    override fun onCreate() {
        //执行友盟的注册
        //做下友盟分享的初始化
        UMConfigure.init(
                context,
                "5ed07e8d570df3d8a20002cf",
                "crm_um_share",
                UMConfigure.DEVICE_TYPE_PHONE,
                ""
        )
        PlatformConfig.setWeixin("wx7c6aefb68a206835", "de675f3b2e25f7efa9dbe15e822987e7")
    }

    override fun onLowMemory() {

    }

    override fun onTerminate() {

    }

    override fun getPriority(): Int {
        return 30
    }
}