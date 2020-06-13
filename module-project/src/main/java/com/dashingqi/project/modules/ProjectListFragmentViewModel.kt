package com.dashingqi.project.modules

import android.app.Application
import com.alibaba.android.arouter.launcher.ARouter
import com.dashingqi.base.base.callback.LiveDataCallback
import com.dashingqi.base.base.viewmodel.BasePageViewModel
import com.dashingqi.base.base.viewmodel.BaseViewModel
import com.dashingqi.base.utils.OnItemClickListener
import com.dashingqi.library.service.providers.common.response.CommonArticleResponse
import com.dashingqi.project.BR
import com.dashingqi.project.R
import com.dashingqi.project.net.IProjectService
import com.dashingqi.project.net.ProjectTreeListResponse
import com.orhanobut.logger.Logger

/**
 * @author : zhangqi
 * @time : 2020/5/25
 * desc :
 */
class ProjectListFragmentViewModel(application: Application, var cid: Int) : BasePageViewModel<CommonArticleResponse>(application) {

    init {
     itemBinding.bindExtra(BR.onItemClick,onItemClickListener())
        refresh()
    }

    override fun requestData(page: Int) {
        IProjectService.INSTANCE.getProjectList(page, cid).enqueue(LiveDataCallback<ProjectTreeListResponse>(baseLiveData)
                .bindSmartRefresh()
                .bindStateLayout()
                .doOnResponseSuccess { _, response ->
                    Logger.d("project list response size is ------->  ${response.data.datas.size}")
                    handleItemData(page, response.data.datas)
                }
        )
    }

    override fun getItemLayoutId(): Int {
        return R.layout.project_item_list
    }

    /**
     * 指定开始的请求页码
     */
    override fun getStartPageNum(): Int {
        return 0
    }

    override fun getPageSize(): Int {
        return 15
    }

    private fun onItemClickListener():OnItemClickListener<CommonArticleResponse>{
        return object :OnItemClickListener<CommonArticleResponse>{
            override fun onItemClick(item: CommonArticleResponse) {
                ARouter.getInstance().build("/web/commonView").withString("url",item.link).withString("title",item.title).navigation()
            }

        }
    }
}