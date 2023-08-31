package com.dashingqi.module.share.modules.wx

import android.app.Activity
import com.dashingqi.library.service.providers.share.WxShareBean
//import com.umeng.socialize.ShareAction
//import com.umeng.socialize.UMShareAPI
//import com.umeng.socialize.UMShareListener
//import com.umeng.socialize.bean.SHARE_MEDIA
//import com.umeng.socialize.media.UMImage
//import com.umeng.socialize.media.UMMin

/**
 * @author : zhangqi
 * @time : 2020/7/25
 * desc : 微信分享的工具类
 */
class WXShareUtils {

    /**
     * 分享小程序目前仅仅支持微信好友
     */
    fun wxShareMiniProgram(bean: WxShareBean, activity: Activity) {

//        //兼容低版本的网页链接
//        var umMin = UMMin("https://www.baidu.com")
//        // 小程序消息封面图片
//        if (bean.miniShareImageUrl) {
//            umMin.setThumb(UMImage(activity, bean.thumbUrl))
//        } else {
//            umMin.setThumb(UMImage(activity, bean.imgBitmap))
//        }
//        // 小程序消息title
//        umMin.title = bean.wxTitle + bean.wxDescription
//        // 小程序消息描述
//        umMin.description = bean.wxDescription
//        //小程序页面路径
//        umMin.path = bean.wxPath
//        // 小程序原始id,在微信平台查询
//        umMin.userName = bean.wxId
//        ShareAction(activity)
//                .withMedia(umMin)
//                .setPlatform(SHARE_MEDIA.WEIXIN)
//                .setCallback(shareListener).share()
    }

    /**
     * 分享图片 可以分享给朋友也可以分享到朋友圈
     */
    fun wxShareImg(bean: WxShareBean, activity: Activity) {
//        var umImage = UMImage(activity, bean.imgBitmap!!)
//        ShareAction(activity)
//                .withText("crm")
//                .withMedia(umImage)
//                .setPlatform(if (bean.targetScene == 1) SHARE_MEDIA.WEIXIN_CIRCLE else SHARE_MEDIA.WEIXIN)
//                .setCallback(shareListener).share()
    }

    /**
     * 是否安装微信了
     */
    fun installWX(activity: Activity): Boolean {
        return false;
//        return UMShareAPI.get(activity).isInstall(activity, SHARE_MEDIA.WEIXIN)
    }

    /**
     * 分享的回调
     */
//    private var shareListener = object : UMShareListener {
//        override fun onResult(p0: SHARE_MEDIA?) {
//
//        }
//
//        override fun onCancel(p0: SHARE_MEDIA?) {
//
//        }
//
//        override fun onError(p0: SHARE_MEDIA?, p1: Throwable?) {
//            var name = p0?.name
//            var ordinal = p0?.ordinal
//            p1?.printStackTrace()
//            var message = p1?.message
//            var toString = p1.toString()
//        }
//
//        override fun onStart(p0: SHARE_MEDIA?) {
//        }
//
//    }

    companion object {
        val instance = WXShareUtils()
    }
}