package com.dashingqi.base.base.activity

import android.os.Bundle
import android.view.LayoutInflater
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.dashingqi.base.base.observer.BaseLiveDataObserver
import com.dashingqi.library_base.BR
import com.dashingqi.base.base.viewmodel.BaseViewModel
import com.dashingqi.base.ext.getDbClass
import com.dashingqi.base.ext.getVmClass

/**
 * @author : zhangqi
 * @time : 2020/4/25
 * desc :
 * 对于抽象类来说，在Kotlin中默认是open的
 * lateinit关键字是Kotlin中延迟初始化的实现 用于变量（var）
 * 说到lateinit关键字，就不得不说下kotlin中另外一个延迟初始化的实现lazy，它是用于常量的（val）
 */
abstract class BaseMVVMActivity<DB : ViewDataBinding, VM : BaseViewModel> : BaseActivity() {
    lateinit var dataBinding: DB
    lateinit var viewModel: VM
    lateinit var mBaseLiveDataObserver: BaseLiveDataObserver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        createDataBinding()
        viewModel = createViewModel()
        dataBinding.setVariable(getVariableId(), viewModel)
        // 这句话的作用 是在xml中使用了LiveData，能监听到LiveData数据源发生变化
        dataBinding.lifecycleOwner = this
        mBaseLiveDataObserver = viewModel.baseLiveData.attach(this, this)
    }

    /**
     * 创建DataBinding
     */
    private fun createDataBinding() {
        var dbClass = getDbClass<DB>(this)
        var method = dbClass.getMethod("inflate", LayoutInflater::class.java)
        dataBinding = method.invoke(null, layoutInflater) as DB
        setContentView(dataBinding.root)
    }

    /**
     * 创建ViewModel
     */
    private fun createViewModel(): VM {
        return ViewModelProvider(this, getViewHolderFactory()).get(getVmClass(this))
    }

    /**
     * 如果你想传递参数到ViewModel中
     * 可以重写这个方法，通过Factory 重新构造一个带参数的ViewModel
     */
    open fun getViewHolderFactory(): ViewModelProvider.Factory {
        return defaultViewModelProviderFactory
    }

    /**
     * BR.viewModel 是由文件 base_br_layout生成的
     */
    private fun getVariableId() = BR.viewModel
}