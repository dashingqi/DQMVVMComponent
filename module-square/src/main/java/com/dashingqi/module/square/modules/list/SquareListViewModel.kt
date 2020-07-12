package com.dashingqi.module.square.modules.list

import android.app.Application
import com.alibaba.android.arouter.launcher.ARouter
import com.dashingqi.base.base.callback.LiveDataCallback
import com.dashingqi.base.base.viewmodel.BasePageViewModel
import com.dashingqi.base.utils.OnItemClickListener
import com.dashingqi.library.service.providers.common.response.CommonArticleResponse
import com.dashingqi.module.square.BR
import com.dashingqi.module.square.R
import com.dashingqi.module.square.net.ISquareService
import com.dashingqi.module.square.net.SquareListResponse

/**
 * @author : zhangqi
 * @time : 2020/7/12
 * desc :
 */
class SquareListViewModel(application: Application) : BasePageViewModel<CommonArticleResponse>(application) {

    init {
        itemBinding.bindExtra(BR.onItemClick, onItemClickListener())
        refresh()
    }

    override fun requestData(page: Int) {
        ISquareService.instance.getUserArticleList(page)
                .enqueue(LiveDataCallback<SquareListResponse>(baseLiveData)
                        .bindStateLayout()
                        .bindSmartRefresh()
                        .doOnResponseSuccess { _, response ->
                            handleItemData(page, response.data.datas)
                        }
                )
    }

    override fun getItemLayoutId(): Int {
        return R.layout.square_item_list
    }

    private fun onItemClickListener(): OnItemClickListener<CommonArticleResponse> {
        return object : OnItemClickListener<CommonArticleResponse> {
            override fun onItemClick(item: CommonArticleResponse) {
                ARouter.getInstance().build("/web/commonView").withString("url", item.link).withString("title", item.title).navigation()
            }
        }
    }
}