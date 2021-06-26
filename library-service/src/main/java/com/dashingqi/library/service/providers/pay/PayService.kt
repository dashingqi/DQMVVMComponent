package com.dashingqi.library.service.providers.pay

import android.app.Activity
import com.alibaba.android.arouter.facade.template.IProvider
//import com.dashingqi.alipay.bean.AliPayBean
//import com.dashingqi.dqpay.bean.IPayInfoBean
//import com.dashingqi.dqpay.callback.IPayCallback
//import com.dashingqi.dqpay.strategy.IPayStrategy
//import com.dashingqi.wxpay.bean.WXPayInfoBean

/**
 * @author : zhangqi
 * @time : 2020/8/6
 * desc :
 */
interface PayService : IProvider {

    /**
     * 执行微信支付支付
     */
//    fun performWxPay(strategy: IPayStrategy<WXPayInfoBean>, activity: Activity, payInfoBean: WXPayInfoBean, payCallback: IPayCallback<WXPayInfoBean>)

//    /**
//     * 执行支付宝支付
//     */
//    fun performAliPay(strategy: IPayStrategy<AliPayBean>, activity: Activity, payInfoBean: AliPayBean, payCallback: IPayCallback<AliPayBean>)

}