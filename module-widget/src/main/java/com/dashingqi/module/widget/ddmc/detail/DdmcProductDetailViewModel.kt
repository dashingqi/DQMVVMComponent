package com.dashingqi.module.widget.ddmc.detail

import android.app.Application
import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableField
import com.dashingqi.base.base.viewmodel.BaseViewModel
import com.dashingqi.base.utils.JsonUtils
import com.dashingqi.module.widget.BR
import com.dashingqi.module.widget.R
import com.dashingqi.module.widget.ddmc.detail.response.DdmcProductDetail
import com.dashingqi.module.widget.ddmc.detail.response.Recipe
import com.dashingqi.module.widget.ddmc.detail.response.ThinkLike
import com.dashingqi.module.widget.ddmc.detail.response.UserRecommend
import me.tatarka.bindingcollectionadapter2.ItemBinding

/**
 * @author : zhangqi
 * @time : 2020/9/3
 * desc :
 */
class DdmcProductDetailViewModel(application: Application) : BaseViewModel(application) {

    val productDetail = JsonUtils.jsonToObject(application, "ddmc_product_detail.json", DdmcProductDetail::class.java)

    val recipeItems = ObservableArrayList<Recipe>()
    val recipeItemBinding = ItemBinding.of<Recipe>(BR.item, R.layout.widget_item_recommend)

    val recommendItems = ObservableArrayList<UserRecommend>()
    val userRecommendItemBinding = ItemBinding.of<UserRecommend>(BR.item, R.layout.widget_ddmc_item_recommend)

    val detailImages = ObservableArrayList<String>()
    val detailsImagesItemBinding = ItemBinding.of<String>(BR.item, R.layout.widget_item_ddmc_details_image)

    val thinkListItems = ObservableArrayList<ThinkLike>()
    val thinkLikeItemBinding = ItemBinding.of<ThinkLike>(BR.item, R.layout.widget_item_ddmc_recommend_list)


    val commentCover = ObservableField("")

    val tabData = ArrayList<String>()

    init {

        recipeItems.addAll(productDetail.data.detail.product_recipe.recipe_list)
        commentCover.set(productDetail.data.detail.product_comment.list[0].img_url[0])
        recommendItems.addAll(productDetail.data.detail.user_recommend)
        detailImages.addAll(productDetail.data.detail.image_details)
        thinkListItems.addAll(productDetail.data.detail.think_like)

        tabData.add("商品")
        tabData.add("评价")
        tabData.add("详情")
        tabData.add("推荐")

    }
}