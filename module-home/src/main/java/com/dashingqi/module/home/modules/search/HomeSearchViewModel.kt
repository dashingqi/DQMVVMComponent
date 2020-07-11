package com.dashingqi.module.home.modules.search

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.alibaba.android.arouter.launcher.ARouter
import com.dashingqi.base.base.callback.LiveDataCallback
import com.dashingqi.base.base.viewmodel.BasePageViewModel
import com.dashingqi.base.utils.OnItemClickListener
import com.dashingqi.library.service.providers.common.response.CommonArticleResponse
import com.dashingqi.module.home.BR
import com.dashingqi.module.home.R
import com.dashingqi.module.home.net.HomeArticleListResponse
import com.dashingqi.module.home.net.IHomeService

/**
 * @author : zhangqi
 * @time : 2020/6/15
 * desc :
 */
class HomeSearchViewModel(application: Application) : BasePageViewModel<CommonArticleResponse>(application) {

    val key = MutableLiveData<String>()

    init {
        itemBinding.bindExtra(BR.itemClick, onItemClickListener())
    }

    override fun requestData(page: Int) {
        IHomeService.instance.homeSearch(0, key.value ?: "")
                .enqueue(LiveDataCallback<HomeArticleListResponse>(baseLiveData)
                        .bindStateLayout()
                        .bindSmartRefresh()
                        .doOnResponseSuccess { call, response ->
                            handleItemData(page, response.data.datas)
                        }
                )
    }

    override fun getItemLayoutId(): Int {
        return R.layout.home_item_article
    }

    /**
     * 搜索到的条目点击事件
     */
    private fun onItemClickListener(): OnItemClickListener<CommonArticleResponse> {
        return object : OnItemClickListener<CommonArticleResponse> {
            override fun onItemClick(item: CommonArticleResponse) {
                ARouter.getInstance().build("/web/commonView").withString("url", item.link).withString("title", item.title).navigation()
            }

        }
    }

}