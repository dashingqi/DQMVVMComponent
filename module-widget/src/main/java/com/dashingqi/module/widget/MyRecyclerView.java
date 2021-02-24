package com.dashingqi.module.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @author : zhangqi
 * @time : 2/24/21
 * desc :
 */
class MyRecyclerView extends RecyclerView {

    private MyScrollChangeListener mMyScrollChangeListener;
    public MyRecyclerView(@NonNull Context context) {
        super(context);
    }

    public MyRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void setOnScrollChangeListener(OnScrollChangeListener l) {
        super.setOnScrollChangeListener(l);
        mMyScrollChangeListener.onScrollChange(l);



    }

    public void setMyScrollChangeListener(MyScrollChangeListener listener){
        mMyScrollChangeListener = listener;
    }

    interface MyScrollChangeListener{
        void onScrollChange(OnScrollChangeListener l);
    }
}
