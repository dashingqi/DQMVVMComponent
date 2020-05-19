package com.dashingqi.base.widget.bottomtab

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction

/**
 * @author : zhangqi
 * @time : 2020/5/19
 * desc : 控制Fragment是显示和隐藏
 */
class FragmentSwitchController<T>
(var fragmentManager: FragmentManager, var containerID: Int, var creator: FragmentCreator<T>, var tags: Set<T>) {

    /**
     * 展示Fragment
     */
    fun showFragment(tagId: T) {
        var beginTransaction = fragmentManager.beginTransaction()
        hideOtherFragment(beginTransaction, tagId)
        var fragment = getFragmentByTag(beginTransaction, tagId)
        beginTransaction.show(fragment).commitAllowingStateLoss()
    }

    /**
     * 隐藏其他Fragment
     * 先获取Fragment，然后添加一下 设置一个tag
     * 然后将其隐藏
     */
    private fun hideOtherFragment(transaction: FragmentTransaction, tagId: T) {
        for (tag in tags) {
            //遇到相同的就不隐藏了
            if (tag!! == tagId) {
                continue
            }
            var fragment = fragmentManager.findFragmentByTag(tagId.toString())
            if (fragment != null) {
                if (!fragment.isAdded)
                    transaction.add(containerID, fragment!!, tag.toString())
            }

            transaction.hide(fragment!!)
        }

    }

    private fun getFragmentByTag(transaction: FragmentTransaction, tagId: T): Fragment {
        var fragment = fragmentManager.findFragmentByTag(tagId.toString())
        if (fragment == null) {
            fragment = creator.create(tagId)
        }

        if (fragment != null && !fragment.isAdded) {
            transaction.add(containerID, fragment, tagId.toString())
        }

        return fragment!!
    }
}