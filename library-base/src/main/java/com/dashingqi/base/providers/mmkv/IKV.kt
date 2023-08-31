package com.dashingqi.base.providers.mmkv

import java.lang.reflect.Type

/**
 * @author : zhangqi
 * @time : 2020/6/10
 * desc :
 */
interface IKV {

    /**
     * 存储Boolean值
     */
    fun putBoolean(key: String, value: Boolean)

    /**
     * 存储字符串
     */
    fun putString(key: String, value: String)

    /**
     * 存储Int值
     */
    fun putInt(key: String, value: Int)

    /**
     * 存储Long值
     */
    fun putLong(key: String, value: Long)


    /**
     * 存储Float值
     */
    fun putFloat(key: String, value: Float)

    /**
     * 存储Double
     */
    fun putDouble(key: String, value: Double)

    /**
     * 存储字节数组
     */
    fun putByte(key: String, byteArray: ByteArray)

    fun putObject(key: String, obj: Any)

    /**
     * 获取到字符串
     */
    fun getString(key: String): String

    /**
     * 获取到字符串 带有默认值的
     */
    fun getString(key: String, defaultValue: String): String

    /**
     * 获取到Boolean值
     */
    fun getBoolean(key: String): Boolean

    /**
     * 获取到Boolean值，带有默认值
     */
    fun getBoolean(key: String, defaultValue: Boolean): Boolean

    /**
     * 获取到Int值
     */
    fun getInt(key: String): Int

    /**
     * 获取到Int值，带有默认值
     */
    fun getInt(key: String, defaultValue: Int): Int

    /**
     * 获取到Long值
     */
    fun getLong(key: String): Long

    /**
     * 获取到Long值，带有默认值
     */
    fun getLong(key: String, defaultValue: Long): Long

    /**
     * 获取到Double值
     */
    fun getDouble(key: String): Double

    /**
     * 获取到Double值，带有默认值的
     */
    fun getDouble(key: String, defaultValue: Double): Double

    /**
     * 获取到字节数组
     */
    fun getByteArray(key: String): ByteArray?

    /**
     * 获取到字节数组，带有默认值
     */
    fun getByteArray(key: String, defaultValue: ByteArray): ByteArray?

    /**
     * 指定类型
     */
    fun <T> getObject(key: String, cla: Type?): T

    /**
     * 删除指定key对应的value
     */
    fun clear(keyName:String)
}