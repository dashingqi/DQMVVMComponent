package com.dashingqi.module.impl.eventbus

import android.content.Context
import com.alibaba.android.arouter.facade.annotation.Route
import com.dashingqi.base.providers.eventbus.IEvent
import com.dashingqi.base.providers.eventbus.IEventBusProvider
import com.dashingqi.base.route.RoutePath

/**
 *
 * @ProjectName: DQMVVMComponent
 * @Package: com.dashingqi.module.impl.eventbus
 * @ClassName: EventBusImpl
 * @Author: DashingQI
 * @CreateDate: 2020/6/28 10:55 PM
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/6/28 10:55 PM
 * @UpdateRemark:
 * @Version: 1.0
 *
 */

@Route(path = RoutePath.Impl.EVENT_BUS,name = "事件模块入口")
class EventBusProviderImpl:IEventBusProvider {

    var eventMap = HashMap<Any,IEvent>()
    override fun with(flag: Any): IEvent {
        var iEvent = eventMap[flag]
        if (iEvent==null){

            iEvent = EventImpl()

            eventMap[flag] = iEvent
        }
        return iEvent

    }

    override fun init(context: Context?) {

    }
}