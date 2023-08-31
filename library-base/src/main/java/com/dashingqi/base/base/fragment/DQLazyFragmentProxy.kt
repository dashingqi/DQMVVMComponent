package com.dashingqi.base.base.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.dashingqi.base.base.fragment.DQLazyFragmentProxy.DQLazyProxyOwner
import java.lang.ref.WeakReference

/**
 * @author : zhangqi
 * @time : 2020/4/25
 * desc : 懒加载Fragment的代理文件 用来处理业务逻辑
 */
class DQLazyFragmentProxy<T>(var mFragment: T) where T : Fragment, T : DQLazyProxyOwner? {

    var isLoaded = false
    private var rootViewWeakReference: WeakReference<View?>? = null

    fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, saveInstanceState: Bundle?): View? {
        return if (mFragment.lazyEnabled()) {
            var view: View? = null
            if (rootViewWeakReference != null) {
                view = rootViewWeakReference?.get()
            }

            if (view == null) {
                view = mFragment.view
                rootViewWeakReference = WeakReference(view)
            }

            if (view == null) {
                isLoaded = false
                view = mFragment.getContentView(inflater, container)
                rootViewWeakReference = WeakReference(view)
            }
            loadJudgment(view)
            view
        } else {

            val view = mFragment.view ?: mFragment.getContentView(inflater, container)
            loadJudgment(view)
            mFragment.view
        }
    }

    private fun loadJudgment(view: View?) {
        if (view != null && mFragment.getUserVisibleHint() && !isLoaded && !mFragment.isHidden && mFragment.isShowing()) {
            isLoaded = true
            mFragment.onLoad(view)
        }
    }

    fun onViewCreated(view: View, saveInstanceState: Bundle?) {
        if (mFragment.lazyEnabled()) {
            loadJudgment(mFragment.view)
        } else {
            mFragment.onLoad(view)
        }
    }

    fun onStart() {
        if (mFragment.lazyEnabled()) {
            loadJudgment(mFragment!!.view)
        }
    }

    fun setUserVisibleHint(isVisibleToUser: Boolean) {
        if (mFragment.lazyEnabled()) {
            loadJudgment(mFragment.view)
        }
    }

    fun onHiddenChanged(hidden: Boolean) {
        if (mFragment.lazyEnabled()) {
            loadJudgment(mFragment.view)
        }
    }

    fun onDetach() {
        rootViewWeakReference = null
    }

    interface DQLazyProxyOwner {
        /**
         * 获取到布局文件
         *
         * @param inflater
         * @param parent
         * @return
         */
        fun getContentView(inflater: LayoutInflater, parent: ViewGroup?): View?

        /**
         * 加载操作
         *
         * @param view
         */
        fun onLoad(view: View)

        /**
         * 是否卡其懒加载
         */
        fun lazyEnabled(): Boolean

        /**
         * 判断当前Fragment是否可见的
         */
        fun isShowing(): Boolean
    }

}