package com.dashingqi.module.wx.net

import com.dashingqi.base.base.response.BaseResponse
import com.dashingqi.library.service.providers.common.response.CommonArticleResponse

/**
 * @author : zhangqi
 * @time : 2020/5/24
 * desc :
 */
data class WxArticleListResponse(var data: WxArticleListResponseDta) : BaseResponse()

data class WxArticleListResponseDta(var curPage: Int,
                                    var offset: Int,
                                    var over: Boolean,
                                    var pageCount: Int,
                                    var size: Int,
                                    var total: Int,
                                    val datas: List<CommonArticleResponse>)