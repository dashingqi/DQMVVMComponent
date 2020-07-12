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

        /**
         * 最新博文
         */
        const val NEW_ARTICLE = "${MAIN}/newArticle"

        /**
         * 最新项目
         */
        const val NEW_PROJECT = "${MAIN}/newProject"
    }

    /**
     * 广场
     */
    object Square {

        private const val MAIN = "/square"

        const val SQUARE_LIST = "${MAIN}/squareList"
    }
}