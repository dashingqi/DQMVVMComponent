package com.dashingqi.module.impl.json

import android.content.Context
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.facade.service.SerializationService
import com.google.gson.Gson
import java.lang.reflect.Type

/**
 * @author : zhangqi
 * @time : 2020/6/10
 * desc :
 */
@Route(path = "/impl/json", name = "对象序列化")
class JsonSerializationProvider : SerializationService {

    var gson = Gson()
    override fun <T : Any?> json2Object(input: String?, clazz: Class<T>?): T {
        return gson.fromJson(input, clazz)
    }

    override fun init(context: Context?) {
    }

    override fun object2Json(instance: Any?): String {
        return gson.toJson(instance)
    }

    override fun <T : Any?> parseObject(input: String?, clazz: Type?): T {
        return gson.fromJson(input, clazz)
    }
}