package com.dashingqi.module.pay.app

import android.app.Activity
import android.content.Context
import com.alibaba.android.arouter.facade.annotation.Route
import com.dashingqi.alipay.bean.AliPayBean
import com.dashingqi.base.route.RoutePath
import com.dashingqi.dqpay.callback.IPayCallback
import com.dashingqi.dqpay.strategy.IPayStrategy
import com.dashingqi.dqpay.utils.PayUtils
import com.dashingqi.library.service.providers.pay.PayService
import com.dashingqi.wxpay.bean.WXPayInfoBean

/**
 * @author : zhangqi
 * @time : 2020/8/6
 * desc :
 */

@Route(path = RoutePath.Pay.PAY_SERVICE, name = "支付模块的入口")
class PayServiceImpl : PayService {
    override fun performWxPay(
        strategy: IPayStrategy<WXPayInfoBean>,
        activity: Activity,
        payInfoBean: WXPayInfoBean,
        payCallback: IPayCallback<WXPayInfoBean>
    ) {
        PayUtils.pay(strategy, activity, payInfoBean, payCallback)
    }

    override fun performAliPay(
        strategy: IPayStrategy<AliPayBean>,
        activity: Activity,
        payInfoBean: AliPayBean,
        payCallback: IPayCallback<AliPayBean>
    ) {
        PayUtils.pay(strategy, activity, payInfoBean, payCallback)
    }

    override fun init(context: Context?) {
    }

}