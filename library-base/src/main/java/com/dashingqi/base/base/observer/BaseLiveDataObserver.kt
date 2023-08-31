package com.dashingqi.base.base.observer

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.dashingqi.base.base.livedata.BaseLiveData
import com.dashingqi.base.widget.loading.LoadingDialog

/**
 * @author : zhangqi
 * @time : 2020/7/26
 * desc :
 */
class BaseLiveDataObserver(private var liveData: BaseLiveData, private var owner: LifecycleOwner, activity: Activity) {

    constructor(liveData: BaseLiveData, activity: AppCompatActivity) : this(liveData, activity, activity)
    constructor(liveData: BaseLiveData, fragment: Fragment) : this(liveData, fragment, fragment.activity!!)

    private val loadingDialog by lazy {
        var dialog = LoadingDialog(activity)
        dialog
    }

    init {
        /**
         * 坚挺绑定的FinishActivity的LiveData
         * 当发送事件，就销毁当前Activity的操作
         */
        liveData.finishLiveData.observe(owner, Observer { intValue ->
            activity.setResult(intValue)
            activity.finish()
        })

        /**
         * 用来控制显示加载框
         */
        liveData.showLoading.observe(owner, Observer { cancelabls ->
            loadingDialog.window?.decorView?.tag = cancelabls
            if (cancelabls.size > 0) {
                loadingDialog.show()
            } else {
                loadingDialog.dismiss()
            }
        })
    }
}