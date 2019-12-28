package com.zulqarnain.testproject.api

import com.zulqarnain.testproject.data.StoreCategoryResponse
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface MyService {
    @FormUrlEncoded
    @POST("/services/getCategory")
    fun getCategory(@Field("client_key") client_key:String,
                    @Field("store_id") store_id:String)
            : Call<StoreCategoryResponse>

}