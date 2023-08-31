package com.dashingqi.library.service.providers.common.response

/**
 * @author : zhangqi
 * @time : 2020/5/24
 * desc :通用的分类数据Bean
 */
data class CommonClassifyResponse(
    val name: String = "",
    val courseId: Int = 0,
    val id: Int = 0,
    val order: Int = 0,
    val parentChapterId: Int = 0,
    val userControlSetTop: Boolean = false,
    val visible: Int = 0
)