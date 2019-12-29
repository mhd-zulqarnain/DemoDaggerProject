package com.zulqarnain.testproject.ui


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.zulqarnain.testproject.R
import com.zulqarnain.testproject.api.MyService
import com.zulqarnain.testproject.data.StoreCategoryResponse
import com.zulqarnain.testproject.di.BaseFragment
import dagger.android.support.DaggerFragment
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class DummyFragment : DaggerFragment() {

    @Inject
    lateinit var retrofitService: MyService


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
         return inflater.inflate(R.layout.fragment_dummy, container, false);
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getStoreCategories()
        Log.e("DummyFragment","injected")

    }

    private fun getStoreCategories() {

        retrofitService.getCategory("goshoppi777", "22")
            .enqueue(object : Callback<StoreCategoryResponse> {
                override fun onFailure(call: Call<StoreCategoryResponse>, t: Throwable) {
                    Log.e("DummyFragment","response is null, ErrorBody:${t}")
                }
                override fun onResponse(
                    call: Call<StoreCategoryResponse>,
                    response: Response<StoreCategoryResponse>
                ) {
                    Log.e("DummyFragment","response ")
                }
            })

    }


}
