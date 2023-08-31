package com.dashingqi.library.service.providers.share

import android.graphics.Bitmap

/**
 * @author : zhangqi
 * @time : 2020/7/25
 * desc :
 */
class WxShareBean {

    //分享的文本
    var wxText = ""

    //标题
    var wxTitle = ""

    //描述
    var wxDescription = ""

    //网页的链接
    var webPageUrl = ""

    /**
     * 封面图的网络地址
     */
    var thumbUrl = ""

    /**
     * 封面图的 Bitmap
     */
    var thumbBitmap: Bitmap? = null

    /**
     * 分享图片 bitmap
     */
    var imgBitmap: Bitmap? = null

    /**
     * 小程序的链接
     */
    var miniProgramUrl = ""

    /**
     * 微信平台ID
     */
    var wxId = ""

    /**
     * 小程序页面路径
     */
    var wxPath = ""

    /**
     * 小程序的封面是网络还是bitmap
     * 默认是网络图片的地址
     */
    var miniShareImageUrl = true


    //朋友圈(1)还是微信好友(0)
    var targetScene = 1

    /**
     * 分享类别
     * 0 文字
     * 1 图片
     * 2 网页
     * 3 小程序
     * 4 视频
     */
    var shareType = 0
    override fun toString(): String {
        return "WXShareBean(wxText='$wxText', wxTitle='$wxTitle', wxDescription='$wxDescription', targetScene=$targetScene, webPageUrl='$webPageUrl', thumbUrl='$thumbUrl', miniProgramUrl='$miniProgramUrl', wxId='$wxId', wxPath='$wxPath', shareType=$shareType)"
    }

}