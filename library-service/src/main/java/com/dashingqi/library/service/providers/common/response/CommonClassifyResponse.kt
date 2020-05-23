package com.dashingqi.library.service.providers.common.response

/**
 * @author : zhangqi
 * @time : 2020/5/24
 * desc :通用的分类数据Bean
 */
data class CommonClassifyResponse(var courseId: Int = 0, var id: Int = 0,
                                  var name: String = "", var order: Int = 0, var parentChapterId: Int = 0,
                                  var userControlSetTop: Boolean,
                                  var visible: Int = 0) {
}