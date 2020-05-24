package com.dashingqi.project.net

import com.dashingqi.base.base.response.BaseResponse
import com.dashingqi.library.service.providers.common.response.CommonClassifyResponse

/**
 * @author : zhangqi
 * @time : 2020/5/25
 * desc :
 */
data class ProjectTreeResponse(var data:List<CommonClassifyResponse>):BaseResponse() {
}