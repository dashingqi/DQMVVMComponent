package com.dashingqi.base.widget.bottomtab;

/**
 * @author : zhangqi
 * @time : 2020/5/20
 * desc :
 */

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import com.dashingqi.library_base.R;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

public class BottomBar extends LinearLayout {
    MyDataSetObserver observer = new MyDataSetObserver();
    OnItemSelectInterceptor mOnItemSelectIInterceptor;
    OnItemSelectIChangedListener mOnItemSelectChangedListener;
    OnItemRepeatListener mOnItemRepeatListener;
    int selectIndex = -1;

    public BottomBar(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    {
        setOrientation(HORIZONTAL);
    }


    public void setOnItemSelectInterceptor(OnItemSelectInterceptor interceptor) {
        mOnItemSelectIInterceptor = interceptor;
    }


    public void setOnItemSelectChangedListener(OnItemSelectIChangedListener listener) {
        mOnItemSelectChangedListener = listener;
    }


    public void setOnItemRepeatListener(OnItemRepeatListener listener) {
        mOnItemRepeatListener = listener;
    }

    private Adapter mAdapter;

    public void setAdapter(Adapter adapter) {
        if (adapter == null) {
            return;
        }
        if (mAdapter != null) {
            mAdapter.unregisterAdapterDataObserver(observer);
        }

        mAdapter = adapter;
        mAdapter.registerAdapterDataObserver(observer);
        //默认设置不选中任何一个
        selectIndex = -1;
        updateItems();
    }

    public int getSelectIndex() {
        return selectIndex;
    }


    /**
     * 设置新的选中index
     */
    public void setSelectedIndex(int index) {
        if (!isLegal(index)) {
            return;
        }
        //判断是否重复点击
        if (index == selectIndex) {
            if (mOnItemRepeatListener != null) {
                mOnItemRepeatListener.onItemRepeat(selectIndex);
            }
            return;
        }
        if (mOnItemSelectIInterceptor != null && mOnItemSelectIInterceptor.onItemSelect(selectIndex, index)) {
            return;
        }

        //没有被拦截
        if (mOnItemSelectChangedListener != null) {
            mOnItemSelectChangedListener.onItemSelect(selectIndex, index);
        }
        selectedAnimate(getChildAt(index));
        if (isLegal(selectIndex)) {
            unSelectedAnimate(getChildAt(selectIndex));
        }
        selectIndex = index;
        if (mAdapter != null) {
            mAdapter.notifyDataSetChanged();
        }
    }

    /**
     * 判断Index是否合法
     */
    private boolean isLegal(int index) {
        if (index < 0) {
            return false;
        }
        if (index >= getChildCount()) {
            return false;
        }
        return true;
    }

    private void updateItems() {
        Logger.d("updateItems");

        if (mAdapter == null) {
            return;
        }

        Logger.d("item count = " + mAdapter.getItemCount());

        for (int i = 0; i < mAdapter.getItemCount(); i++) {
            RecyclerView.ViewHolder holder = findViewHolderByPosition(i);
            if (holder == null) {
                holder = mAdapter.createViewHolder(this, mAdapter.getItemViewType(i));
                holder.itemView.setTag(R.id.base_bottom_bar_view_holder_tag_id, holder);
                LinearLayout.LayoutParams layoutParams = new LayoutParams(0, LayoutParams.MATCH_PARENT, 1f);
                holder.itemView.setLayoutParams(layoutParams);
                final int finalI = i;
                holder.itemView.setOnClickListener(v -> setSelectedIndex(finalI));
                addView(holder.itemView);
            }
            mAdapter.onBindViewHolder(holder, i, selectIndex);
        }

        for (int i = mAdapter.getItemCount(); i < getChildCount(); i++) {
            removeView(getChildAt(i));
        }
    }

    private RecyclerView.ViewHolder findViewHolderByPosition(int i) {
        if (i >= getChildCount()) {
            return null;
        }
        return (RecyclerView.ViewHolder) getChildAt(i).getTag(R.id.base_bottom_bar_view_holder_tag_id);
    }

    /**
     * 选中的动画
     * @param view
     */
    private void selectedAnimate(View view) {
        view.animate().translationY(-5f).scaleX(1.03f).scaleY(1.03f).setDuration(200).start();
    }

    /**
     * 为选中的动画
     * @param view
     */
    private void unSelectedAnimate(View view) {
        view.animate().translationY(0f).scaleX(1.0f).scaleY(1.0f).setDuration(200).start();
    }


    class MyDataSetObserver extends RecyclerView.AdapterDataObserver {
        @Override
        public void onChanged() {
            super.onChanged();
            Logger.d("onChange()");
            updateItems();
        }

    }

    public abstract static class Adapter<VH extends RecyclerView.ViewHolder, B> extends RecyclerView.Adapter<VH> {
        private List<B> data = new ArrayList<>();

        @Override
        public final void onBindViewHolder(@NonNull VH holder, int position) {

        }

        @Override
        public int getItemCount() {
            return data.size();
        }

        /**
         * onBindViewHolder
         *
         * @param holder
         * @param position
         * @param selectedPosition
         */
        public abstract void onBindViewHolder(VH holder, int position, int selectedPosition);

        public void addAll(List<B> list) {
            data.addAll(list);
        }

        public void add(B item) {
            data.add(item);
        }

        public void setData(@NonNull List<B> data) {
            this.data = data;
        }

        public List<B> getData() {
            return data;
        }

        public B getItem(int position) {
            if (position < 0 || position >= data.size()) {
                return null;
            }
            return data.get(position);
        }

        public void clear() {
            data.clear();
        }
    }
}
