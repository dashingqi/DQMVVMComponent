package com.dashingqi.base.ext

import android.text.Editable
import android.view.View
import android.widget.TextView
import com.dashingqi.base.empty.EmptyTextWatcher

/**
 * @author : zhangqi
 * @time : 2020/8/4
 * desc : 通常用于 登录按钮随着输入框输入二改变样式
 *
 * EditText :TextView
 *
 * view ---> 用于改变样式的View
 * textViews：通常是输入框们
 */

class EnableExt {
    fun binding(view: View, vararg textViews: TextView) {
        judeViewState(view, textViews)
        var textWatcher = object : EmptyTextWatcher() {
            override fun afterTextChanged(s: Editable?) {
                judeViewState(view, textViews)
            }
        }
        textViews.forEach {
            it.addTextChangedListener(textWatcher)
        }
    }

    /**
     * 用于判断是否要改变当前View的样式
     */
    private fun judeViewState(view: View, textViews: Array<out TextView>) {

        var emptyTextView = textViews.firstOrNull {
            it.text.isEmpty()
        }
        view.isEnabled = (emptyTextView == null)

    }
}