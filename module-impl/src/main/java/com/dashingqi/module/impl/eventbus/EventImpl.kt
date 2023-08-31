package com.dashingqi.module.impl.eventbus

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.OnLifecycleEvent
import com.alibaba.android.arouter.launcher.ARouter
import com.dashingqi.base.providers.eventbus.IEvent
import com.dashingqi.base.providers.eventbus.IEventBusProvider

/**
 *
 * @ProjectName: DQMVVMComponent
 * @Package: com.dashingqi.module.impl.eventbus
 * @ClassName: EventImpl
 * @Author: DashingQI
 * @CreateDate: 2020/6/28 10:59 PM
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/6/28 10:59 PM
 * @UpdateRemark:
 * @Version: 1.0
 */
class EventImpl:IEvent {

   private var observers = ArrayList<(data:Any?)->Unit>()
    var eventBusImpl = ARouter.getInstance().navigation(IEventBusProvider::class.java) as EventBusProviderImpl

    override fun sendEvent() {
        sendEvent(null)
    }

    override fun sendEvent(obj: Any?) {
        observers.forEach {
            it.invoke(obj)
        }
    }

    override fun observe(owner: LifecycleOwner, observer: (data: Any?) -> Unit) {
        observers.add(observer)
        owner.lifecycle.addObserver(object :LifecycleObserver{

            @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
            fun onDestroyPerform(){
                owner.lifecycle.removeObserver(this)
                removeObserver(observer)
            }
        })
    }

    override fun removeObserver(observer: (data: Any?) -> Unit) {
        observers.remove(observer)
        eventBusImpl.eventMap.remove(observer)

    }
}