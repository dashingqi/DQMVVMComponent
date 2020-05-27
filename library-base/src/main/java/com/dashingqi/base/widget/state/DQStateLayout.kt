package com.dashingqi.base.widget.state

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import com.dashingqi.library_base.R
import com.orhanobut.logger.Logger

/**
 * @author : zhangqi
 * @time : 2020/5/13
 * desc :
 */
class DQStateLayout : FrameLayout, IStateLayout {


    private var errorLayoutID = defaultErrorLayoutID
    private var emptyLayoutID = defaultEmptyLayoutID
    private var loadLayoutID = defaultLoadLayoutID

    private var emptyView: View? = null
    private var errorView: View? = null
    private var loadView: View? = null
    private var successView: View? = null

    private var blockReloadState: Boolean = true

    private var isBlockReload: Boolean = false

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
        blockReloadState = obtainAttrs.getBoolean(R.styleable.base_DQStateLayout_base_block_reload_state, false)
        obtainAttrs.recycle()
    }

    /**
     * 切换到空数据布局
     */
    override fun switchToEmptyLayout() {
        isBlockReload = false
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
        isBlockReload = false
        if (errorLayoutID != View.NO_ID && errorView == null) {
            errorView = LayoutInflater.from(context).inflate(errorLayoutID, this, false)
            this.addView(errorView)
        }
        handleViewShow(errorView!!)
    }

    override fun onFinishInflate() {
        super.onFinishInflate()
        successView = getChildAt(0)
    }

    /**
     * 切换到成功的布局，也就是显示正常数据的布局
     */
    override fun switchToSuccessLayout() {
        if (blockReloadState) isBlockReload = true
        handleViewShow(successView!!)

    }

    /**
     * 切换到加载中的布局
     */
    override fun switchToLoadingLayout() {
        if (isBlockReload) return
        if (loadLayoutID != View.NO_ID && loadView == null) {
            Logger.d("switchToLoadingLayout")
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

//    /**
//     * 控制加载布局
//     */
//    fun setBlockReload(blockState: Boolean): DQStateLayout {
//        isBlockReload = blockState
//        return this
//    }

    companion object {

        private var defaultErrorLayoutID: Int = View.NO_ID
        private var defaultEmptyLayoutID: Int = View.NO_ID
        private var defaultLoadLayoutID: Int = View.NO_ID

        fun setDefaultLoadLayout(loadLayout: Int) {
            defaultLoadLayoutID = loadLayout
        }

        fun setDefaultEmptyLayout(emptyLayout: Int) {
            defaultEmptyLayoutID = emptyLayout
        }

        fun setDefaultErrorLayout(errorLayout: Int) {
            defaultErrorLayoutID = errorLayout
        }
    }
}