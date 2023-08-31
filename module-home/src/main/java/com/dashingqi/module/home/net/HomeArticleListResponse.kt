package com.dashingqi.module.home.net

import com.dashingqi.base.base.response.BaseResponse
import com.dashingqi.library.service.providers.common.response.CommonArticleListDataResponse

/**
 * @author : zhangqi
 * @time : 2020/5/25
 * desc :首页文章列表数据
 */
data class HomeArticleListResponse(var data: CommonArticleListDataResponse) : BaseResponse() {
}