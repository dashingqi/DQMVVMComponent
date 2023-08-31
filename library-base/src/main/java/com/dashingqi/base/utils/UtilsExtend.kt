package com.dashingqi.base.utils

import android.view.Gravity
import android.widget.Toast
import com.dashingqi.base.application.BaseApplication

/**
 * @author : zhangqi
 * @time : 2020/7/26
 * desc :
 */

/**
 * 弹出吐司
 */
fun toast(message: String) {
    var toast = Toast.makeText(BaseApplication.instance, message, Toast.LENGTH_SHORT)
    toast.setGravity(Gravity.CENTER, 0, 0)
    toast.show()
}