package com.dashingqi.module.widget.openeye.constant

import com.dashingqi.module.widget.R
import com.dashingqi.module.widget.net.response.OpenEyeResponse

/**
 *
 * @ProjectName: DQMVVMComponent
 * @Package: com.dashingqi.module.widget.openeye.constant
 * @ClassName: TypeLayoutId
 * @Author: DashingQI
 * @CreateDate: 2020/8/26 10:38 PM
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/8/26 10:38 PM
 * @UpdateRemark:
 * @Version: 1.0
 */

/**
 * 根据Type获取到布局Id
 */
fun getLayoutId(item: OpenEyeResponse.ItemListBean): Int {
    return when (item.type) {
        TypeConfigUtil.TEXT_CARD -> R.layout.widget_open_eye_item_text
        TypeConfigUtil.PICTURE_FOLLOW_CARD -> R.layout.widget_open_eye_item_picture_follow_card
        TypeConfigUtil.FOLLOW_CARD -> R.layout.widget_open_eye_item_video_follow_card
        TypeConfigUtil.VIDEO_SMALL_CARD -> R.layout.widget_open_eye_item_samll_video
        TypeConfigUtil.BANNER2 -> R.layout.widget_open_eye_item_banner2
        TypeConfigUtil.BANNER -> R.layout.widget_open_eye_item_banner2
        TypeConfigUtil.BRIEF_CARD -> R.layout.widget_open_eye_item_brief_card
        else -> R.layout.widget_open_eye_item_text
    }
}