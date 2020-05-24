package com.dashingqi.project.modules

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.alibaba.android.arouter.facade.annotation.Route
import com.dashingqi.base.base.fragment.BaseMvvMFragment
import com.dashingqi.library.service.providers.common.initcode.init
import com.dashingqi.library.service.providers.common.initcode.initAndBindVP
import com.dashingqi.project.databinding.ProjectFragmentBinding

/**
 * @author : zhangqi
 * @time : 2020/5/22
 * desc :
 */
@Route(path = "/project/project_fragment")
class ProjectFragment : BaseMvvMFragment<ProjectFragmentBinding, ProjectFragmentViewModel>() {

    override fun onLoad(view: View) {
        super.onLoad(view)

        createObserver()
        dataBinding.magicIndicator.initAndBindVP(dataBinding.viewPager, viewModel.mProjectTreeData)
        dataBinding.viewPager.init(this, viewModel.mFragmentData)
    }

    private fun createObserver() {
        viewModel.mNotify.observe(this, Observer {
            dataBinding.magicIndicator.navigator.notifyDataSetChanged()
            dataBinding.viewPager.adapter?.notifyDataSetChanged()
        })
    }
}