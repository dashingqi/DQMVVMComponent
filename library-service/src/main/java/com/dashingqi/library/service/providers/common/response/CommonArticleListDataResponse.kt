package com.dashingqi.library.service.providers.common.response

/**
 * @author : zhangqi
 * @time : 2020/5/25
 * desc :
 */
class CommonArticleListDataResponse(var curPage: Int, var datas: List<CommonArticleResponse>, var offset: Int, var over: Boolean, var pageCount: Int, var size: Int, var total: Int) {
}