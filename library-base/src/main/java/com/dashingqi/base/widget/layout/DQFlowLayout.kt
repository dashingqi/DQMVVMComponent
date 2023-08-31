package com.dashingqi.base.widget.layout

import android.content.Context
import android.util.AttributeSet
import android.util.Log.d
import android.view.View
import android.view.ViewGroup
import com.orhanobut.logger.Logger
import com.orhanobut.logger.Logger.d

/**
 * @author : zhangqi
 * @time : 2020/5/18
 * desc : 流式布局
 */
class DQFlowLayout(context: Context, attrs: AttributeSet?) : ViewGroup(context, attrs) {

    //需要装每一行View集合的集合
    var totalLineViews = mutableListOf<MutableList<View>>()
    var perTotalLineHeightList = mutableListOf<Int>()

    override fun generateDefaultLayoutParams(): LayoutParams {
        return MarginLayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT)
    }

    override fun generateLayoutParams(attrs: AttributeSet?): LayoutParams {
        return MarginLayoutParams(context, attrs)
    }

    override fun generateLayoutParams(p: LayoutParams?): LayoutParams {
        return MarginLayoutParams(p)
    }

    /**
     * 主要确认父布局的宽和高
     * 宽度最宽不能超过 widthSize
     * 需要计算 高度的总和
     */
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        var widthMode = MeasureSpec.getMode(widthMeasureSpec)
        var widthSize = MeasureSpec.getSize(widthMeasureSpec)
        var heightMode = MeasureSpec.getMode(heightMeasureSpec)
        var heightSize = MeasureSpec.getSize(heightMeasureSpec)

        /**
         * 高度的总和
         */
        var totalHeight = 0
        var perLineTotalWidth = 0
        var perLineMaxHeight = 0

        for (i in 0 until childCount) {
            var childView = getChildAt(i)
            //测量子View
            measureChild(childView, widthMeasureSpec, heightMeasureSpec)

            //获取到子View的布局参数
            var childLayoutParams = childView.layoutParams as MarginLayoutParams
            //获取到childView的宽度
            val childViewWidth = childView.measuredWidth + childLayoutParams.leftMargin + childLayoutParams.rightMargin
            //获取到childView的高度
            val childViewHeight = childView.measuredHeight + childLayoutParams.topMargin + childLayoutParams.bottomMargin

            if (perLineTotalWidth + childViewWidth > widthSize) {
                //要换行了
                totalHeight += perLineMaxHeight
                perLineTotalWidth = childViewWidth
                perLineMaxHeight = childViewHeight
            } else {
                perLineTotalWidth += childViewWidth
                perLineMaxHeight = perLineMaxHeight.coerceAtLeast(childViewHeight)
            }

            if (i == childCount - 1) {
                totalHeight += perLineMaxHeight
            }
        }

        heightSize = if (heightMode == MeasureSpec.EXACTLY) {
            heightSize
        } else {
            totalHeight
        }

        setMeasuredDimension(widthSize, heightSize)

    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        totalLineViews.clear()
        //需要一个装每行View的集合
        var perLineView = mutableListOf<View>()

        val heightSize = measuredHeight
        val widthSize = measuredWidth
        var perLineTotalWidth = 0
        var perTotalHeight = 0

        for (index in 0 until childCount) {
            var childView = getChildAt(index)
            val lp = childView.layoutParams as MarginLayoutParams

            val childWidth = childView.measuredWidth + lp.leftMargin + lp.rightMargin
            val childHeight = childView.measuredHeight + lp.topMargin + lp.bottomMargin

            if (childWidth + perLineTotalWidth > widthSize) {
                totalLineViews.add(perLineView)
                perTotalLineHeightList.add(perTotalHeight)
                perLineView = mutableListOf()
                perLineView.add(childView)
                perLineTotalWidth = childWidth
                perTotalHeight = 0
            } else {
                //将符合条件的View添加到集合中
                perLineView.add(childView)
                perLineTotalWidth += childWidth
                perTotalHeight = childHeight.coerceAtLeast(perTotalHeight)
            }
        }

        totalLineViews.add(perLineView)
        perTotalLineHeightList.add(perTotalHeight)

        var left = 0
        var top = 0
        Logger.d("totalLineViews = ${totalLineViews.size}")
        for (index in 0 until totalLineViews.size ) {
            perLineView = totalLineViews[index]
            //获取到每行最高的View的高度
            var perLineMaxHeight = perTotalLineHeightList[index]
            Logger.d("size = ${perLineView.size}")
            for (j in 0 until perLineView.size ) {
                var childView = perLineView[j]
                var layoutParams = childView.layoutParams as MarginLayoutParams
                var childLeft = left + layoutParams.leftMargin
                var childTop = top + layoutParams.topMargin
                var childRight = childLeft + childView.measuredWidth + layoutParams.rightMargin
                var childBottom = childTop + childView.measuredHeight + layoutParams.bottomMargin
                childView.layout(childLeft, childTop, childRight, childBottom)
                left += childView.measuredWidth + layoutParams.leftMargin + layoutParams.rightMargin
            }
            left = 0
            top += perLineMaxHeight
        }
    }
}