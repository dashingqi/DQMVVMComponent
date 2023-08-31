package com.dashingqi.library.service.providers.common.response

import android.text.Html
import androidx.databinding.ObservableField
import kotlin.collections.ArrayList

/**
 * @author : zhangqi
 * @time : 2020/5/24
 * desc :
 */
class CommonArticleResponse {


    var apkLink: String = ""
    var audit: Int = 0
    var author: String = ""//作者
    var chapterId: Int = 0
    var chapterName: String = ""
    var collect: Boolean = false//是否收藏
    var courseId: Int = 0
    var desc: String = ""
    var envelopePic: String = ""
    var fresh: Boolean = false
    var id: Int = 0
    var link: String = ""
    var niceDate: String = ""
    var niceShareDate: String = ""
    var origin: String = ""
    var prefix: String = ""
    var projectLink: String = ""
    var publishTime: Long = 0L
    var superChapterId: Int = 0
    var superChapterName: String = ""
    var shareUser: String = ""
    var tags: List<CommonTagsResponse> = ArrayList()
    var title: String = ""
    var type: Int = 0
    var userId: Int = 0
    var visible: Int = 0
    var zan: Int = 0

    fun getCategoryName(): String {
        return "$superChapterName / $chapterName"
    }

    fun getTitleText(): String {
        return Html.fromHtml(title).toString()
    }

    fun getShareUserText(): String {
        return Html.fromHtml("分享人：${shareUser}").toString()
    }


    /**
     * 控制喜欢的状态变化
     */
    var collectField = ObservableField(false)

    fun isCollectFiledMethod(): ObservableField<Boolean> {
        collectField.set(collect)
        return collectField
    }
}