package com.dashingqi.base.widget.loading

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import com.dashingqi.library_base.R

/**
 * @author : zhangqi
 * @time : 2020/5/13
 * desc :
 */
class DQStateLayout : FrameLayout, IStateLayout {

    var defaultErrorLayoutID: Int = View.NO_ID
    var defaultEmptyLayoutID: Int = View.NO_ID
    var defaultLoadLayoutID: Int = View.NO_ID

    var errorLayoutID = defaultErrorLayoutID
    var emptyLayoutID = defaultEmptyLayoutID
    var loadLayoutID = defaultLoadLayoutID

    var emptyView: View? = null
    var errorView: View? = null
    var loadView: View? = null

    constructor(context: Context) : this(context, null) {}
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0) {}
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        attrs(attrs)
    }

    private fun attrs(attrs: AttributeSet?) {
        var obtainAttrs = context.obtainStyledAttributes(attrs, R.styleable.base_DQStateLayout)
        errorLayoutID = obtainAttrs.getResourceId(R.styleable.base_DQStateLayout_base_error_layout, defaultErrorLayoutID)
        emptyLayoutID = obtainAttrs.getResourceId(R.styleable.base_DQStateLayout_base_empty_layout, defaultEmptyLayoutID)
        loadLayoutID = obtainAttrs.getResourceId(R.styleable.base_DQStateLayout_base_load_layout, defaultLoadLayoutID)
        obtainAttrs.recycle()
    }

    /**
     * 切换到空数据布局
     */
    override fun switchToEmptyLayout() {
        if (emptyLayoutID != View.NO_ID && emptyView == null) {
            emptyView = LayoutInflater.from(context).inflate(emptyLayoutID, this, false)
            addView(emptyView)
        }

        handleViewShow(emptyView!!)
    }

    /**
     * 切换到错误布局
     */
    override fun switchToErrorLayout() {
        if (errorLayoutID != View.NO_ID && errorView == null) {
            errorView = LayoutInflater.from(context).inflate(errorLayoutID, this, false)
            this.addView(errorView)
        }
        handleViewShow(errorView!!)
    }

    override fun switchToSuccessLayout() {

    }

    /**
     * 切换到加载中的布局
     */
    override fun switchToLoadingLayout() {

        if (loadLayoutID != View.NO_ID && loadView == null) {
            loadView = LayoutInflater.from(context).inflate(loadLayoutID, this, false)
            addView(loadView)
        }
        handleViewShow(loadView!!)

    }

    /**
     * 处理View的显示
     */
    private fun handleViewShow(view: View) {
        if (view == null)
            return
        if (loadView != null && view != loadView) {
            loadView!!.visibility = View.GONE
        }

        if (errorView != null && view != errorView) {
            errorView!!.visibility = View.GONE
        }

        if (emptyView != null && view != emptyView) {
            emptyView!!.visibility = View.GONE
        }

        view.visibility = View.VISIBLE
    }
}