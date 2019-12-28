package com.zulqarnain.testproject.data
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.zulqarnain.testproject.data.SubCategory

class StoreCategory {

    @SerializedName("category_id")
    @Expose
    var categoryId: Long? = null
    @SerializedName("category_name")
    @Expose
    var categoryName: String? = null
    @SerializedName("category_image")
    @Expose
    var categoryImage: String? = null
    @SerializedName("categ_tooltip")
    @Expose
    var categTooltip: String? = null
    @SerializedName("sub_categories")
    @Expose
    var subCategories: List<SubCategory>? = null

}
