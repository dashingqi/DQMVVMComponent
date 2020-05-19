package com.dashingqi.base.widget.bottomtab

import android.content.Context
import android.util.AttributeSet
import android.view.View

import android.widget.LinearLayout
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import com.dashingqi.library_base.R


/**
 * @author : zhangqi
 * @time : 2020/5/19
 * desc :
 */
class BottomBar(context: Context?, attrs: AttributeSet?) : LinearLayout(context, attrs) {
    private var observer = MyDataSetObserver()
    var mOnItemSelectIInterceptor: OnItemSelectInterceptor? = null
    var mOnItemSelectChangedListener: OnItemSelectIChangedListener? = null
    var mOnItemRepeatListener: OnItemRepeatListener? = null
    private var selectIndex = -1
    fun setOnItemSelectInterceptor(interceptor: OnItemSelectInterceptor?) {
        mOnItemSelectIInterceptor = interceptor
    }

    fun setOnItemSelectChangedListener(listener: OnItemSelectIChangedListener) {
        mOnItemSelectChangedListener = listener
    }

    fun setOnItemRepeatListener(listener: OnItemRepeatListener?) {
        mOnItemRepeatListener = listener
    }

    private var mAdapter: Adapter<*, *>? = null
    fun setAdapter(adapter: Adapter<*, *>?) {
        if (adapter == null) {
            return
        }
        if (mAdapter != null) {
            mAdapter.unregisterAdapterDataObserver(observer)
        }
        mAdapter = adapter
        mAdapter.registerAdapterDataObserver(observer)
        selectIndex = -1
        updateItems()
    }

    fun getSelectIndex(): Int {
        return selectIndex
    }

    /**
     * 设置新的选中index
     */
    fun setSelectedIndex(index: Int) {
        if (!isLegal(index)) {
            return
        }
        //判断是否重复点击
        if (index == selectIndex) {
            mOnItemRepeatListener?.onItemRepeat(selectIndex)
            return
        }
        if (mOnItemSelectIInterceptor != null && mOnItemSelectIInterceptor!!.onItemSelect(selectIndex, index)) {
            return
        }

        //没有被拦截
        mOnItemSelectChangedListener?.onItemSelect(selectIndex, index)
        selectedAnimate(getChildAt(index))
        if (isLegal(selectIndex)) {
            unSelectedAnimate(getChildAt(selectIndex))
        }
        selectIndex = index
        if (mAdapter != null) {
            mAdapter.notifyDataSetChanged()
        }
    }

    /**
     * 判断Index是否合法
     */
    private fun isLegal(index: Int): Boolean {
        if (index < 0) {
            return false
        }
        return index < childCount
    }

    private fun updateItems() {
        if (mAdapter == null) {
            return
        }
        for (i in 0 until mAdapter.itemCount) {
            var holder: RecyclerView.ViewHolder? = findViewHolderByPosition(i)
            if (holder == null) {
                holder = mAdapter.createViewHolder(this, mAdapter.getItemViewType(i))
                holder?.itemView?.setTag(R.id.base_bottom_bar_view_holder_tag_id, holder)
                val layoutParams = LayoutParams(0, LayoutParams.MATCH_PARENT, 1f)
                holder?.itemView?.layoutParams = layoutParams
                holder?.itemView?.setOnClickListener { _ -> setSelectedIndex(i) }
                addView(holder?.itemView)
            }
        }
        for (i in mAdapter.itemCount until childCount) {
            removeView(getChildAt(i))
        }
    }

    private fun findViewHolderByPosition(i: Int): RecyclerView.ViewHolder? {
        return if (i >= childCount) {
            null
        } else getChildAt(i).getTag(R.id.base_bottom_bar_view_holder_tag_id) as RecyclerView.ViewHolder
    }

    private fun selectedAnimate(view: View) {
        view.animate().translationY(-5f).scaleX(1.03f).scaleY(1.03f).setDuration(200).start()
    }

    private fun unSelectedAnimate(view: View) {
        view.animate().translationY(0f).scaleX(1.0f).scaleY(1.0f).setDuration(200).start()
    }

    inner class MyDataSetObserver : RecyclerView.AdapterDataObserver() {
        override fun onChanged() {
            super.onChanged()
            updateItems()
        }
    }

    abstract class Adapter<VH : RecyclerView.ViewHolder?, B> : RecyclerView.Adapter<VH>() {
        private var data: MutableList<B> = ArrayList()
        override fun onBindViewHolder(@NonNull holder: VH, position: Int) {}
        private val itemCount: Int = data.size
        override fun getItemCount(): Int {
            return itemCount
        }

        /**
         * onBindViewHolder
         *
         * @param holder
         * @param position
         * @param selectedPosition
         */
        abstract fun onBindViewHolder(holder: VH, position: Int, selectedPosition: Int)
        fun addAll(list: List<B>?) {
            data.addAll(list!!)
        }

        fun add(item: B) {
            data.add(item)
        }

        fun setData(@NonNull data: MutableList<B>) {
            this.data = data
        }

        fun getData(): List<B> {
            return data
        }

        fun getItem(position: Int): B? {
            return if (position < 0 || position >= data.size) {
                null
            } else data[position]
        }

        fun clear() {
            data.clear()
        }
    }

    init {
        orientation = HORIZONTAL
    }
}