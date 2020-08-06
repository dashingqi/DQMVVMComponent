package com.dashingqi.module.pay.modules

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.dashingqi.alipay.bean.AliPayBean
import com.dashingqi.alipay.impl.AliPay
import com.dashingqi.base.route.RoutePath
import com.dashingqi.base.utils.toast
import com.dashingqi.dqpay.callback.IPayCallback
import com.dashingqi.library.service.providers.pay.PayService
import com.dashingqi.module.pay.R
import com.dashingqi.wxpay.bean.WXPayInfoBean
import com.dashingqi.wxpay.impl.WXPay
import kotlinx.android.synthetic.main.pay_test_activity.*


@Route(path = RoutePath.Pay.PAY_TEST_ACTIVITY)
class PayTestActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.pay_test_activity)

        btnWxPay.setOnClickListener {
            wxPay()
        }

        btnAliPay.setOnClickListener {
            aliPay()
        }


    }


    private fun wxPay() {
        //初始化微信支付，注册app
        WXPay.initWxPay(this, "")

        //构造支付数据
        var wxPayInfoBean = WXPayInfoBean()


        var wxPayCallback = object : IPayCallback<WXPayInfoBean> {
            override fun onCancel() {
                toast("微信支付取消了")
            }

            override fun onFail(errorCode: Int, errorMsg: String) {
                toast(errorMsg)
            }

            override fun onSuccess(data: WXPayInfoBean) {
                toast("微信支付成功")
            }

        }
        ARouter.getInstance().navigation(PayService::class.java).performWxPay(WXPay, this, wxPayInfoBean, wxPayCallback)

    }

    private fun aliPay() {

        var aliPayBean = AliPayBean()

        var aliPayCallback = object : IPayCallback<AliPayBean> {
            override fun onCancel() {
                toast("支付宝取消支付")
            }

            override fun onFail(errorCode: Int, errorMsg: String) {
                toast(errorMsg)
            }

            override fun onSuccess(data: AliPayBean) {
                toast("支付宝支付成功")
            }

        }

        ARouter.getInstance().navigation(PayService::class.java).performAliPay(AliPay(), this, aliPayBean, aliPayCallback)
    }
}