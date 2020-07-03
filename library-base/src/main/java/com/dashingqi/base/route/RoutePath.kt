package com.dashingqi.base.route

/**
 *
 * @ProjectName: DQMVVMComponent
 * @Package: com.dashingqi.base.route
 * @ClassName: RoutePath
 * @Author: DashingQI
 * @CreateDate: 2020/6/28 10:57 PM
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/6/28 10:57 PM
 * @UpdateRemark:
 * @Version: 1.0
 */
object RoutePath {

    object Impl{
       private const val MAIN = "/impl"
        const val EVENT_BUS = "${MAIN}/event_bus"
    }

    object Widget{
        private const val MAIN = "/widget"
        const val WIDGET_LETTER_VIEW = "${MAIN}/letter_view"
    }
}