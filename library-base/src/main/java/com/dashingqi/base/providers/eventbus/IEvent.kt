package com.dashingqi.base.providers.eventbus

import androidx.lifecycle.LifecycleOwner

/**
 *
 * @ProjectName: DQMVVMComponent
 * @Package: com.dashingqi.base.providers.eventbus
 * @ClassName: IEvent
 * @Author: DashingQI
 * @CreateDate: 2020/6/28 10:52 PM
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/6/28 10:52 PM
 * @UpdateRemark:
 * @Version: 1.0
 */
interface IEvent {

    fun sendEvent()
    fun sendEvent(obj: Any?)

    /**
     * 把函数作为参数
     * 执行这个函数 invoke
     */
    fun observe(owner: LifecycleOwner, observer: (data: Any?) -> Unit)

    fun removeObserver(observer: (data: Any?) -> Unit)
}