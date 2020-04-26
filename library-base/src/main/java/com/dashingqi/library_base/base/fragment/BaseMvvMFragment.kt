package com.dashingqi.library_base.base.fragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.CallSuper
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.dashingqi.library_base.BR
import com.dashingqi.library_base.base.viewmodel.BaseViewModel
import com.dashingqi.library_base.ext.getDbClass
import com.dashingqi.library_base.ext.getVmClass

/**
 * @author : zhangqi
 * @time : 2020/4/25
 * desc :
 */
abstract class BaseMvvMFragment<DB : ViewDataBinding, VM : BaseViewModel> : BaseLazyFragment() {

    lateinit var dataBinding: DB
    lateinit var viewModel: VM


    /**
     * 通过DataBinding获取到布局文件
     */
    override fun getContentView(inflater: LayoutInflater, parent: ViewGroup?): View? {
        createDataBinding()
        return dataBinding.root
    }

    /**
     * 布局加载完成之后了
     */
    @CallSuper
    override fun onLoad(view: View) {
        viewModel = createViewModel()
        dataBinding.lifecycleOwner = this
        dataBinding.setVariable(getVariableId(), viewModel)
    }

    /**
     * 创建DataBinding
     */
    private fun createDataBinding() {
        var dbClass = getDbClass<DB>(this)
        var method = dbClass.getMethod("inflate", LayoutInflater::class.java)
        dataBinding = method.invoke(null, layoutInflater) as DB
    }

    /**
     * 创建ViewModel
     */
    private fun createViewModel(): VM {
        return ViewModelProviders.of(this, getViewModelFactory()).get(getVmClass(this))
    }

    /**
     * 可通过重写该方法，来提供ViewModel传参数
     */
    open fun getViewModelFactory(): ViewModelProvider.Factory? {
        return null
    }

    /**
     * BR.viewModel变量是由 文件 base_br_layout文件生成的
     */
    private fun getVariableId() = BR.viewModel
}