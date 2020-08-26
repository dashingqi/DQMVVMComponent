package com.dashingqi.module.widget.net.response

import com.dashingqi.base.base.response.BaseResponse

/**
 * @author : zhangqi
 * @time : 2020/8/26
 * desc :
 */
data class OpenEyeResponse(var itemList: List<ItemListBean>) : BaseResponse() {

    data class ItemListBean(var type: String, var data: ItemListDataBean)

    data class ItemListDataBean(var dataType: String, var image: String, var title:String,var category:String,var id: Int, var type: String, var text: String, var header: HeaderDataBean, var content: ContentBean,var cover: CoverDataBean)

    data class HeaderDataBean(var id: String, var actionUrl: String, var icon: String, var iconType: String, var time: String,
                              var showHateVideo: Boolean, var followType: String, var tagId: String, var tagName: String, var issuerName: String, var topShow: Boolean, var title: String, var description: String)

    data class ContentBean(var data: ContentDataBean)

    data class ContentDataBean(var title: String, var description: String, var url: String, var playUrl: String, var cover: CoverDataBean)
    data class CoverDataBean(var feed: String, var detail: String, var homePage: String)
}