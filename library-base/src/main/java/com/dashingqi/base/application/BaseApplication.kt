package com.dashingqi.base.application

import android.app.Application
import com.alibaba.android.arouter.facade.service.SerializationService
import com.alibaba.android.arouter.launcher.ARouter
import com.dashingqi.base.providers.application.IApplicationProvider
import com.dashingqi.base.widget.smart.PremixHeader
import com.dashingqi.base.widget.state.DQStateLayout
import com.dashingqi.dqlog.DQJsonParse
import com.dashingqi.dqlog.DQLog
import com.dashingqi.dqlog.DQLogInterceptor
import com.dashingqi.library_base.R
import com.didichuxing.doraemonkit.DoraemonKit
import com.scwang.smartrefresh.layout.SmartRefreshLayout
import com.scwang.smartrefresh.layout.footer.ClassicsFooter
import java.lang.reflect.Type

/**
 * @author : zhangqi
 * @time : 2020/5/20
 * desc :
 */
class BaseApplication : IApplicationProvider {
    override fun init(application: Application) {
        instance = application

    }

    override fun onCreate() {
        initSmartRefresh()
        initStateLayout()
        initDQLog()
        DoraemonKit.install(instance)

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
        DQStateLayout.setDefaultLoadLayout(R.layout.base_common_state_load_layout)
        DQStateLayout.setDefaultErrorLayout(R.layout.base_common_state_error_layout)
        DQStateLayout.setDefaultEmptyLayout(R.layout.base_common_state_empty_layout)
    }

    /**
     * 初始化Log
     */
    private fun initDQLog(){
        DQLog.setDQLogInterceptor(object :DQLogInterceptor{
            override fun process(level: Int): Boolean {
               return true
            }

        })

        val serializationService = ARouter.getInstance().navigation(SerializationService::class.java)
        DQLog.setDQJsonParse(object :DQJsonParse{
            override fun <T> jsonToObject(json: String?, classType: Type?): T {
                return serializationService.parseObject(json,classType)
            }

            override fun objectToJson(any: Any?): String? {
               return serializationService.object2Json(any)
            }

        })
    }

    companion object {
        lateinit var instance: Application
    }
}