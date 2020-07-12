package com.dashingqi.module.square.modules.list

import android.app.Application
import com.dashingqi.base.base.callback.LiveDataCallback
import com.dashingqi.base.base.viewmodel.BaseViewModel
import com.dashingqi.module.square.net.ISquareService
import com.dashingqi.module.square.net.SquareListResponse
import com.orhanobut.logger.Logger

/**
 * @author : zhangqi
 * @time : 2020/7/12
 * desc :
 */
class SquareListViewModel(application: Application) : BaseViewModel(application) {

    init {
        getData()
    }

    private fun getData() {
        ISquareService.instance.getUserArticleList(0)
                .enqueue(LiveDataCallback<SquareListResponse>(baseLiveData)
                        .bindStateLayout()
                        .doOnResponseSuccess { _, response ->
                            Logger.d("size ---> ${response.data.datas.size}")
                        }
                )
    }
}