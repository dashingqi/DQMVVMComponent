package com.dashingqi.module.widget.openeye

import android.app.Application
import androidx.fragment.app.Fragment
import com.alibaba.android.arouter.launcher.ARouter
import com.dashingqi.base.base.viewmodel.BaseViewModel
import com.dashingqi.base.route.RoutePath
import com.dashingqi.library.service.providers.common.response.CommonClassifyResponse

/**
 * @author : zhangqi
 * @time : 2020/8/26
 * desc :
 */
class WidgetOpenEyeViewModel(application: Application) : BaseViewModel(application) {

    init {

    }


    /**
     * 标题数据
     */
    fun getTitleData(): ArrayList<CommonClassifyResponse> {
        return arrayListOf(CommonClassifyResponse("发现"), CommonClassifyResponse("推荐"), CommonClassifyResponse("日报"))
    }

    /**
     * 获取到对应的fragment数据
     */
    fun getFragmentData(): ArrayList<Fragment> {
        var fragmentDatas = ArrayList<Fragment>()
        fragmentDatas.add(ARouter.getInstance().build(RoutePath.Widget.WIDGET_OPEN_EYE_DISCOVER).navigation() as Fragment)
        fragmentDatas.add(ARouter.getInstance().build(RoutePath.Widget.WIDGET_OPEN_EYE_COMMEND).navigation() as Fragment)
        fragmentDatas.add(ARouter.getInstance().build(RoutePath.Widget.WIDGET_OPEN_EYE_DAILY).navigation() as Fragment)
        return fragmentDatas

    }

}