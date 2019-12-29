package com.zulqarnain.testproject.ui.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.zulqarnain.testproject.api.MyService
import com.zulqarnain.testproject.architecture.db.todoDao
import com.zulqarnain.testproject.data.local.Todo
import com.zulqarnain.testproject.data.remote.StoreCategoryResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class MainViewModel @Inject constructor(
    var retrofitService: MyService
) : ViewModel() {

    var _logResponse = MutableLiveData("")
    val logLiveLog: LiveData<String> = _logResponse
//    val todoLiveData: LiveData<Todo> = _logResponse

    init {
//        getStoreCategories()
        dbCall()
    }

    fun dbCall(){
//        todoDao.getToDoList()
    }
    fun getStoreCategories() {

        retrofitService.getCategory("goshoppi777", "22")
            .enqueue(object : Callback<StoreCategoryResponse> {
                override fun onFailure(call: Call<StoreCategoryResponse>, t: Throwable) {
                    Log.e("DummyFragment", "response is null, ErrorBody:${t}")
                }

                override fun onResponse(
                    call: Call<StoreCategoryResponse>,
                    response: Response<StoreCategoryResponse>
                ) {
                    Log.e("DummyFragment", "response ")
                    _logResponse.value = response.toString()
                }
            })

    }


}