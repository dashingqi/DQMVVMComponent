package com.dashingqi.module.wx.modules

import android.app.Application
import com.alibaba.android.arouter.launcher.ARouter
import com.dashingqi.base.base.callback.LiveDataCallback
import com.dashingqi.base.base.viewmodel.BasePageViewModel
import com.dashingqi.base.eventbus.EventBusPath
import com.dashingqi.base.providers.eventbus.IEventBusProvider
import com.dashingqi.base.utils.OnItemClickListener
import com.dashingqi.library.service.providers.common.response.CommonArticleResponse
import com.dashingqi.module.wx.BR
import com.dashingqi.module.wx.R
import com.dashingqi.module.wx.net.IWxService
import com.dashingqi.module.wx.net.WxArticleListResponse
import com.orhanobut.logger.Logger

/**
 * @author : zhangqi
 * @time : 2020/5/23
 * desc :
 */
class WXArticleChapterFragmentViewModel(application: Application, var id: Int) : BasePageViewModel<CommonArticleResponse>(application) {
    init {
        Logger.d("viewModel ---> id ----> $id")
        itemBinding.bindExtra(BR.onItemClick,onItemClickListener())
        refresh()
    }


    override fun requestData(page: Int) {
        IWxService.INSTANCE.getWxArticleList(id, page).enqueue(LiveDataCallback<WxArticleListResponse>(baseLiveData)
                .bindSmartRefresh()
                .bindStateLayout()
                .doOnResponseSuccess { _, response ->
                    Logger.d("size = ${response.data.datas.size}")
                    handleItemData(page, response.data.datas)
                    ARouter.getInstance().navigation(IEventBusProvider::class.java).with(EventBusPath.Test.TEST_PATH)
                            .sendEvent(EventBusPath.Test.TEST_PATH_FLAG)
                })
    }

    override fun getItemLayoutId(): Int = R.layout.wx_item_article

    private fun onItemClickListener():OnItemClickListener<CommonArticleResponse>{
        return object :OnItemClickListener<CommonArticleResponse>{
            override fun onItemClick(item: CommonArticleResponse) {
                ARouter.getInstance().build("/web/commonView").withString("url",item.link).withString("title",item.title).navigation()
            }

        }
    }


}