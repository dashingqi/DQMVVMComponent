package com.dashingqi.project.modules

import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
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

    private val mFragments = ArrayList<Fragment>()
    override fun onLoad(view: View) {
        super.onLoad(view)


        dataBinding.viewPager.init(this, mFragments)
        dataBinding.magicIndicator.initAndBindVP(dataBinding.viewPager, viewModel.mProjectTreeData)
        createObserver()

    }

    private fun createObserver() {
        viewModel.mNotify.observe(this, Observer {
            if (mFragments.isNotEmpty()) {
                mFragments.clear()
            }
            it.forEach { data ->
                mFragments.add(ARouter.getInstance().build("/project/list_fragment").withInt("cid", data.id).navigation() as Fragment)
            }
            dataBinding.magicIndicator.navigator.notifyDataSetChanged()
            dataBinding.viewPager.adapter?.notifyDataSetChanged()
        })
    }
}