package com.dashingqi.module.wx.modules

import android.app.Application
import android.graphics.Rect
import android.icu.lang.UCharacter.GraphemeClusterBreak.V
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.dashingqi.base.base.fragment.BaseMvvMFragment
import com.dashingqi.base.base.viewmodel.ParamViewModelFactory
import com.dashingqi.base.utils.DensityUtils
import com.dashingqi.module.wx.R
import com.dashingqi.module.wx.databinding.WxArticleChapterFragmentBinding
import com.orhanobut.logger.Logger
import kotlinx.android.synthetic.main.wx_article_chapter_fragment.*
import kotlinx.android.synthetic.main.wx_item_article.view.*

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
        wxRV.addItemDecoration(object : RecyclerView.ItemDecoration() {
            override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
              if(  parent.getChildAdapterPosition(view) ==0){
                  outRect.top = DensityUtils.dip2pxInt(context!!, 16f)
              }
                outRect.left = DensityUtils.dip2pxInt(context!!, 16f)
                outRect.right = DensityUtils.dip2pxInt(context!!, 16f)
                outRect.bottom = DensityUtils.dip2pxInt(context!!, 16f)

            }
        })
        super.onLoad(view)
    }

    override fun getViewModelFactory(): ViewModelProvider.Factory? {
        return ParamViewModelFactory(
                arrayOf(Application::class.java, Int::class.java),
                arrayOf(activity?.application, articleId)
        )
    }
}