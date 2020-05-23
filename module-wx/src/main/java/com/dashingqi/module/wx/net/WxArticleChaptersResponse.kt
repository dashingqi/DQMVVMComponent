package com.dashingqi.module.wx.net

import com.dashingqi.base.base.response.BaseResponse
import com.dashingqi.library.service.providers.common.response.CommonClassifyResponse

/**
 * @author : zhangqi
 * @time : 2020/5/23
 * desc :
 */
data class WxArticleChaptersResponse(var data: List<CommonClassifyResponse>) : BaseResponse() {

}