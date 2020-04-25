package com.dashingqi.library_base.base.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment

/**
 * @author : zhangqi
 * @time : 2020/4/25
 * desc :
 */
open class BaseFragment : Fragment() {
    private val BASE_TAG = "${this.javaClass.name}"

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.d(BASE_TAG, "onAttach: ${this.javaClass.name}")
    }

    override fun onStart() {
        super.onStart()
        Log.d(BASE_TAG, "onStart: ${this.javaClass.name}")
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Log.d(BASE_TAG, "onActivityCreated: ${this.javaClass.name}")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.d(BASE_TAG, "onDestroyView: ${this.javaClass.name} ")
    }

    override fun onDetach() {
        super.onDetach()
        Log.d(BASE_TAG, "onDetach: ${this.javaClass.name}")
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        Log.d(BASE_TAG, "setUserVisibleHint: ${this.javaClass.name} isVisibleToUser =  $isVisibleToUser")
    }
}