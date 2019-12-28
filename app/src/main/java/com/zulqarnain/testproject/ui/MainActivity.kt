package com.zulqarnain.testproject.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.zulqarnain.testproject.R
import com.zulqarnain.testproject.common.retrofit.ApiClient
import com.zulqarnain.testproject.data.StoreCategoryResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getStoreCategories()
    }
    private fun getStoreCategories() {
        val response = ApiClient.getInstance().getService().getCategory("goshoppi777", "22")
            .enqueue(object : Callback<StoreCategoryResponse> {
                override fun onFailure(call: Call<StoreCategoryResponse>, t: Throwable) {
                                Log.e("error","response is null, ErrorBody:${t}")
                }

                override fun onResponse(
                    call: Call<StoreCategoryResponse>,
                    response: Response<StoreCategoryResponse>
                ) {
                                Log.e("onResponse","response ")
                }
            })
//
//        if (response.isSuccessful) {
//            if (response.body() != null) {
//                val storeCategoryResponse = response.body() as StoreCategoryResponse
//                val storeCategoryList = storeCategoryResponse.storeCategories
//                if(storeCategoryList!=null) {
//
//                    storeCategoryList.forEach {
//                        if(it.subCategories!=null){
//                            it.subCategories!!.forEach{sub->
//                                sub.categoryId=it.categoryId
//
//                            }
//                        }
//                    }
//                }
//            }
//        } else {
//            Log.e("error","response is null, Message:${response.message()} ErrorBody:${response.errorBody()} Code:${response.code()}")
//        }

    }

}
