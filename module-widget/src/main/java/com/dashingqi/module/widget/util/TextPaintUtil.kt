package com.dashingqi.module.widget.util

import android.text.TextPaint
import android.text.TextUtils

/**
 * @author : zhangqi
 * @time : 7/19/21
 * desc : 文本画笔的工具类
 */
object TextPaintUtil {


    /**
     * 获取到文本的高度
     */
    fun getTextHeight(paint: TextPaint?, includePadding: Boolean): Float {

        var tempTextHeight: Float = 0.0f
        paint?.let { textPaint ->
            tempTextHeight = if (includePadding) {
                textPaint.fontMetrics.bottom - textPaint.fontMetrics.top
            } else {
                textPaint.fontMetrics.descent - textPaint.fontMetrics.ascent
            }
        }

        return tempTextHeight
    }

    /**
     * 获取到文本的宽度
     */
    fun getTextWidth(content: String, textPaint: TextPaint): Float {

        return if (content.isNullOrEmpty()) {
            0.0f
        } else {
            textPaint.measureText(content)
        }
    }

    /**
     * 获取到一行文字的baseLineY值
     */
    fun getTextBaseLineY(paint: TextPaint, maxHeight: Int, includePadding: Boolean): Float {
        var baseLineY = (maxHeight - getTextHeight(paint, includePadding)) / 2
        baseLineY -= if (includePadding) {
            paint.fontMetrics.top
        } else {
            paint.fontMetrics.ascent
        }
        return baseLineY
    }

    fun getNewLineBaseLineY(textPaintOne: TextPaint, textPaintTwo: TextPaint, maxHeight: Int, includePadding: Boolean): Float {
        var baseLineY = (maxHeight -
                getTextHeight(textPaintOne, includePadding) - getTextHeight(textPaintTwo, includePadding)) / 2
        if (includePadding) {
            baseLineY -= textPaintOne.getFontMetrics().top
        } else {
            baseLineY -= textPaintOne.getFontMetrics().ascent
        }
        return baseLineY
    }
}