package com.dashingqi.module.collect.modules.list

import android.app.Application
import androidx.databinding.ObservableArrayList
import com.dashingqi.base.base.callback.LiveDataCallback
import com.dashingqi.base.base.viewmodel.BaseViewModel
import com.dashingqi.library.service.providers.common.response.CommonArticleResponse
import com.dashingqi.library_res.BR
import com.dashingqi.module.collect.net.ICollectService
import com.dashingqi.module.collect.net.response.CollectListResponse
import com.dashingqi.moudle.collect.R
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
class CollectListViewModel(application: Application) : BaseViewModel(application) {

    var items = ObservableArrayList<CommonArticleResponse>()

    val onItemBind: OnItemBind<CommonArticleResponse> = OnItemBind { itemBinding, position, item ->
        itemBinding[BR.item] = if (item.envelopePic.isNotEmpty()) R.layout.service_project_item_list else R.layout.service_wx_item_article
    }


    init {
        ICollectService.instance.getCollectList(0).enqueue(LiveDataCallback<CollectListResponse>(baseLiveData)
                .bindSmartRefresh()
                .bindStateLayout()
                .doOnResponseSuccess { call, response ->
                    items.addAll(response.data.datas)
                }
        )
    }


}