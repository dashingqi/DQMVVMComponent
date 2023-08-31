package com.dashingqi.base.widget.loading

import android.content.Context
import android.os.Bundle
import android.view.Window
import com.dashingqi.library_base.R
import kotlinx.android.synthetic.main.base_loading_dialog_layout.*

/**
 * @author : zhangqi
 * @time : 2020/5/19
 * desc :
 */
class LoadingDialog(context: Context) : BaseDialog(context, R.style.base_loadingDialogTheme) {

    init {
        setContentView(R.layout.base_loading_dialog_layout)
        setCanceledOnTouchOutside(false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ////设置加载框的样式
        loadingDialogPb.indeterminateDrawable = WhiteProgressDrawable()
    }

    override fun getWindow(): Window? {
        return super.getWindow()!!
    }
}