package com.dashingqi.module.impl.mmkv

import android.content.Context
import androidx.annotation.Keep
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.service.SerializationService
import com.alibaba.android.arouter.launcher.ARouter
import com.dashingqi.base.providers.mmkv.IKV
import com.tencent.mmkv.MMKV
import java.lang.reflect.Type

/**
 * @author : zhangqi
 * @time : 2020/6/10
 * desc :
 */
class DQMMKV : IKV {

    @Keep
    @Autowired
    lateinit var serializationService: SerializationService

    var mmkv: MMKV

    var context: Context

    constructor(context: Context) {
        ARouter.getInstance().inject(this)
        mmkv = MMKV.defaultMMKV()
        this.context = context
    }

    constructor(context: Context, id: String) {
        ARouter.getInstance().inject(this)
        mmkv = MMKV.mmkvWithID(id)
        this.context = context
    }

    override fun putBoolean(key: String, value: Boolean) {
        mmkv.encode(key, value)
    }

    override fun putString(key: String, value: String) {
        mmkv.encode(key, value)
    }

    override fun putInt(key: String, value: Int) {
        mmkv.encode(key, value)
    }

    override fun putLong(key: String, value: Long) {
        mmkv.encode(key, value)
    }


    override fun putFloat(key: String, value: Float) {
        mmkv.encode(key, value)
    }

    override fun putDouble(key: String, value: Double) {
        mmkv.encode(key, value)
    }

    override fun putByte(key: String, byteArray: ByteArray) {
        mmkv.encode(key, byteArray)
    }

    override fun putObject(key: String, obj: Any) {
        mmkv.encode(key, serializationService?.object2Json(obj))
    }


    override fun getString(key: String): String {
        return getString(key, "")
    }

    override fun getString(key: String, defaultValue: String): String {
        return mmkv.decodeString(key, defaultValue)
    }

    override fun getBoolean(key: String): Boolean {
        return getBoolean(key, false)
    }

    override fun getBoolean(key: String, defaultValue: Boolean): Boolean {
        return mmkv.decodeBool(key, defaultValue)
    }

    override fun getInt(key: String): Int {
        return mmkv.decodeInt(key)
    }

    override fun getInt(key: String, defaultValue: Int): Int {
        return mmkv.decodeInt(key, defaultValue)
    }

    override fun getLong(key: String): Long {
        return mmkv.decodeLong(key)
    }

    override fun getLong(key: String, defaultValue: Long): Long {
        return mmkv.decodeLong(key, defaultValue)
    }

    override fun getDouble(key: String): Double {
        return mmkv.decodeDouble(key)
    }

    override fun getDouble(key: String, defaultValue: Double): Double {
        return mmkv.decodeDouble(key, defaultValue)
    }

    override fun getByteArray(key: String): ByteArray {
        return mmkv.decodeBytes(key)
    }

    override fun getByteArray(key: String, defaultValue: ByteArray): ByteArray {
        return mmkv.decodeBytes(key, defaultValue)
    }


    override fun <T> getObject(key: String, cla: Type?): T {
        var strValue = getString(key)
        return serializationService.parseObject(strValue, cla)
    }

    override fun clear(keyName: String) {
        mmkv.remove(keyName)
    }
}