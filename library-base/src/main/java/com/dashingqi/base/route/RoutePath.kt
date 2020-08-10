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

    object Impl {
        private const val MAIN = "/impl"
        const val EVENT_BUS = "${MAIN}/event_bus"
    }

    object Widget {
        private const val MAIN = "/widget"
        const val WIDGET_LETTER_VIEW = "${MAIN}/letter_view"
        const val WIDGET_COOR_BOTTOM = "${MAIN}/coor_bottom"
        const val WIDGET_MATERIAL = "${MAIN}/material"
        const val WIDGET_BOTTOM_SHEET_DIALOG = "${MAIN}/bottom_sheet_dialog"
        const val WIDGET_EX_FRESH = "${MAIN}/ex_fresh"
        const val WIDGET_BATTERY = "${MAIN}/battery"
        const val WIDGET_CIRCLE_PROGRESS = "${MAIN}/circleProgress"

        /**
         * 物流
         */
        const val WIDGET_LOGISTICS = "${MAIN}/logistics"
    }

    /**
     * 主页
     */
    object Home {
        private const val MAIN = "/home"

        const val HOME_ACTIVITY = "${MAIN}/mainActivity"

        /**
         * 最新博文
         */
        const val NEW_ARTICLE = "${MAIN}/newArticle"

        /**
         * 最新项目
         */
        const val NEW_PROJECT = "${MAIN}/newProject"

        /**
         * 每日一问
         */

        const val WEN_DA = "${MAIN}/wenDa"
    }


    object QrCode {
        private const val MAIN = "/qrcode"
        const val QRCODE_HW = "${MAIN}/scanHw"
    }

    /**
     * 广场
     */
    object Square {

        private const val MAIN = "/square"

        const val SQUARE_LIST = "${MAIN}/squareList"
    }

    /**
     * 收藏
     */
    object Collect {
        private const val MAIN = "/collect"
        const val COLLECT_SERVICE = "${MAIN}/service"
    }

    /**
     * 登录
     */
    object Login {
        private const val MAIN = "/login"
        const val LOGIN_ACTIVITY = "${MAIN}/loginActivity"
    }

    /**
     * 分享
     */
    object Share {
        private const val MAIN = "/share"
        const val SHARE_SERVICE = "${MAIN}/shareService"
    }

    object User {
        private const val MAIN = "/user"
        const val USER_SERVICE = "${MAIN}/service"
    }

    object Debug {
        private const val MAIN = "/debug"
        const val DEBUG_VIEW = "${MAIN}/debugView"
    }

    object Pay{
        private const val MAIN = "/pay"
        const val PAY_SERVICE = "${MAIN}/service"

        const val PAY_TEST_ACTIVITY = "${MAIN}/payTestActivity"
    }

}