package com.dashingqi.module.home.net

import com.dashingqi.base.base.response.BaseResponse
import com.dashingqi.library.service.providers.common.response.CommonArticleListDataResponse

/**
 * @author : zhangqi
 * @time : 2020/7/11
 * desc : 首页最新项目的Response
 */
data class HomeProjectListResponse(var data: CommonArticleListDataResponse):BaseResponse() {
}