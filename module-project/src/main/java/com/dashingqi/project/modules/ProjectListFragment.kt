package com.dashingqi.project.modules

import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.dashingqi.base.base.fragment.BaseMvvMFragment
import com.dashingqi.project.databinding.ProjectListFragmentBinding

/**
 * @author : zhangqi
 * @time : 2020/5/25
 * desc :
 */
@Route(path = "/project/list_fragment")
class ProjectListFragment : BaseMvvMFragment<ProjectListFragmentBinding, ProjectListFragmentViewModel>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

}