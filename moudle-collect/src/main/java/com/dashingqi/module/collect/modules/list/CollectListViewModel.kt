package com.dashingqi.module.collect.modules.list

import android.app.Application
import androidx.databinding.ObservableArrayList
import com.alibaba.android.arouter.launcher.ARouter
import com.dashingqi.base.base.callback.LiveDataCallback
import com.dashingqi.base.base.response.BaseResponse
import com.dashingqi.base.base.viewmodel.BaseMultiplyPageViewModel
import com.dashingqi.base.base.viewmodel.BaseViewModel
import com.dashingqi.base.ext.request
import com.dashingqi.base.utils.OnItemClickListener
import com.dashingqi.dqlog.DQLog
import com.dashingqi.library.service.providers.collect.CollectService
import com.dashingqi.library.service.providers.common.response.CommonArticleResponse
import com.dashingqi.module.collect.app.CollectServiceImpl
import com.dashingqi.module.collect.net.ICollectService
import com.dashingqi.module.collect.net.response.CollectListResponse
import com.dashingqi.moudle.collect.BR
import com.dashingqi.moudle.collect.R
import me.tatarka.bindingcollectionadapter2.ItemBinding
import me.tatarka.bindingcollectionadapter2.OnItemBind


/**
 *
 * @ProjectName: DQMVVMComponent
 * @Package: com.dashingqi.module.collect.modules.list
 * @ClassName: CollectListViewModel
 * @Author: DashingQI
 * @CreateDate: 2020/8/24 11:30 PM
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/8/24 11:30 PM
 * @UpdateRemark:
 * @Version: 1.0
 */
class CollectListViewModel(application: Application) : BaseMultiplyPageViewModel<CommonArticleResponse>(application) {


    val onItemBind: OnItemBind<CommonArticleResponse> = OnItemBind { itemBinding, position, item ->
        itemBinding[BR.item] = if (item.envelopePic.isNotEmpty()) R.layout.service_project_item_list else R.layout.service_wx_item_article
    }

    val itemBinding = ItemBinding.of(onItemBind)
            .bindExtra(BR.onCollectArticleClickListener, onCollectionListener())

    init {
        refresh()
    }

    override fun requestData(page: Int) {

        ICollectService.instance.getCollectList(page).enqueue(LiveDataCallback<CollectListResponse>(baseLiveData)
                .bindSmartRefresh()
                .bindStateLayout()
                .doOnResponseSuccess { call, response ->
                    response.data.datas.forEach {
                        it.collect = true
                    }
                    handleItemData(page, response.data.datas)
                }
        )

        testScope(page)
    }

    private fun testScope(page:Int){
        request {
           val data =  ICollectService.instance.getSuspendCollectList(page)
            DQLog.d("data size  = ${data.data.datas.size}")
        }
    }


    private fun onCollectionListener(): OnItemClickListener<CommonArticleResponse> {
        return object : OnItemClickListener<CommonArticleResponse> {
            override fun onItemClick(item: CommonArticleResponse) {
                val callback = LiveDataCallback<BaseResponse>(baseLiveData).bindLoading().doOnResponseSuccess { call, response ->
                    val itemData = items.filter { it.id == item.id }[0]
                    if (items.contains(itemData)) {
                        items.remove(itemData)
                    }
                }
                ARouter.getInstance().navigation(CollectService::class.java).performCollectArticle(item.id.toString(), callback, item.collect)

            }
        }
    }


}