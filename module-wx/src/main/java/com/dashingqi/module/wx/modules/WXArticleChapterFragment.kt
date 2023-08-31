package com.dashingqi.module.wx.modules

import android.app.Application
import android.graphics.Rect
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.dashingqi.base.base.fragment.BaseMvvMFragment
import com.dashingqi.base.base.viewmodel.ParamViewModelFactory
import com.dashingqi.base.utils.DensityUtils
import com.dashingqi.library.service.providers.widget.CommonItemDecoration
import com.dashingqi.module.wx.databinding.WxArticleChapterFragmentBinding
import kotlinx.android.synthetic.main.wx_article_chapter_fragment.*

/**
 * @author : zhangqi
 * @time : 2020/5/23
 * desc : 某个公众号下的文章列表
 */
@Route(path = "/wx/article_list_fragment")
class WXArticleChapterFragment : BaseMvvMFragment<WxArticleChapterFragmentBinding, WXArticleChapterFragmentViewModel>() {

    @JvmField
    @Autowired(name = "id")
    var articleId = 0

    override fun onLoad(view: View) {
        ARouter.getInstance().inject(this)
        wxRV.addItemDecoration(CommonItemDecoration())
        super.onLoad(view)
    }

    override fun getViewModelFactory(): ViewModelProvider.Factory {
        return ParamViewModelFactory(
                arrayOf(Application::class.java, Int::class.java),
                arrayOf(activity?.application, articleId)
        )
    }
}