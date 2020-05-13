package com.dashingqi.base.base.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel

/**
 * @author : zhangqi
 * @time : 2020/4/25
 * desc : BaseViewModel
 * 在Kotlin中，所有新建的类是final类型，如果想要有子类需要使用
 * 关键字open来修饰这个表明这个类可以被继承。
 */
open class BaseViewModel(application: Application) : AndroidViewModel(application) {
}