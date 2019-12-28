package com.zulqarnain.testproject.data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class SubCategory {

    @SerializedName("subcategory_id")
    @Expose
    var subcategoryId: Long? = null
    @SerializedName("subcategory_name")
    @Expose
    var subcategoryName: String? = null
    @SerializedName("subcategory_image")
    @Expose
    var subcategoryImage: String? = null
    @SerializedName("subcateg_tooltip")
    @Expose
    var subcategTooltip: String? = null
    var categoryId: Long? = null

}
