package com.dashingqi.module.wx.net

import com.dashingqi.base.base.response.BaseResponse

/**
 * @author : zhangqi
 * @time : 2020/5/23
 * desc :
 */
data class WxArticleChaptersResponse(var data: List<DataBean>) : BaseResponse() {

    data class DataBean(var courseId: Int, var id: Int,
                        var name: String, var order: Int, var parentChapterId: Int,
                        var userControlSetTop: Boolean,
                        var visible: Int
    )

}