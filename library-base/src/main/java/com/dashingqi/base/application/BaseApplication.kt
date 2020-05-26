package com.dashingqi.base.application

import android.app.Application
import com.dashingqi.base.providers.application.IApplicationProvider
import com.dashingqi.base.widget.smart.PremixHeader
import com.dashingqi.base.widget.state.DQStateLayout
import com.dashingqi.library_base.R
import com.orhanobut.logger.Logger
import com.scwang.smartrefresh.layout.SmartRefreshLayout
import com.scwang.smartrefresh.layout.footer.ClassicsFooter

/**
 * @author : zhangqi
 * @time : 2020/5/20
 * desc :
 */
class BaseApplication : IApplicationProvider {
    override fun init(application: Application) {

    }

    override fun onCreate() {
        initSmartRefresh()
        initStateLayout()

    }

    override fun onLowMemory() {

    }

    override fun onTerminate() {

    }

    override fun getPriority(): Int {
        return 100
    }

    /**
     * 初始化设置下拉刷新和上滑加载
     */
    private fun initSmartRefresh() {
        SmartRefreshLayout.setDefaultRefreshHeaderCreator { context, layout ->
            return@setDefaultRefreshHeaderCreator PremixHeader(context)
        }

        SmartRefreshLayout.setDefaultRefreshFooterCreator { context, layout ->
            return@setDefaultRefreshFooterCreator ClassicsFooter(context)
        }
    }

    /**
     * 初始化状态布局
     */
    private fun initStateLayout() {
        DQStateLayout.setDefaultLoadLayout(R.layout.base_common_state_loading_layout)
    }
}