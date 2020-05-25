package com.dashingqi.base.application

import android.app.Application
import com.dashingqi.base.providers.application.IApplicationProvider
import com.dashingqi.base.widget.smart.PremixHeader
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
        Logger.d("base-application-create")
        initSmartRefresh()

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
}