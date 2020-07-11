package com.dashingqi.module.home.modules.newproject

import android.app.Application
import com.alibaba.android.arouter.launcher.ARouter
import com.dashingqi.base.base.callback.LiveDataCallback
import com.dashingqi.base.base.viewmodel.BasePageViewModel
import com.dashingqi.base.utils.OnItemClickListener
import com.dashingqi.library.service.providers.common.response.CommonArticleResponse
import com.dashingqi.module.home.BR
import com.dashingqi.module.home.R
import com.dashingqi.module.home.net.HomeProjectListResponse
import com.dashingqi.module.home.net.IHomeService
import com.orhanobut.logger.Logger

/**
 * @author : zhangqi
 * @time : 2020/7/11
 * desc :
 */
class HomeNewProjectViewModel(application: Application) : BasePageViewModel<CommonArticleResponse>(application) {

    init {
        itemBinding.bindExtra(BR.onNewProjectItemClick, onItemClickListener())
        refresh()
    }

    override fun getItemLayoutId(): Int {
        return R.layout.home_item_project
    }

    private fun onItemClickListener(): OnItemClickListener<CommonArticleResponse> {
        return object : OnItemClickListener<CommonArticleResponse> {
            override fun onItemClick(item: CommonArticleResponse) {
                ARouter.getInstance().build("/web/commonView").withString("url", item.link).withString("title", item.title).navigation()
            }

        }
    }

    override fun requestData(page: Int) {
        IHomeService.instance.getHomeProjectList(page).enqueue(LiveDataCallback<HomeProjectListResponse>(baseLiveData)
                .bindStateLayout()
                .bindStateLayout()
                .doOnResponseSuccess { _, response ->
                    Logger.d("home project list data size is -----> ${response.data.datas.size}")
                    handleItemData(page, response.data.datas)
                }
        )
    }
}