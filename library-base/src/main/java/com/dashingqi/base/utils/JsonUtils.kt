package com.dashingqi.base.utils

import android.content.Context
import com.google.gson.Gson
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader

/**
 *
 * @ProjectName: DQMVVMComponent
 * @Package: com.dashingqi.base.utils
 * @ClassName: JsonUtils
 * @Author: DashingQI
 * @CreateDate: 2020/8/26 11:00 PM
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/8/26 11:00 PM
 * @UpdateRemark:
 * @Version: 1.0
 */
object JsonUtils {

    /**
     * 从文件中读取JSON转成字符串
     */
    fun getJson(context: Context, fileName: String?): String? {
        val stringBuilder = StringBuilder()
        //获得assets资源管理器
        val assetManager = context.assets
        //使用IO流读取json文件内容
        try {
            val bufferedReader = BufferedReader(
                    InputStreamReader(
                            assetManager.open(fileName!!), "utf-8"
                    )
            )
            var line: String?
            while (bufferedReader.readLine().also { line = it } != null) {
                stringBuilder.append(line)
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return stringBuilder.toString()
    }

    /**
     * 传入Json字符串
     */
    fun <T> jsonToObject(json: String?, type: Class<T>?): T {
        val gson = Gson()
        return gson.fromJson(json, type)
    }

    /**
     * 传入Json文件名字
     */
    fun <T> jsonToObject(
            context: Context,
            fileName: String?,
            type: Class<T>?
    ): T {
        val gson = Gson()
        return gson.fromJson(getJson(context, fileName), type)
    }

}