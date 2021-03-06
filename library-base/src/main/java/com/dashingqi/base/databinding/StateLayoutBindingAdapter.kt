package com.dashingqi.base.databinding

import androidx.databinding.BindingAdapter
import com.dashingqi.base.widget.state.DQStateLayout
import com.dashingqi.base.widget.state.IStateLayout
import com.orhanobut.logger.Logger

/**
 * @author : zhangqi
 * @time : 2020/5/15
 * desc :
 */
object StateLayoutBindingAdapter {

    @JvmStatic
    @BindingAdapter(value = ["stateLayoutState"], requireAll = true)
    fun setState(stateLayout: DQStateLayout, state: Int) {
        if (stateLayout != null) {
            Logger.d("bean --> ${stateLayout.toString()}  state ----> $state")
            when (state) {
                IStateLayout.STATE_LOADING -> stateLayout.switchToLoadingLayout()
                IStateLayout.STATE_EMPTY -> stateLayout.switchToEmptyLayout()
                IStateLayout.STATE_ERROR -> stateLayout.switchToErrorLayout()
                IStateLayout.STATE_SUCCESS -> stateLayout.switchToSuccessLayout()
            }
        }
    }
}