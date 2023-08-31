package com.dashingqi.library.service.providers.common.ViewShowController

import android.view.View
import com.dashingqi.library.service.providers.common.response.CommonArticleResponse

/**
 *
 * @ProjectName: DQMVVMComponent
 * @Package: com.dashingqi.library.service.providers.common.ViewShowController
 * @ClassName: ViewShowController
 * @Author: DashingQI
 * @CreateDate: 2020/8/10 10:59 PM
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/8/10 10:59 PM
 * @UpdateRemark:
 * @Version: 1.0
 */
object ViewShowController {

    fun getTagOne(item: CommonArticleResponse): String {
        return item.tags.let {
            it[0].name
        }
    }

    fun isShowTagOne(item: CommonArticleResponse): Int {
        return if (getTagOne(item).isNullOrEmpty()) {
            View.GONE
        } else {
            View.VISIBLE
        }
    }

}