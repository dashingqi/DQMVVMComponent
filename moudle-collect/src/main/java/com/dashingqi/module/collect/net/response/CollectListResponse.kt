package com.dashingqi.module.collect.net.response

import com.dashingqi.base.base.response.BaseResponse
import com.dashingqi.library.service.providers.common.response.CommonArticleResponse

/**
 *
 * @ProjectName: DQMVVMComponent
 * @Package: com.dashingqi.module.collect.net.response
 * @ClassName: CollectListResponse
 * @Author: DashingQI
 * @CreateDate: 2020/8/24 11:40 PM
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/8/24 11:40 PM
 * @UpdateRemark:
 * @Version: 1.0
 */
data class CollectListResponse(var data :CollectListDataBean):BaseResponse(){
    data class CollectListDataBean(var datas:List<CommonArticleResponse>)

}