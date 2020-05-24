package com.dashingqi.base.base.fragment


import androidx.fragment.app.Fragment


/**
 * @author : zhangqi
 * @time : 2020/4/25
 * desc :
 */
open class BaseFragment : Fragment() {
    private var showing = false
    private var touchable = false


    override fun onStart() {
        super.onStart()
        showing = true
    }

    override fun onResume() {
        super.onResume()
        touchable = true
    }

    override fun onPause() {
        super.onPause()
        touchable = false
    }

    override fun onStop() {
        super.onStop()
        showing = false
    }
    /**
     * fragment是否可见
     */
    fun fragmentIsShowing(): Boolean = showing

    /**
     * fragment是否可触摸的
     */
    fun isTouchable(): Boolean = touchable
}