package com.dashingqi.module.home.modules.wenda

import android.app.Application
import com.alibaba.android.arouter.launcher.ARouter
import com.dashingqi.base.base.callback.LiveDataCallback
import com.dashingqi.base.base.response.BaseResponse
import com.dashingqi.base.base.viewmodel.BasePageViewModel
import com.dashingqi.base.utils.OnItemClickListener
import com.dashingqi.library.service.providers.collect.CollectService
import com.dashingqi.library.service.providers.common.response.CommonArticleResponse
import com.dashingqi.module.home.BR
import com.dashingqi.module.home.R
import com.dashingqi.module.home.net.HomeWenDaListResponse
import com.dashingqi.module.home.net.IHomeService

/**
 *
 * @ProjectName: DQMVVMComponent
 * @Package: com.dashingqi.module.home.modules.wenda
 * @ClassName: HomeWenDaViewModel
 * @Author: DashingQI
 * @CreateDate: 2020/8/10 10:00 PM
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/8/10 10:00 PM
 * @UpdateRemark:
 * @Version: 1.0
 */
class HomeWenDaViewModel(application: Application) : BasePageViewModel<CommonArticleResponse>(application) {

    init {
        itemBinding.bindExtra(BR.itemClick,onItemClickListener())
                .bindExtra(BR.onCollectClickListener,onCollectClickListener())
        refresh()
    }

    override fun getItemLayoutId(): Int {
        return R.layout.home_item_wenda
    }

    override fun requestData(page: Int) {
        IHomeService.instance.getWenDa(page).enqueue(LiveDataCallback<HomeWenDaListResponse>(baseLiveData)
                .bindSmartRefresh()
                .bindStateLayout()
                .doOnResponseSuccess { _, response ->
                    handleItemData(page, response.data.datas)
                })
    }

    override fun getStartPageNum(): Int {
        return 1
    }

    private fun onItemClickListener(): OnItemClickListener<CommonArticleResponse> {
        return object : OnItemClickListener<CommonArticleResponse> {
            override fun onItemClick(item: CommonArticleResponse) {
                ARouter.getInstance().build("/web/commonView").withString("url", item.link).withString("title", item.title).navigation()
            }

        }
    }

    /**
     * 收藏的事件
     */
    private fun onCollectClickListener(): OnItemClickListener<CommonArticleResponse> {
        return object : OnItemClickListener<CommonArticleResponse> {
            override fun onItemClick(item: CommonArticleResponse) {
                var callBack = LiveDataCallback<BaseResponse>(baseLiveData)
                        .bindLoading()
                        .doOnResponseSuccess { _, _ ->
                            item.collect = !item.collect
                            item.isCollectFiledMethod()
                        }
                ARouter.getInstance().navigation(CollectService::class.java).performCollectArticle(item.id.toString(), callBack, item.collect)
            }
        }
    }


}