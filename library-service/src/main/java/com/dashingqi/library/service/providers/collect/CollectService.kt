package com.dashingqi.library.service.providers.collect

import com.alibaba.android.arouter.facade.template.IProvider
import com.dashingqi.base.base.callback.LiveDataCallback
import com.dashingqi.base.base.response.BaseResponse

/**
 * @author : zhangqi
 * @time : 2020/7/25
 * desc :
 */
interface CollectService : IProvider {

    /**
     * 收藏文章
     */
    fun collectArticle(id: String,callBack:LiveDataCallback<BaseResponse>)

    /**
     * 取消收藏
     */
    fun unCollectArticle(id: String,callBack:LiveDataCallback<BaseResponse>)

    /**
     * 用于收藏或者取消文章
     */
    fun performCollectArticle(id: String,callBack:LiveDataCallback<BaseResponse>,fresh:Boolean)
}