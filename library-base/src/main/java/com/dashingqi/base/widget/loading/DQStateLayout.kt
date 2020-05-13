package com.dashingqi.base.widget.loading

import android.content.Context
import android.os.Build
import android.os.Parcel
import android.os.Parcelable
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import androidx.annotation.RequiresApi
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

    override fun switchToEmptyLayout() {
    }

    override fun switchToErrorLayout() {
    }

    override fun switchToSuccessLayout() {

    }

    override fun switchToLoadingLayout() {

    }
}