package com.dashingqi.library.service.providers.share

import android.app.Activity
import com.alibaba.android.arouter.facade.template.IProvider

/**
 * @author : zhangqi
 * @time : 2020/7/25
 * desc :
 */
interface ShareService : IProvider {

    /**
     * 执行微信的分享
     */
    fun performWxShare(shareBean: WxShareBean, activity: Activity)

    /**
     * 用于判断是否安装微信
     */
    fun isInstallWx(activity: Activity): Boolean
}