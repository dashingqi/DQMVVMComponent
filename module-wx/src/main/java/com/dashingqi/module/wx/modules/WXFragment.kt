package com.dashingqi.module.wx.modules

import com.alibaba.android.arouter.facade.annotation.Route
import com.dashingqi.base.base.fragment.BaseMvvMFragment
import com.dashingqi.module.wx.databinding.WxFragmentBinding

/**
 * @author : zhangqi
 * @time : 2020/5/22
 * desc :
 */
@Route(path = "/wx/wx_fragment")
class WXFragment : BaseMvvMFragment<WxFragmentBinding, WXFragmentViewModel>() {
}