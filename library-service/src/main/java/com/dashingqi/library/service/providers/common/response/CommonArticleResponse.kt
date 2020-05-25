package com.dashingqi.library.service.providers.common.response

/**
 * @author : zhangqi
 * @time : 2020/5/24
 * desc :
 */
data class CommonArticleResponse(
        var apkLink: String,
        var author: String,//作者
        var chapterId: Int,
        var chapterName: String,
        var collect: Boolean,//是否收藏
        var courseId: Int,
        var desc: String,
        var envelopePic: String,
        var fresh: Boolean,
        var id: Int,
        var link: String,
        var niceDate: String,
        var origin: String,
        var prefix: String,
        var projectLink: String,
        var publishTime: Long,
        var superChapterId: Int,
        var superChapterName: String,
        var shareUser: String,
        var tags: List<CommonTagsResponse>,
        var title: String,
        var type: Int,
        var userId: Int,
        var visible: Int,
        var zan: Int){
    fun getCategoryName():String{
        return "$superChapterName / $chapterName"
    }
}