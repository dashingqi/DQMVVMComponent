package com.dashingqi.base.ext

import java.lang.reflect.ParameterizedType

/**
 * @author : zhangqi
 * @time : 2020/4/25
 * desc :
 */

fun <VM> getVmClass(obj: Any): Class<VM> {
    return (obj.javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[1] as Class<VM>
}

fun <DB> getDbClass(obj: Any): Class<DB> {
    return (obj.javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[0] as Class<DB>
}