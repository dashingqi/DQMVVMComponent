package com.dashingqi.module.collect.app

import android.content.Context
import com.alibaba.android.arouter.facade.annotation.Route
import com.dashingqi.base.base.callback.LiveDataCallback
import com.dashingqi.base.base.response.BaseResponse
import com.dashingqi.base.route.RoutePath
import com.dashingqi.library.service.providers.collect.CollectService
import com.dashingqi.module.collect.net.ICollectService

/**
 * @author : zhangqi
 * @time : 2020/7/25
 * desc :
 */
@Route(path = RoutePath.Collect.COLLECT_SERVICE, name = "收藏模块的入口")
class CollectServiceImpl : CollectService {
    override fun collectArticle(id: String, callBack: LiveDataCallback<BaseResponse>) {

        ICollectService.instance.collectArticle(id).enqueue(callBack)

    }

    override fun unCollectArticle(id: String, callBack: LiveDataCallback<BaseResponse>) {
        ICollectService.instance.unCollectArticle(id).enqueue(callBack)
    }

    override fun performCollectArticle(id: String, callBack: LiveDataCallback<BaseResponse>, fresh: Boolean) {
        if (fresh) {
            unCollectArticle(id, callBack)
        } else {
            collectArticle(id, callBack)
        }
    }

    override fun init(context: Context?) {

    }
}