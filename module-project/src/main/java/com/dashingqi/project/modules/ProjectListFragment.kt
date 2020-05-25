package com.dashingqi.project.modules

import android.app.Application
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.dashingqi.base.base.fragment.BaseMvvMFragment
import com.dashingqi.base.base.viewmodel.ParamViewModelFactory
import com.dashingqi.project.databinding.ProjectListFragmentBinding

/**
 * @author : zhangqi
 * @time : 2020/5/25
 * desc :
 */
@Route(path = "/project/list_fragment")
class ProjectListFragment : BaseMvvMFragment<ProjectListFragmentBinding, ProjectListFragmentViewModel>() {

    @JvmField
    @Autowired(name = "cid")
    var cid: Int = 0
    override fun onLoad(view: View) {
        ARouter.getInstance().inject(this)
        super.onLoad(view)
    }

    override fun getViewModelFactory(): ViewModelProvider.Factory? {
        return ParamViewModelFactory(
                arrayOf(Application::class.java, Int::class.java),
                arrayOf(activity!!.application, cid)
        )
    }
}