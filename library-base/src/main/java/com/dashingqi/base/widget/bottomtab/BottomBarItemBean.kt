package com.dashingqi.base.widget.bottomtab

import androidx.lifecycle.MutableLiveData

/**
 * @author : zhangqi
 * @time : 2020/5/19
 * desc :底部tab数据
 */
class BottomBarItemBean(
        text: String,
        priority: Int = 0,
        path: String,
        selectedImageId: Int,
        unSelectedImageId: Int,
        selectedColorId: Int,
        unSelectedColorId: Int,
        tip: Int = 0,
        visibility: Boolean = true
) {
    val textLD = MutableLiveData<String>()
    val priorityLD = MutableLiveData<Int>()
    val pathLD = MutableLiveData<String>()
    val selectedImageIdLD = MutableLiveData<Int>()
    val unSelectedImageIdLD = MutableLiveData<Int>()
    val selectedColorIdLD = MutableLiveData<Int>()
    val unSelectedColorIdLD = MutableLiveData<Int>()
    val tipLD = MutableLiveData<Int>()
    val visibilityLD = MutableLiveData<Boolean>()

    init {
        textLD.value = text
        priorityLD.value = priority
        pathLD.value = path
        selectedImageIdLD.value = selectedImageId
        unSelectedImageIdLD.value = unSelectedImageId
        selectedColorIdLD.value = selectedColorId
        unSelectedColorIdLD.value = unSelectedColorId
        tipLD.value = tip
        visibilityLD.value = visibility
    }
}