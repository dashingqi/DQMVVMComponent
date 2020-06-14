package com.dashingqi.module.home.modules.main

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.alibaba.android.arouter.launcher.ARouter
import com.dashingqi.base.base.callback.LiveDataCallback
import com.dashingqi.base.base.response.BaseResponse
import com.dashingqi.base.base.viewmodel.BasePageViewModel
import com.dashingqi.base.base.viewmodel.BaseViewModel
import com.dashingqi.base.utils.OnItemClickListener
import com.dashingqi.library.service.providers.common.response.CommonArticleResponse
import com.dashingqi.module.home.BR
import com.dashingqi.module.home.R
import com.dashingqi.module.home.net.HomeArticleListResponse
import com.dashingqi.module.home.net.HomeBannerResponse
import com.dashingqi.module.home.net.IHomeService
import com.orhanobut.logger.Logger
import kotlinx.android.synthetic.main.home_fragment.*

/**
 * @author : zhangqi
 * @time : 2020/5/21
 * desc :
 */
class HomeFragmentViewModel(application: Application) : BasePageViewModel<CommonArticleResponse>(application) {

    init {
        itemBinding.bindExtra(BR.itemClick, onItemClickListener())
        getBannerData()
        refresh()
    }

    var bannerData = MutableLiveData<List<HomeBannerResponse.DataBean>>()

    /**
     * 获取到首页Banner数据
     */
    private fun getBannerData() {
        IHomeService.instance.getBannerData().enqueue(LiveDataCallback<HomeBannerResponse>(baseLiveData)
                .bindStateLayout()
                .doOnResponseSuccess { _, response ->
                    var data = response.data
                    bannerData.value = data
                }
        )
    }

    /**
     * 获取文章列表
     */
    override fun requestData(page: Int) {
        IHomeService.instance.getHomeArticleList(page).enqueue(LiveDataCallback<HomeArticleListResponse>(baseLiveData)
                .bindSmartRefresh()
                .bindStateLayout()
                .doOnResponseSuccess { _, response ->
                    Logger.d("home article list data size is -----> ${response.data.datas.size}")
                    handleItemData(page, response.data.datas)
                }
        )
    }

    override fun getItemLayoutId(): Int {
        return R.layout.home_article_item
    }

    private fun onItemClickListener(): OnItemClickListener<CommonArticleResponse> {
        return object : OnItemClickListener<CommonArticleResponse> {
            override fun onItemClick(item: CommonArticleResponse) {
                ARouter.getInstance().build("/web/commonView").withString("url", item.link).withString("title", item.title).navigation()
            }

        }
    }
}