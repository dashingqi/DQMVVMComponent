package com.dashingqi.module.widget.ddmc.detail.response

import com.dashingqi.base.base.response.BaseResponse

/**
 * @author : zhangqi
 * @time : 2020/9/3
 * desc : 叮咚买菜详情数据
 */
data class DdmcProductDetail(
        val code: Int,
        val `data`: Data,
        val msg: String,
        val server_time: Int,
        val success: Boolean
) : BaseResponse()

data class Data(
        val detail: Detail
)

data class Detail(
        val activity: List<Any>,
        val ad_info: AdInfo,
        val ali_applet_url: String,
        val attend_user_share: Int,
        val buy_limit: String,
        val category_id: String,
        val category_path: String,
        val country_of_origin: Any,
        val coupon_tags: List<Any>,
        val gift_product_ids: List<Any>,
        val hint: String,
        val id: String,
        val image_details: List<String>,
        val image_list: List<String>,
        val is_booking: Int,
        val is_delete: Int,
        val is_gift: Int,
        val is_invoice: Int,
        val is_onion: Int,
        val is_presale: Int,
        val is_promotion: Int,
        val is_stockout_notify: Int,
        val is_vip: Int,
        val mark_discount: Int,
        val mark_new: Int,
        val mark_self: Int,
        val now_promotion: NowPromotion,
        val oid: Int,
        val only_new_user: Boolean,
        val origin_price: String,
        val praise_rate: Int,
        val presale_delivery_date_display: String,
        val price: String,
        val product_comment: ProductComment,
        val product_name: String,
        val product_recipe: ProductRecipe,
        val product_similar: List<Any>,
        val propertys_array: List<PropertysArray>,
        val question_and_answer: List<Any>,
        val share_text: String,
        val sizes: List<Any>,
        val small_image: String,
        val spec: String,
        val status: Int,
        val stock_number: Int,
        val stockout: Int,
        val stockout_reserved: Boolean,
        val sub_list: List<Any>,
        val think_like: List<ThinkLike>,
        val today_stockout: String,
        val type: Int,
        val user_recommend: List<UserRecommend>,
        val vip_exposure: String,
        val vip_price: String,
        val web_url: String,
        val wight_hint: String,
        val wx_applet_code_path: String,
        val wx_applet_url: String
)

data class AdInfo(
        val _id: String,
        val ads: List<Ad>,
        val finish_time: Int,
        val height: Int,
        val id: Int,
        val name: String,
        val start_time: Int,
        val width: Int
)

class NowPromotion(
)

data class ProductComment(
        val list: List<DataBean>,
        val total: Int
)

data class ProductRecipe(
        val is_more: Boolean,
        val recipe_list: List<Recipe>,
        val total: Int
)

data class PropertysArray(
        val name: String,
        val value: String
)

data class ThinkLike(
        val activity: List<Activity>,
        val badge_img: String,
        val badge_position: Int,
        val buy_limit: Int,
        val category_id: String,
        val category_path: String,
        val decision_information: List<String>,
        val id: String,
        val is_booking: Int,
        val is_bulk: Int,
        val is_gift: Int,
        val is_invoice: Int,
        val is_onion: Int,
        val is_presale: Int,
        val is_promotion: Int,
        val is_vod: Boolean,
        val mark_discount: Int,
        val mark_new: Int,
        val mark_self: Int,
        val month_sales: Int,
        val name: String,
        val net_weight: Int,
        val net_weight_unit: String,
        val oid: Int,
        val origin_price: String,
        val presale_delivery_date_display: String,
        val price: String,
        val product_name: String,
        val sale_point_msg: List<List<String>>,
        val sizes: List<Any>,
        val small_image: String,
        val spec: String,
        val status: Int,
        val stock_number: Int,
        val stockout_reserved: Boolean,
        val sub_list: List<Any>,
        val today_stockout: String,
        val total_sales: Int,
        val type: Int,
        val vip_price: String
)

data class UserRecommend(
        val activity: List<ActivityX>,
        val badge_img: String,
        val badge_position: Int,
        val buy_limit: Int,
        val category_id: String,
        val category_path: String,
        val decision_information: List<String>,
        val id: String,
        val is_booking: Int,
        val is_bulk: Int,
        val is_gift: Int,
        val is_invoice: Int,
        val is_onion: Int,
        val is_presale: Int,
        val is_promotion: Int,
        val is_vod: Boolean,
        val mark_discount: Int,
        val mark_new: Int,
        val mark_self: Int,
        val month_sales: Int,
        val name: String,
        val net_weight: Int,
        val net_weight_unit: String,
        val oid: Int,
        val origin_price: String,
        val presale_delivery_date_display: String,
        val price: String,
        val product_name: String,
        val sale_point_msg: List<List<String>>,
        val sizes: List<Any>,
        val small_image: String,
        val spec: String,
        val status: Int,
        val stock_number: Int,
        val stockout_reserved: Boolean,
        val sub_list: List<Any>,
        val today_stockout: String,
        val total_sales: Int,
        val type: Int,
        val vip_price: String
)

data class Ad(
        val content: String,
        val height: Int,
        val height_2: Int,
        val id: String,
        val image: String,
        val image_2: String,
        val link: String,
        val name: String,
        val recipe_mark: Int,
        val width: Int,
        val width_2: Int
)

data class DataBean(
        val _id: String,
        val avatar: String,
        val eval_content: String,
        val eval_star: String,
        val eval_time: String,
        val img_url: List<String>,
        val is_vip: Int,
        val reply_content: String,
        val reply_time: String,
        val username: String
)

data class Recipe(
        val author_avatar: String,
        val author_id: String,
        val author_name: String,
        val favorite_num: String,
        val food_desc: String,
        val height: String,
        val id: String,
        val image: String,
        val is_shelf: Int,
        val is_vod: Boolean,
        val name: String,
        val skill_level: String,
        val time_consuming: String,
        val width: String
)

data class Activity(
        val is_unsold: Boolean,
        val tag: String,
        val type: Int,
        val type_name: String
)

data class ActivityX(
        val is_unsold: Boolean,
        val tag: String,
        val type: Int,
        val type_name: String
)