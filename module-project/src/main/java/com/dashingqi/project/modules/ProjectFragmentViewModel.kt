package com.dashingqi.project.modules

import android.app.Application
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.alibaba.android.arouter.launcher.ARouter
import com.dashingqi.base.base.callback.LiveDataCallback
import com.dashingqi.base.base.viewmodel.BaseViewModel
import com.dashingqi.library.service.providers.common.response.CommonClassifyResponse
import com.dashingqi.project.net.IProjectService
import com.dashingqi.project.net.ProjectTreeResponse
import com.orhanobut.logger.Logger

/**
 * @author : zhangqi
 * @time : 2020/5/22
 * desc :
 */
class ProjectFragmentViewModel(application: Application) : BaseViewModel(application) {


    /**
     * 状态顶部标题的数据
     */
    val mProjectTreeData = arrayListOf<CommonClassifyResponse>()

    /**
     * 用来通知刷新界面
     */
    val mNotify = MutableLiveData<ArrayList<CommonClassifyResponse>>()

    val mFragmentData = arrayListOf<Fragment>()

    init {
        getProjectTreeData()
    }

    private fun getProjectTreeData() {
        IProjectService.INSTANCE.getProjectTree().enqueue(LiveDataCallback<ProjectTreeResponse>(baseLiveData)
                .doOnResponseSuccess { _, response ->
                    Logger.d("project tree data size is -----> ${response.data.size}")
                    if (mProjectTreeData.size > 0) mProjectTreeData.clear()
                    mProjectTreeData.addAll(response.data)
                    if (mFragmentData.size > 0) mFragmentData.clear()
                    mProjectTreeData.forEach {
                        mFragmentData.add(ARouter.getInstance().build("/project/list_fragment").withInt("cid",it.id).navigation() as Fragment)
                    }
                    mNotify.value = mProjectTreeData
                })
    }
}