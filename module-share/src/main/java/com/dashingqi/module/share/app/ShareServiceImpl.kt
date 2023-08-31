package com.dashingqi.module.share.app

import android.app.Activity
import android.content.Context
import com.alibaba.android.arouter.facade.annotation.Route
import com.dashingqi.base.route.RoutePath
import com.dashingqi.library.service.providers.share.ShareService
import com.dashingqi.library.service.providers.share.WxShareBean
import com.dashingqi.module.share.modules.wx.WXShareUtils

/**
 * @author : zhangqi
 * @time : 2020/7/25
 * desc :
 */
@Route(path = RoutePath.Share.SHARE_SERVICE, name = "分享模块入口")
class ShareServiceImpl : ShareService {
    override fun performWxShare(shareBean: WxShareBean, activity: Activity) {
        handleWxShare(shareBean, activity)
    }

    override fun isInstallWx(activity: Activity): Boolean {
        return WXShareUtils.instance.installWX(activity)
    }

    override fun init(context: Context?) {
    }
    /**
     * 处理传递过来的微信分享
     */
    private fun handleWxShare(bean: WxShareBean, activity: Activity) {
        when (bean.shareType) {
            1 -> {
                //图片的分享
                WXShareUtils.instance.wxShareImg(bean, activity)
            }
            3 -> {
                //小程序的分享
                WXShareUtils.instance.wxShareMiniProgram(bean, activity)
            }
            else -> {

            }
        }
    }
}