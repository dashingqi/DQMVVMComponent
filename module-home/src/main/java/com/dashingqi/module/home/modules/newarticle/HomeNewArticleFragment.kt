package com.dashingqi.module.home.modules.newarticle

import com.alibaba.android.arouter.facade.annotation.Route
import com.dashingqi.base.base.fragment.BaseMvvMFragment
import com.dashingqi.base.route.RoutePath
import com.dashingqi.module.home.databinding.HomeNewArticleFragmentBinding

/**
 * @author : zhangqi
 * @time : 2020/7/11
 * desc : 最新博文
 */

@Route(path = RoutePath.Home.NEW_ARTICLE)
 class HomeNewArticleFragment : BaseMvvMFragment<HomeNewArticleFragmentBinding, HomeNewArticleViewModel>() {
}