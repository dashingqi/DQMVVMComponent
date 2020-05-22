package com.dashingqi.project.modules

import com.alibaba.android.arouter.facade.annotation.Route
import com.dashingqi.base.base.fragment.BaseMvvMFragment
import com.dashingqi.project.databinding.ProjectFragmentBinding

/**
 * @author : zhangqi
 * @time : 2020/5/22
 * desc :
 */
@Route(path = "/project/project_fragment")
class ProjectFragment : BaseMvvMFragment<ProjectFragmentBinding, ProjectFragmentViewModel>() {
}