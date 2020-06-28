package com.dashingqi.base.providers.eventbus

import com.alibaba.android.arouter.facade.template.IProvider

/**
 *
 * @ProjectName: DQMVVMComponent
 * @Package: com.dashingqi.base.providers.eventbus
 * @ClassName: IEventBusProvider
 * @Author: DashingQI
 * @CreateDate: 2020/6/28 10:50 PM
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/6/28 10:50 PM
 * @UpdateRemark:
 * @Version: 1.0
 */
interface IEventBusProvider : IProvider {

    fun with(flag:Any):IEvent
}