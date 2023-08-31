package com.dashingqi.module.home.net

import com.dashingqi.base.base.response.BaseResponse

/**
 * @author : zhangqi
 * @time : 2020/5/23
 * desc : 首页Banner数据
 */
data class HomeBannerResponse(val data: List<DataBean>) : BaseResponse() {

    data class DataBean(var desc: String, var id: Int, var imagePath: String, var isVisible: Int, var order: Int, var title: String, var type: Int, var url: String)
}